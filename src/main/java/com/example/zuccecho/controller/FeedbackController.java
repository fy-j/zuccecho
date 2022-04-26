package com.example.zuccecho.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.constant.SessionUtil;
import com.example.zuccecho.entity.Feedback;
import com.example.zuccecho.entity.Student;
import com.example.zuccecho.form.CreateFeedbackDto;
import com.example.zuccecho.form.PublishFeedbackDto;
import com.example.zuccecho.form.StudentDto;
import com.example.zuccecho.repository.FeedbackRepository;
import com.example.zuccecho.service.FeedbackService;
import com.example.zuccecho.util.BaseResponsePackageUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private FeedbackRepository feedbackRepository;


    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Map<String,Object> createFeedback(
            @RequestBody CreateFeedbackDto feedbackDto,
            HttpServletRequest request
    ){
        feedbackService.createFeedback(feedbackDto,request.getSession());
        return ResponseConstant.V_ADD_SUCCESS;
    }


    @RequestMapping(value = "publish",method = RequestMethod.POST)
    public Map<String,Object> publishFeedback(
            @RequestBody PublishFeedbackDto feedbackDto
    ){
        PublishFeedbackDto feedbackDto1 = feedbackService.publishFeedback(feedbackDto);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                   "msg","发布成功",
                        "res",feedbackDto1
                ));
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

    @RequestMapping(value = "show/{id}",method = RequestMethod.GET)
    public Map<String,Object> getFeedbackById(
        @PathVariable(value = "id") int feedbackId
    ){
        PublishFeedbackDto feedback = feedbackService.getFeedback(feedbackId);
        return BaseResponsePackageUtil.baseData(feedback);
    }

    @RequestMapping(value = "showAllForm/{feedbackId}",method = RequestMethod.GET)
    public Map<String,Object> showAllStuShouldFill(
            @PathVariable(name = "feedbackId") int feedbackId
    ){
        List<StudentDto> students = feedbackService.showAllStuShouldFill(feedbackId);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "nums",students.size(),
                        "res",students
                ));
    }


    @RequestMapping(value = "showUnfinishedForm/{feedbackId}",method = RequestMethod.GET)
    public Map<String,Object> showUnfinishedForm(
        @PathVariable(name = "feedbackId") int feedback
    ){
        List<StudentDto> studentDtos = feedbackService.showAllStuUnFinished(feedback);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "nums",studentDtos.size(),
                        "res",studentDtos
                ));
    }

    @RequestMapping(value = "showFinishedForm/{feedbackId}",method = RequestMethod.GET)
    public Map<String,Object> showFinishedForm(
            @PathVariable(name = "feedbackId") int feedback
    ){
        List<StudentDto> studentDtos = feedbackService.showAllStuFinished(feedback);
        return BaseResponsePackageUtil.baseData(
                ImmutableMap.of(
                        "nums",studentDtos.size(),
                        "res",studentDtos
                ));
    }
}
