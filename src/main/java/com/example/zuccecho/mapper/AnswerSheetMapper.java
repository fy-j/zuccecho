package com.example.zuccecho.mapper;

import com.example.zuccecho.form.AnswerSheetDto;
import com.example.zuccecho.form.StudentDto;

import javax.servlet.http.HttpSession;

public interface AnswerSheetMapper {

    void submit(AnswerSheetDto answerSheetDto, HttpSession session);

    void stuDtoSubmit(HttpSession session,Integer feedbackId);

}
