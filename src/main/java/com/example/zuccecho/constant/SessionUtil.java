package com.example.zuccecho.constant;

import com.example.zuccecho.entry.Student;
import com.example.zuccecho.entry.Teacher;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static void saveToSession(HttpSession session, Student student){
        session.setAttribute("studentLogin",student);
    }

    public static void saveTeacherToSession(HttpSession session, Teacher teacher){
        session.setAttribute("teacherLogin",teacher);
    }

    public static void logoutFromSession(HttpSession session){
        session.removeAttribute("studentLogin");
    }

    public static void logoutTeacherFromSession(HttpSession session){
        session.removeAttribute("teacherLogin");
    }

}
