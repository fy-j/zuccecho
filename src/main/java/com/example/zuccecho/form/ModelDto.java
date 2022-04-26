package com.example.zuccecho.form;

import com.example.zuccecho.entity.MdQuestions;
import lombok.Data;

import java.util.List;

@Data
public class ModelDto {

    private final Integer modelId;
    private final String modelName;
    private final String illustration;

    private final List<MdQuestionDto> questions;

}
