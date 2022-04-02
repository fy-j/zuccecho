package com.example.zuccecho.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.entry.SubjectiveQuestion;
import com.example.zuccecho.repository.SubjectiveQuestionRepository;
import com.example.zuccecho.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/subjective_question")
public class SubjectiveQuestionnaireController {

    @Autowired
    private SubjectiveQuestionRepository subjectiveQuestionRepository;

    @RequestMapping(value = "/showAll")
    public Map<String,Object> subjectiveShowAll(){
        List<SubjectiveQuestion> list = subjectiveQuestionRepository.findAll();
        return BaseResponsePackageUtil.baseData(list);
    }

    @RequestMapping(value = "/showById/{questionId}")
    public Map<String,Object> subjectiveShowById(
            @PathVariable(value = "questionId") int questionId
    ){
        SubjectiveQuestion subjectiveQuestion = subjectiveQuestionRepository.getById(questionId);
        return BaseResponsePackageUtil.baseData(subjectiveQuestion);
    }

    @RequestMapping(value = "/add")
    public Map<String,Object> subjectiveAdd(
            @RequestBody JSONObject p
    ){
        String des = p.getString("questionDescribe");
        SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();
        subjectiveQuestion.setQuestionDescribe(des);
        subjectiveQuestionRepository.save(subjectiveQuestion);
        return ResponseConstant.V_ADD_SUCCESS;
    }

    @RequestMapping(value="/deleteById/{questionId}")
    public Map<String,Object> subjectiveDeleteById(
            @PathVariable(value = "questionId" ) int quesId
    ){
        subjectiveQuestionRepository.deleteById(quesId);
        return ResponseConstant.V_ADD_SUCCESS;
    }

    @RequestMapping(value = "/modify")
    public Map<String,Object> subjectiveModify(
            @RequestBody JSONObject p
    ){
        int id =p.getInteger("questionId");
        String des = p.getString("questionDescribe");
        subjectiveQuestionRepository.subjectiveModify(id,des);
        return ResponseConstant.V_UPDATE_SUCCESS;
    }
}
