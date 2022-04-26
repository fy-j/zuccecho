package com.example.zuccecho.service;

import com.example.zuccecho.constant.Category;
import com.example.zuccecho.entity.MdOptions;
import com.example.zuccecho.entity.MdQuestions;
import com.example.zuccecho.entity.Model;
import com.example.zuccecho.exception.EchoServiceException;
import com.example.zuccecho.form.MdOptionDto;
import com.example.zuccecho.form.MdQuestionDto;
import com.example.zuccecho.form.ModelDto;
import com.example.zuccecho.mapper.ModelMapper;
import com.example.zuccecho.repository.MdOptionsRepository;
import com.example.zuccecho.repository.MdQuestionsRepository;
import com.example.zuccecho.repository.ModelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelMapper {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private MdQuestionsRepository mdQuestionsRepository;

    @Autowired
    private MdOptionsRepository mdOptionsRepository;


    /**
     * {
     *     "modelName":"模板3",
     *     "illustration":"日常反馈",
     *     "questions":[
     *         {
     *             "category":"single",
     *             "title":"课程难度",
     *             "is_required":"REQUIRE",
     *             "remark":"无",
     *             "options":[
     *                 {
     *                     "title":"难"
     *                 },
     *                 {
     *                     "title":"简单"
     *                 }
     *             ]
     *         },
     *         {
     *             "category":"subjective",
     *             "title":"你的建议",
     *             "is_required":"NONE_REQUIRE",
     *             "remark":"无"
     *         }
     *     ]
     * }
     * @param modelDto
     * @throws EchoServiceException
     */
    @Override
    public void create(ModelDto modelDto) throws EchoServiceException {
        if(modelDto.getModelId()!=null) throw new EchoServiceException("模板Id应为空");
        if(modelDto.getQuestions()!=null){
            modelDto.getQuestions().stream().forEach(
                    mdQuestions -> {
                        if(!mdQuestions.getCategory().equals(Category.Q_CATEGORY_SUBJECTIVE)
                                &&!mdQuestions.getCategory().equals(Category.Q_CATEGORY_MULTI_CHOICE)
                                &&!mdQuestions.getCategory().equals(Category.Q_CATEGORY_SINGLE_CHOICE)) throw new EchoServiceException("未知题目类型");
                        if(mdQuestions.getCategory().equals(Category.Q_CATEGORY_MULTI_CHOICE)
                                ||mdQuestions.getCategory().equals(Category.Q_CATEGORY_SINGLE_CHOICE)){
                            if(mdQuestions.getOptions()==null||mdQuestions.getOptions().size()==0) throw new EchoServiceException("选择题选项不能为空");
                        }
                        if(mdQuestions.getOptions()!=null&&mdQuestions.getOptions().size()!=0){
                            mdQuestions.getOptions().stream().forEach(
                                    mdOptions -> {
                                        if(mdOptions.getMdOptionId()!=null) throw new EchoServiceException("选项Id应为空");
                                    }
                            );
                        }
                    }
            );
        }

        Model model = new Model();
        BeanUtils.copyProperties(modelDto,model);
        if(modelDto.getQuestions()!=null){
            modelDto.getQuestions().stream().forEach(
                    mdQuestions -> {
                        MdQuestions questions = new MdQuestions();
                        questions.setQuestionId(mdQuestions.getQuestionId());
                        questions.setIsRequired(mdQuestions.getIs_required());
                        questions.setRemark(mdQuestions.getRemark());
                        questions.setTitle(mdQuestions.getTitle());
                        questions.setCategory(mdQuestions.getCategory());
                        questions.setModel(model);
                        if(mdQuestions.getOptions()!=null&&mdQuestions.getOptions().size()!=0){
                            mdQuestions.getOptions().stream().forEach(
                                    mdOptions -> {
                                        MdOptions options = new MdOptions();
                                        options.setTitle(mdOptions.getTitle());
                                        options.setQuestions(questions);
                                        questions.getOptions().add(options);
                                    }
                            );
                        }
                        model.getMdQuestions().add(questions);
                    }
            );
            modelRepository.save(model);
        }
    }

    @Override
    public List<ModelDto> getAllModelDto() {
        List<Model> models = modelRepository.findAll();
        List<ModelDto> modelDtos = new ArrayList<>();
        if(models!=null&&models.size()!=0){
            models.stream().forEach(
                    model -> {
                        ModelDto modelDto = new ModelDto(model.getModelId(),model.getModelName(),model.getIllustration(),
                                model.getMdQuestions().stream().map(
                                        mdQuestions -> new MdQuestionDto(mdQuestions.getQuestionId(),mdQuestions.getCategory(), mdQuestions.getTitle(), mdQuestions.getIsRequired(), mdQuestions.getRemark(),
                                                mdQuestions.getOptions().stream().map(
                                                        mdOptions -> new MdOptionDto(mdOptions.getMdOptionId(),mdOptions.getTitle())
                                                ).collect(Collectors.toList())
                                )).collect(Collectors.toList()));
                        modelDtos.add(modelDto);
                    }
            );
        }
        return modelDtos;
    }

    @Override
    public ModelDto getModelDtoById(int modelId) {
        Model model = modelRepository.getById(modelId);

        return null;
    }


}
