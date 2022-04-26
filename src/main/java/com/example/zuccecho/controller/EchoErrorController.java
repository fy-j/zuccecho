package com.example.zuccecho.controller;

import com.example.zuccecho.exception.EchoServiceException;
import com.example.zuccecho.util.BaseResponsePackageUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestControllerAdvice
public class EchoErrorController {

    @ExceptionHandler(Exception.class)
    public Map<String,Object> handleGlobalError(HttpServletRequest request, Exception ex){
        ex.printStackTrace();
        return BaseResponsePackageUtil.errorMessage(ex.getMessage());
    }

    public Map<String,Object> handleEchoError(HttpServletRequest request, EchoServiceException ex){
        ex.printStackTrace();
        return BaseResponsePackageUtil.errorMessage(ex.getMessage());
    }


}
