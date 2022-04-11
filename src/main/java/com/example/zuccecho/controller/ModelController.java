package com.example.zuccecho.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.entry.ChoiceQuestion;
import com.example.zuccecho.entry.Model;
import com.example.zuccecho.entry.SubjectiveQuestion;
import com.example.zuccecho.repository.ChoiceQuestionRepository;
import com.example.zuccecho.repository.ModelRepository;
import com.example.zuccecho.repository.SubjectiveQuestionRepository;
import com.example.zuccecho.util.BaseResponsePackageUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "model")
public class ModelController {

    @Autowired
    public ModelRepository modelRepository;

    @Autowired
    private ChoiceQuestionRepository choiceQuestionRepository;

    @Autowired
    private SubjectiveQuestionRepository subjectiveQuestionRepository;

    @RequestMapping(value = "/addModel",method = RequestMethod.POST)
    public Map<String,Object> addModel(
            @RequestBody JSONObject p
    ){
        String modelName = p.getString("modelName");
        String illustration = p.getString("illustration");
        List<Integer> choices = p.getJSONArray("choices").toJavaList(Integer.class);
        List<Integer> subjectives = p.getJSONArray("subjectives").toJavaList(Integer.class);
        Model model = new Model();
        for(int i=0;i<choices.size();i++){
            ChoiceQuestion byId = choiceQuestionRepository.findById(choices.get(i)).orElse(null);
            model.getChoiceQuestions().add(byId);
        }
        for(int i=0;i<subjectives.size();i++){
            SubjectiveQuestion byId = subjectiveQuestionRepository.getById(subjectives.get(i));
            model.getSubjectiveQuestions().add(byId);
        }
        model.setModelName(modelName);
        model.setIllustration(illustration);
        modelRepository.save(model);
        return ResponseConstant.V_ADD_SUCCESS;
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public Map<String,Object> getAllModels(){
        List<Model> modelList = modelRepository.findAll();
        return BaseResponsePackageUtil.baseData(modelList);
    }


    @RequestMapping(value = "/getById/{modelId}",method = RequestMethod.POST)
    public Map<String,Object> getModelsById(
            @PathVariable(value = "modelId") int modelId
    ){
        Model m = modelRepository.findById(modelId).orElse(null);
        return BaseResponsePackageUtil.baseData(m);
    }
}
