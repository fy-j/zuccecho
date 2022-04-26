package com.example.zuccecho.service;


import com.example.zuccecho.config.MailUtil;
import com.example.zuccecho.entity.Student;
import com.example.zuccecho.form.MessageDto;
import com.example.zuccecho.form.StudentDto;
import com.example.zuccecho.mapper.MessageMapper;
import com.example.zuccecho.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService implements MessageMapper {

    @Autowired
    private MailUtil mailUtil;

    @Override
    public void send(List<StudentDto> studentDtos) {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("问卷提醒");
        messageDto.setContent("您有份问卷即将结束,请尽快填写");
        messageDto.setRecipient(studentDtos.stream().map(
                studentDto -> {
                    return studentDto.getEmail();
                }
        ).collect(Collectors.toList()));
        mailUtil.sendSimpleMail(messageDto);
    }
}
