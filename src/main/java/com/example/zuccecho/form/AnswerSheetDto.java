package com.example.zuccecho.form;

import lombok.Data;

import java.util.List;

@Data
public class AnswerSheetDto {

    private final Integer answerSheetId;
    private final Integer StuId;
    private final Integer feedbackId;
    private final String startTime;
    private final String submitTime;
    private final String statue;
    private List<AnswerSheetDetailDto> detailDtos;
}
