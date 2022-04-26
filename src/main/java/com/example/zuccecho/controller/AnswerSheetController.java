package com.example.zuccecho.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.entity.AnswerSheet;
import com.example.zuccecho.entity.AnswersheetDetail;
import com.example.zuccecho.form.AnswerSheetDto;
import com.example.zuccecho.repository.AnswerSheetRepository;
import com.example.zuccecho.service.AnswerSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "answersheet")
public class AnswerSheetController {


    @Autowired
    private AnswerSheetService answerSheetService;

    @RequestMapping(value = "submit",method = RequestMethod.POST)
    public Map<String,Object> submit(
            @RequestBody AnswerSheetDto answerSheetDto,
            HttpServletRequest request
    ){
        answerSheetService.submit(answerSheetDto,request.getSession());
        return ResponseConstant.V_ADD_SUCCESS;
    }

}
