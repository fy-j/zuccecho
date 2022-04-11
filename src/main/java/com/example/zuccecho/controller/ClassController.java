package com.example.zuccecho.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.constant.SessionUtil;
import com.example.zuccecho.entry.Teacher;
import com.example.zuccecho.entry.ZuccClass;
import com.example.zuccecho.repository.ClassRepository;
import com.example.zuccecho.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/class")
public class ClassController {
    @Autowired
    public ClassRepository classRepository;

    @RequestMapping("/add")
    public Map<String,Object> classAdd(
            @RequestBody JSONObject p
            ){
        Integer teacherId = p.getInteger("tea_teacher_Id");
        String className = p.getString("class_Name");
        Date createTime = p.getDate("create_Time");
        ZuccClass zuccClass = new ZuccClass();
        zuccClass.setTeacherId(teacherId);
        zuccClass.setClassName(className);
        zuccClass.setCreateTime(createTime);
        classRepository.save(zuccClass);
        return ResponseConstant.V_ADD_SUCCESS;
    }

//    @RequestMapping("/delete")
//    public Map<String,Object> classDeleteById(
//            @RequestBody JSONObject p
//    ){
//        Integer teacherId = p.getInteger("");
//    }

    @RequestMapping("/searchByTeacherId")
    public Map<String,Object> classSearchByTeacherId(
            @RequestBody JSONObject p,
            HttpServletRequest request
    ){
        int teacherId  = SessionUtil.getTeacherIdFromSession(request.getSession());
        List<ZuccClass> list = classRepository.findByTeacherId(teacherId);
        return BaseResponsePackageUtil.baseData(list);
    }



    @RequestMapping("/ModifyById")
    public Map<String,Object> classModify(
            @RequestBody JSONObject p
    ){
        String className = p.getString("class_Name");
        Integer classId = p.getInteger("class_Id");
        classRepository.modifyById(classId,className);
        return ResponseConstant.V_UPDATE_SUCCESS;
    }
}
