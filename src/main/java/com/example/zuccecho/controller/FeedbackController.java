package com.example.zuccecho.controller;

import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.entry.Feedback;
import com.example.zuccecho.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @RequestMapping(value = "/add/{classId}/{questionnaireId}")
    public Map<String,Object> addFeedback(
            @PathVariable(value = "classId") int classId,
            @PathVariable(value = "questionnaireId") int questionnaireId
    ){
        Feedback feedback = new Feedback();
        feedback.setQuestionnaireId(questionnaireId);
        feedback.setClassId(classId);
        feedback.setTeacherId(1);
        feedbackRepository.save(feedback);
        return ResponseConstant.V_ADD_SUCCESS;
    }



}
