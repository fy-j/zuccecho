package com.example.zuccecho.mapper;

import com.example.zuccecho.exception.EchoServiceException;
import com.example.zuccecho.form.ModelDto;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ModelMapper {

    void create(ModelDto modelDto) throws EchoServiceException;

    List<ModelDto> getAllModelDto();

    ModelDto getModelDtoById(int modelId);

}
