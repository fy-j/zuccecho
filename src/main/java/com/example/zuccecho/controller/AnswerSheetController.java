package com.example.zuccecho.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.entry.AnswerSheet;
import com.example.zuccecho.entry.Student;
import com.example.zuccecho.repository.AnswerSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping(value = "answersheet")
public class AnswerSheetController {

    @Autowired
    private AnswerSheetRepository answerSheetRepository;

    @RequestMapping(value = "submit",method = RequestMethod.POST)
    public Map<String,Object> submit(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int feedbackId = p.getInteger("feedbackId");
        int stuId = ((Student) request.getSession().getAttribute("studentLogin")).getStuId();
        JSONArray answer = p.getJSONArray("answer");
        String str = answer.toJSONString();
        AnswerSheet answerSheet = new AnswerSheet();
        answerSheet.setAnswers(str);
        answerSheet.setState(1);
        answerSheet.setFeedbackId(feedbackId);
        answerSheet.setStuId(stuId);
        answerSheetRepository.save(answerSheet);
        return ResponseConstant.V_ADD_SUCCESS;
    }

}
