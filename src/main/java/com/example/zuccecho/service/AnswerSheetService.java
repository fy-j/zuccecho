package com.example.zuccecho.service;


import com.example.zuccecho.config.QuartzManager;
import com.example.zuccecho.constant.*;
import com.example.zuccecho.entity.AnswerSheet;
import com.example.zuccecho.entity.AnswersheetDetail;
import com.example.zuccecho.entity.Feedback;
import com.example.zuccecho.exception.EchoServiceException;
import com.example.zuccecho.form.AnswerSheetDetailDto;
import com.example.zuccecho.form.AnswerSheetDto;
import com.example.zuccecho.form.StudentDto;
import com.example.zuccecho.mapper.AnswerSheetMapper;
import com.example.zuccecho.repository.AnswerSheetRepository;
import com.example.zuccecho.repository.FeedbackRepository;
import com.example.zuccecho.util.CacheUtil;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnswerSheetService implements AnswerSheetMapper {

    @Autowired
    private AnswerSheetRepository answerSheetRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private QuartzManager quartzManager;

    /*
{
  "answerSheetId": 0,
  "detailDtos": [
    {
      "answerContent": "15",
      "answerContentView": "string",
      "answerSheetId": 0,
      "questionCategory": "string",
      "questionId": 23,
      "questionTitle": "string"
    },
    {
      "answerContent": "有点快",
      "answerContentView": "string",
      "answerSheetId": 0,
      "questionCategory": "string",
      "questionId": 21,
      "questionTitle": "string"
    }
  ],
  "feedbackId": 3,
  "startTime": "2022/04/20 02:20:00",
  "statue": "string",
  "stuId": 0,
  "submitTime": "2022/04/20 02:20:00"
}
     */
    @Override
    @Transactional
    public void submit(AnswerSheetDto answerSheetDto, HttpSession session) {
        Feedback feedback = feedbackRepository.getById(answerSheetDto.getFeedbackId());
        Instant submitTime = Instant.now();
        if(submitTime.compareTo(feedback.getDeadLine())>0) throw new EchoServiceException("已预期，请联系任课老师");
        feedback.getQuestions().stream().forEach(
                feedbackQuestion -> {
                    if(feedbackQuestion.getIsRequired().equals(QuestionRequire.REQUIRE)){
                        for(AnswerSheetDetailDto it: answerSheetDto.getDetailDtos()){
                            if(it.getQuestionId().equals(feedbackQuestion.getQuestionId())) break;
                        }
                        throw new EchoServiceException("有必填项未完成");
                    }
                }
        );
        AnswerSheet answerSheet = new AnswerSheet();
        answerSheet.setStuId(SessionUtil.getStudentIdFromSession(session));
        answerSheet.setSubmitTime(submitTime);
        answerSheet.setState(AnswerStatue.SUBMITTED);
        answerSheet.setFeedbackId(answerSheetDto.getFeedbackId());
        answerSheet.setStartTime(LocalDateTime.parse(answerSheetDto.getStartTime(),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")).toInstant(ZoneOffset.UTC));
        answerSheetDto.getDetailDtos().stream().forEach(
                answerSheetDetailDto -> {
                    AnswersheetDetail answersheetDetail = new AnswersheetDetail();
                    answersheetDetail.setAnswerSheet(answerSheet);
                    feedback.getQuestions().stream().forEach(
                            feedbackQuestion -> {
                                if(feedbackQuestion.getQuestionId()==answerSheetDetailDto.getQuestionId()){
                                    answersheetDetail.setQuestionnId(feedbackQuestion.getQuestionId());
                                    answersheetDetail.setQuestionCategory(feedbackQuestion.getCategory());
                                    answersheetDetail.setQuestionTitle(feedbackQuestion.getTitle());
                                    answersheetDetail.setAnswerContent(answerSheetDetailDto.getAnswerContent());
                                    if(feedbackQuestion.getCategory().equals(Category.Q_CATEGORY_SINGLE_CHOICE)||
                                        feedbackQuestion.getCategory().equals(Category.Q_CATEGORY_MULTI_CHOICE)){
                                        feedbackQuestion.getOptions().stream().forEach(
                                                feedbackOptions -> {
                                                    if(feedbackOptions.getOptionId().toString().equals(answerSheetDetailDto.getAnswerContent())){
                                                        answersheetDetail.setAnswerContentView(feedbackOptions.getTitle());
                                                    }
                                                }
                                        );
                                    }
                                    else answersheetDetail.setAnswerContentView(answerSheetDetailDto.getAnswerContent());
                                }
                            }
                    );
                    answerSheet.getDetails().add(answersheetDetail);
                }
        );
        answerSheetRepository.save(answerSheet);
        stuDtoSubmit(session,answerSheetDto.getFeedbackId());
    }

    @Override
    public void stuDtoSubmit(HttpSession session,Integer feedbackId) {
        redisTemplate.opsForSet().remove(CacheUtil.cacheKey(CacheUtil.SHOULD_FILL_FORM_PREFIX,feedbackId),SessionUtil.getStuDtoFromSession(session));
        redisTemplate.opsForSet().remove(CacheUtil.cacheKey(CacheUtil.UNFINISHED_FORM,feedbackId),SessionUtil.getStuDtoFromSession(session));
        redisTemplate.opsForSet().add(CacheUtil.cacheKey(CacheUtil.FINISHED_FORM,feedbackId),SessionUtil.getStuDtoFromSession(session));
        Set<StudentDto> studentDtos = redisTemplate.opsForSet().members(CacheUtil.cacheKey(CacheUtil.UNFINISHED_FORM,feedbackId));
        if(studentDtos==null||studentDtos.isEmpty()){
            try {
                quartzManager.deleteJob("job"+feedbackId,"group"+feedbackId);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }
}
