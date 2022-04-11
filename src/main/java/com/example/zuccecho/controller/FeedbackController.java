package com.example.zuccecho.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.constant.SessionUtil;
import com.example.zuccecho.entry.Feedback;
import com.example.zuccecho.repository.FeedbackRepository;
import com.example.zuccecho.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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


    @RequestMapping(value = "showMyFeedbacks/{page}",method = RequestMethod.POST)
    public Map<String,Object> showMyFeedbacks(
            @PathVariable(name = "page") int page,
            HttpServletRequest request
    ){
        Pageable pageable = PageRequest.of(page,10,Sort.by(Sort.Direction.DESC,"feedbackId"));
        int teacherId = SessionUtil.getTeacherIdFromSession(request.getSession());
        Page<Feedback> list = feedbackRepository.findByTeacherId(teacherId,pageable);
        return BaseResponsePackageUtil.baseData(list);
    }


}
