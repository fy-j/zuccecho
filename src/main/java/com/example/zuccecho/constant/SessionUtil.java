package com.example.zuccecho.constant;

import com.example.zuccecho.entity.Student;
import com.example.zuccecho.entity.Teacher;
import com.example.zuccecho.form.StudentDto;
import io.swagger.models.auth.In;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static void saveToSession(HttpSession session, Student student){
        session.setAttribute("studentLogin",new StudentDto(student.getStuId(),student.getName(), student.getPhone(),student.getEmail()));
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

    public static Integer getTeacherIdFromSession(HttpSession session){
        if(session.getAttribute("teacherLogin")==null) return null;
        return ((Teacher)session.getAttribute("teacherLogin")).getTeacherId();
    }

    public static Integer getStudentIdFromSession(HttpSession session){
        if(session.getAttribute("studentLogin")==null) return null;
        return ((StudentDto)session.getAttribute("studentLogin")).getStuId();
    }

    public static StudentDto getStuDtoFromSession(HttpSession session){
        return (StudentDto) session.getAttribute("studentLogin");
    }
}
