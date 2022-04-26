package com.example.zuccecho.mapper;


import com.example.zuccecho.entity.Student;
import com.example.zuccecho.form.CreateFeedbackDto;
import com.example.zuccecho.form.PublishFeedbackDto;
import com.example.zuccecho.form.StudentDto;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface FeedbackMapper {

    void createFeedback(CreateFeedbackDto feedbackDto, HttpSession session);

    PublishFeedbackDto publishFeedback(PublishFeedbackDto feedbackDto);

    PublishFeedbackDto getFeedback(int id);

    void createForm(int classId,int feedbackId);

    List<StudentDto> showAllStuShouldFill(int feedbackId);

    List<StudentDto> showAllStuUnFinished(int feedbackId);

    List<StudentDto> showAllStuFinished(int feedbackId);
}
