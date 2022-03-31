package com.example.zuccecho.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.entry.ChoiceQuestion;
import com.example.zuccecho.repository.ChoiceQuestionRepository;
import com.example.zuccecho.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("choice")
public class ChoiceQuestionController {

    @Autowired
    private ChoiceQuestionRepository choiceQuestionRepository;

    @RequestMapping("/add")
    public Map<String,Object> addChoice(
            @RequestBody JSONObject p
    ){
        String problem = p.getString("problem");
        String choiceA = p.getString("choiceA");
        String choiceB = p.getString("choiceB");
        String choiceC = p.getString("choiceC");
        String choiceD = p.getString("choiceD");
        ChoiceQuestion choiceQuestion = new ChoiceQuestion();
        choiceQuestion.setProblemDescribe(problem);
        choiceQuestion.setChoiceA(choiceA);
        choiceQuestion.setChoiceB(choiceB);
        choiceQuestion.setChoiceC(choiceC);
        choiceQuestion.setChoiceD(choiceD);
        choiceQuestionRepository.save(choiceQuestion);
        return ResponseConstant.V_ADD_SUCCESS;
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public Map<String,Object> getAllChoiceQuestions(){
        List<ChoiceQuestion> questionList = choiceQuestionRepository.findAll();
        return BaseResponsePackageUtil.baseData(questionList);
    }
}
