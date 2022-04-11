package com.example.zuccecho.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.constant.SessionUtil;
import com.example.zuccecho.entry.Student;
import com.example.zuccecho.entry.ZuccClass;
import com.example.zuccecho.repository.ClassRepository;
import com.example.zuccecho.repository.StudentRepository;
import com.example.zuccecho.util.BaseResponsePackageUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "stu")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ClassRepository classRepository;

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


    @RequestMapping(value = "showMyClasses",method = RequestMethod.GET)
    public Map<String,Object> showMyClasses(
            HttpServletRequest request
    ){
        int stuId = SessionUtil.getStudentIdFromSession(request.getSession());
        if(redisTemplate.hasKey(String.valueOf(stuId)+"_class")){
            String res = redisTemplate.opsForList().range(String.valueOf(stuId)+"_class",0,-1).toString();
            return BaseResponsePackageUtil.baseData(res);
        }
        Student student = studentRepository.findById(stuId).orElse(null);
        redisTemplate.opsForList().leftPushAll(String.valueOf(stuId)+"_class",student.getZuccClasses().toString());
        return BaseResponsePackageUtil.baseData(student.getZuccClasses());
    }

    /**
     * 讲课程选到类似购物车，添加到缓存中
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Map<String,Object> addCourse(
        @RequestBody JSONObject p,
        HttpServletRequest request
    ){
        List<Integer> list = p.getJSONArray("classes").toJavaList(Integer.class);
        int stuId = SessionUtil.getStudentIdFromSession(request.getSession());
//        redisTemplate.opsForList().leftPushAll(stuId,list);
        Student student = studentRepository.getById(stuId);
        for(int i=0;i<list.size();i++){
            ZuccClass zuccClass = classRepository.getById(list.get(i));
            student.getZuccClasses().add(zuccClass);
        }
        studentRepository.save(student);
        return ResponseConstant.V_ADD_SUCCESS;
    }


}
