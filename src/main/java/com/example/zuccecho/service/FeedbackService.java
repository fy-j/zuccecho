package com.example.zuccecho.service;

import com.example.zuccecho.config.QuartzManager;
import com.example.zuccecho.constant.FeedbackStatue;
import com.example.zuccecho.constant.SessionUtil;
import com.example.zuccecho.entity.*;
import com.example.zuccecho.exception.EchoServiceException;
import com.example.zuccecho.form.*;
import com.example.zuccecho.mapper.FeedbackMapper;
import com.example.zuccecho.repository.ClazzRepository;
import com.example.zuccecho.repository.FeedbackRepository;
import com.example.zuccecho.repository.ModelRepository;
import com.example.zuccecho.util.CacheUtil;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
public class FeedbackService implements FeedbackMapper {

    private final Logger logger = LoggerFactory.getLogger(FeedbackService.class);


    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ClazzRepository clazzRepository;

    @Autowired
    private QuartzManager quartzManager;


    @Override
    public void createFeedback(CreateFeedbackDto feedbackDto,HttpSession session) {
        Model model = modelRepository.getById(feedbackDto.getModelId());
        Feedback feedback = new Feedback();
        feedback.setFeedbackName(feedbackDto.getFeedbackName());
        feedback.setTeacherId(SessionUtil.getTeacherIdFromSession(session));
        feedback.setClassId(feedbackDto.getClassId());
        feedback.setCreateTime(Instant.now());
        feedback.setDeadLine(LocalDateTime.parse(feedbackDto.getDeadLine(),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")).toInstant(ZoneOffset.UTC));
        feedback.setStatus(FeedbackStatue.NORMAL);
        model.getMdQuestions().stream().forEach(
                mdQuestions -> {
                    FeedbackQuestion feedbackQuestion = new FeedbackQuestion();
                    feedbackQuestion.setCategory(mdQuestions.getCategory());
                    feedbackQuestion.setTitle(mdQuestions.getTitle());
                    feedbackQuestion.setIsRequired(mdQuestions.getIsRequired());
                    feedbackQuestion.setFeedback(feedback);
                    mdQuestions.getOptions().stream().forEach(
                            mdOptions -> {
                                FeedbackOptions options = new FeedbackOptions();
                                options.setTitle(mdOptions.getTitle());
                                options.setQuestion(feedbackQuestion);
                                feedbackQuestion.getOptions().add(options);
                            }
                    );
                    feedback.getQuestions().add(feedbackQuestion);
                }
        );
        feedbackRepository.save(feedback);
    }

    @Override
    public void createForm(int classId,int feedbackId) {
        Clazz clazz = clazzRepository.findById(classId).orElse(null);
        List<StudentDto> students = clazz.getStudentSet().stream().map(
                student -> {
                    return new StudentDto(student.getStuId(), student.getName(), student.getPhone(),student.getEmail());
                }
        ).collect(Collectors.toList());
        redisTemplate.opsForSet().add(CacheUtil.cacheKey(CacheUtil.SHOULD_FILL_FORM_PREFIX,feedbackId),students.toArray());
        redisTemplate.opsForSet().add(CacheUtil.cacheKey(CacheUtil.UNFINISHED_FORM,feedbackId),students.toArray());
        redisTemplate.expire(CacheUtil.cacheKey(CacheUtil.UNFINISHED_FORM,feedbackId),1,TimeUnit.DAYS);
        redisTemplate.expire(CacheUtil.cacheKey(CacheUtil.SHOULD_FILL_FORM_PREFIX,feedbackId),1,TimeUnit.DAYS);
    }

    @Override
    public List<StudentDto> showAllStuShouldFill(int feedbackId) {
        Set<StudentDto> students = redisTemplate.opsForSet().members(CacheUtil.cacheKey(CacheUtil.SHOULD_FILL_FORM_PREFIX,feedbackId));
        logger.warn("load form [{}] from cache",feedbackId);
        return students.stream().collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> showAllStuUnFinished(int feedbackId) {
        Set<StudentDto> students = redisTemplate.opsForSet().members(CacheUtil.cacheKey(CacheUtil.UNFINISHED_FORM,feedbackId));
        logger.warn("load form [{}] from cache",feedbackId);
        return students.stream().collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> showAllStuFinished(int feedbackId) {
        Set<StudentDto> students = redisTemplate.opsForSet().members(CacheUtil.cacheKey(CacheUtil.FINISHED_FORM,feedbackId));
        logger.warn("load form [{}] from cache",feedbackId);
        return students.stream().collect(Collectors.toList());
    }

    /*
{
  "classId": 1,
  "createTime": "2022/04/18 08:02:25",
  "deadLine": "2022/04/20 12:00:00",
  "feedbackId": 3,
  "feedbackName": "反馈1",
  "questionDtos": [
    {
      "category": "single",
      "feedbackOptionsDtos": [
        {
          "mdOptionId": 0,
          "title": "难"
        },
        {
          "mdOptionId": 0,
          "title": "简单"
        },
        {
          "mdOptionId": 0,
          "title": "非常简单"
        }
      ],
      "is_required": "REQUIRE",
      "questionId": 0,
      "remark": "",
      "title": "课程难度"
    },
    {
      "category": "subjective",
      "is_required": "NONE_REQUIRE",
      "questionId": 0,
      "remark": "",
      "title": "你的建议"
    },
    {
      "category": "subjective",
      "is_required": "NONE_REQUIRE",
      "questionId": 0,
      "remark": "",
      "title": "请评价课程"
    }
  ]
}
     */

    /**
     * 支持问卷生成后对问卷进行修改
     * 修改后选择发布
     * 前端传来一张修改后的表单
     * @param feedbackDto
     */
    @Override
    public PublishFeedbackDto publishFeedback(PublishFeedbackDto feedbackDto) {
        Feedback feedback = feedbackRepository.findById(feedbackDto.getFeedbackId()).orElse(null);
        if(feedback==null) throw new EchoServiceException("反馈不存在活已过期");
        feedback.setPulishTime(Instant.now());
        feedback.setStatus(FeedbackStatue.PUBLISHED);
        feedback.setFeedbackName(feedbackDto.getFeedbackName());
        feedback.setDeadLine(LocalDateTime.parse(feedbackDto.getDeadLine(),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")).toInstant(ZoneOffset.UTC));
        feedback.setCreateTime(LocalDateTime.parse(feedbackDto.getCreateTime(),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")).toInstant(ZoneOffset.UTC));
        feedback.setClassId(feedbackDto.getClassId());
        feedback.getQuestions().stream().forEach(
                feedbackQuestion -> {
                    feedbackQuestion.getOptions().clear();
                }
        );
        feedback.getQuestions().clear();
        feedbackDto.getQuestionDtos().stream().forEach(
                feedbackQuestionDto -> {
                    FeedbackQuestion question = new FeedbackQuestion();
                    question.setRemark(feedbackQuestionDto.getRemark());
                    question.setCategory(feedbackQuestionDto.getCategory());
                    question.setTitle(feedbackQuestionDto.getTitle());
                    question.setIsRequired(feedbackQuestionDto.getIs_required());
                    question.setFeedback(feedback);
                    if(feedbackQuestionDto.getFeedbackOptionsDtos()!=null){
                        feedbackQuestionDto.getFeedbackOptionsDtos().stream().forEach(
                                feedbackOptionDto -> {
                                    FeedbackOptions options = new FeedbackOptions();
                                    options.setTitle(feedbackOptionDto.getTitle());
                                    options.setQuestion(question);
                                    question.getOptions().add(options);
                                }
                        );
                    }
                    feedback.getQuestions().add(question);
                }
        );
//        feedback.setQuestions(
//                feedbackDto.getQuestionDtos().stream().map(
//                        feedbackQuestionDto -> {
//                            FeedbackQuestion feedbackQuestion = new FeedbackQuestion();
//                            feedbackQuestion.setRemark(feedbackQuestionDto.getRemark());
//                            feedbackQuestion.setTitle(feedbackQuestionDto.getTitle());
//                            feedbackQuestion.setCategory(feedbackQuestionDto.getCategory());
//                            feedbackQuestion.setIsRequired(feedbackQuestionDto.getIs_required());
//                            feedbackQuestion.setFeedback(feedback);
//                            if(feedbackQuestionDto.getFeedbackOptionsDtos()!=null){
//                                feedbackQuestion.setOptions(
//                                        feedbackQuestionDto.getFeedbackOptionsDtos().stream().map(
//                                                feedbackOptionsDto -> {
//                                                    FeedbackOptions options = new FeedbackOptions();
//                                                    options.setTitle(feedbackOptionsDto.getTitle());
//                                                    options.setQuestion(feedbackQuestion);
//                                                    return options;
//                                                }
//                                        ).collect(Collectors.toSet())
//                                );
//                            }
//                            return feedbackQuestion;
//                        }
//                ).collect(Collectors.toSet())
//        );
        feedbackRepository.saveAndFlush(feedback);
        createForm(feedback.getClassId(),feedback.getFeedbackId());
        try {
            String instant = Instant.now().toString();
//            quartzManager.startJob(feedback.getFeedbackId(),feedback.getFeedbackId(),"0 0 */2 * * ?",feedback.getFeedbackId());
            quartzManager.startJob(feedback.getFeedbackId(),feedback.getFeedbackId(),"0 */1 * * * ?",feedback.getFeedbackId());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return getFeedback(feedbackDto.getFeedbackId());
    }

    @Override
    public PublishFeedbackDto getFeedback(int feedbackId){

        PublishFeedbackDto feedbackDtoFromRedis = (PublishFeedbackDto) redisTemplate.opsForValue().get(CacheUtil.cacheKey(CacheUtil.FEEDBACK_KEY_PREFIX,feedbackId));
        if(feedbackDtoFromRedis!=null){
            logger.warn("load paper[{}][{}] from cache.",feedbackDtoFromRedis.getFeedbackId(),feedbackDtoFromRedis.getFeedbackName());
            return feedbackDtoFromRedis;
        }
        Feedback feedback = feedbackRepository.getById(feedbackId);
        PublishFeedbackDto feedbackDto = new PublishFeedbackDto(feedback.getFeedbackId(),feedback.getClassId(),
                feedback.getFeedbackName(),feedback.getTeacherId(),feedback.getPulishTime().toString(),feedback.getDeadLine().toString(),
                feedback.getCreateTime().toString(),feedback.getStatus(),
                feedback.getQuestions().stream().map(
                        feedbackQuestion -> {
                            return new FeedbackQuestionDto(feedbackQuestion.getQuestionId(),feedbackQuestion.getCategory(),
                                feedbackQuestion.getTitle(),feedbackQuestion.getRemark(),feedbackQuestion.getIsRequired(),
                                feedbackQuestion.getOptions().stream().map(
                                        feedbackOptions ->{
                                            return new FeedbackOptionDto(feedbackOptions.getOptionId(),feedbackOptions.getTitle());
                                        }
                                ).collect(Collectors.toList()));
                            }

                ).collect(Collectors.toList()));
        redisTemplate.opsForValue().set(CacheUtil.cacheKey(CacheUtil.FEEDBACK_KEY_PREFIX,feedbackId),feedbackDto,1, TimeUnit.HOURS);
        logger.warn("load paper[{}][{}]",feedbackDto.getFeedbackId(),feedbackDto.getFeedbackName());
        return feedbackDto;
    }

}
