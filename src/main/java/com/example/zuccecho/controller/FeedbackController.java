package com.example.zuccecho.controller;

import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.entry.AnswerSheet;
import com.example.zuccecho.entry.Feedback;
import com.example.zuccecho.repository.AnswerSheetRepository;
import com.example.zuccecho.repository.FeedbackRepository;
import com.example.zuccecho.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private AnswerSheetRepository answerSheetRepository;

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

    @RequestMapping(value = "showAll")
    public Map<String,Object> showAllFeedback(

    ){
        int teacherId = 1;
        List<Feedback> list = feedbackRepository.findAllByTeacherId(teacherId);
        return BaseResponsePackageUtil.baseData(list);
    }


}
