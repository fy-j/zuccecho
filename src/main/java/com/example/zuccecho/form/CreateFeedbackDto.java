package com.example.zuccecho.form;


import lombok.Data;

@Data
public class CreateFeedbackDto {

    private final Integer modelId;
    private final Integer classId;
    private final String feedbackName;
    private final String deadLine;


}
