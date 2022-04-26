package com.example.zuccecho.form;

import lombok.Data;

@Data
public class AnswerSheetDetailDto {

    private final Integer questionId;
    private final Integer answerSheetId;
    private final String questionTitle;
    private final String questionCategory;
    private final String answerContent;
    private final String answerContentView;

}
