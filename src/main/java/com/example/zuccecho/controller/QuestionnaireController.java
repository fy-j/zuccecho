package com.example.zuccecho.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.entry.Model;
import com.example.zuccecho.entry.Questionnaire;
import com.example.zuccecho.repository.ModelRepository;
import com.example.zuccecho.repository.QuestionnaireRepository;
import com.example.zuccecho.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private ModelRepository modelRepository;


    @RequestMapping(value = "create/{modelId}",method = RequestMethod.POST)
    public Map<String,Object> create(
            @PathVariable(value = "modelId") int modelId
    ){
        Model model = modelRepository.getById(modelId);
        Questionnaire questionnaire = new Questionnaire();
        Map<String,Object> mp = new HashMap<>();
        mp.put("choice",model.getChoiceQuestions());
        mp.put("subjective",model.getSubjectiveQuestions());
        JSONObject jsonObject = new JSONObject(mp);
        questionnaire.setQuestions(jsonObject.toJSONString());
        questionnaireRepository.save(questionnaire);
        return ResponseConstant.V_ADD_SUCCESS;
    }

    @RequestMapping(value = "show/{id}")
    public Map<String,Object> showQuestionnaire(
            @PathVariable(value = "id") int questionnaireId
    ){
        Questionnaire questionnaire = questionnaireRepository.getById(questionnaireId);
        return BaseResponsePackageUtil.baseData(questionnaire);
    }

    @RequestMapping(value = "showAll",method = RequestMethod.POST)
    public Map<String,Object> showAllQuestionnaire(
            HttpServletRequest request
    ){
        int teacherId = (int) request.getSession().getAttribute("teacherId");
        List<Questionnaire> list =questionnaireRepository.showAllQuestionnaire(teacherId);
        return BaseResponsePackageUtil.baseData(list);
    }


    @RequestMapping(value = "modify/{id}",method = RequestMethod.POST)
    public Map<String,Object> modifyQuestionnaire(
            @RequestBody JSONObject p,
            @PathVariable(name = "id") int id
    ){
        String questions = p.getString("questions");
        questionnaireRepository.updateQuestionnaireById(id,questions);
        return ResponseConstant.V_UPDATE_SUCCESS;
    }
}
