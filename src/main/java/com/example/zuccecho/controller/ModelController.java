package com.example.zuccecho.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.zuccecho.constant.ResponseConstant;
import com.example.zuccecho.entity.Model;
import com.example.zuccecho.form.ModelDto;
import com.example.zuccecho.repository.ModelRepository;
import com.example.zuccecho.service.ModelServiceImpl;
import com.example.zuccecho.util.BaseResponsePackageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "model")
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelServiceImpl modelService;

    @RequestMapping(value = "/addModel",method = RequestMethod.POST)
    public Map<String,Object> addModel(
            @RequestBody ModelDto modelDto,
            HttpServletRequest request
    ){
        modelService.create(modelDto);
        return ResponseConstant.V_ADD_SUCCESS;
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public Map<String,Object> getAllModels(){
        List<ModelDto> modelDtos = modelService.getAllModelDto();
        return BaseResponsePackageUtil.baseData(modelDtos);
    }


    @RequestMapping(value = "/getById/{modelId}",method = RequestMethod.POST)
    public Map<String,Object> getModelsById(
            @PathVariable(value = "modelId") int modelId
    ){
        Model m = modelRepository.findById(modelId).orElse(null);
        return BaseResponsePackageUtil.baseData(m);
    }
}
