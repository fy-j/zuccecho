package com.example.zuccecho.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.constant.SessionUtil;
import com.example.zuccecho.entity.Teacher;
import com.example.zuccecho.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;


    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Map<String,Object> login(
            HttpServletRequest request,
            @RequestBody JSONObject p
    ){
        String account = p.getString("account");
        String pwd = p.getString("pwd");
        Teacher teacher = teacherRepository.findByAccount(account);
        if(teacher.getPwd().equals(pwd)){
            SessionUtil.saveTeacherToSession(request.getSession(),teacher);
            return ResponseConstant.V_USER_LOGIN_SUCCESS;
        }
        return ResponseConstant.X_USER_WRONG_PASSWORD;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public Map<String,Object> logout(
            HttpServletRequest request
    ){
        SessionUtil.logoutTeacherFromSession(request.getSession());
        return ResponseConstant.V_USER_LOGOUT_SUCCESS;
    }

}
