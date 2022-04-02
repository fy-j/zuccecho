package com.example.zuccecho.constant;

import com.example.zuccecho.entry.Student;

import javax.servlet.http.HttpSession;

public class StuSessionUtil {

    public static void SaveToSession(HttpSession session, Student student){
        session.setAttribute("studentLogin",student);
    }
}
