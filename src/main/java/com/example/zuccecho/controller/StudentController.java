package com.example.zuccecho.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.constant.SessionUtil;
import com.example.zuccecho.entry.Student;
import com.example.zuccecho.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "stu")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public Map<String,Object> login(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        String account = p.getString("account");
        String pwd = p.getString("pwd");
        Student stu = studentRepository.findByStuAccount(account);
        if(!stu.getPwd().equals(pwd)){
            return ResponseConstant.X_USER_WRONG_PASSWORD;
        }
        SessionUtil.saveToSession(request.getSession(),stu);
        return ResponseConstant.V_USER_LOGIN_SUCCESS;
    }

    @RequestMapping(value = "reg",method = RequestMethod.POST)
    public Map<String,Object> reg(
            @RequestBody JSONObject p
    ){
        String account = p.getString("account");
        String pwd = p.getString("pwd");
        String name = p.getString("name");
        String phone = p.getString("phone");
        Student student = new Student();
        student.setStuAccount(account);
        student.setName(name);
        student.setPhone(phone);
        student.setPwd(pwd);
        studentRepository.save(student);
        return ResponseConstant.V_ADD_SUCCESS;
    }
}
