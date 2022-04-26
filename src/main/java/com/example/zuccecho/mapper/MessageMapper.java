package com.example.zuccecho.mapper;

import com.example.zuccecho.form.StudentDto;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MessageMapper {

    void send(List<StudentDto> studentDtos);

}
