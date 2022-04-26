package com.example.zuccecho.form;


import lombok.Data;

import java.util.List;

@Data
public class MdQuestionDto {

    private final int questionId;
    private final String category;
    private final String title;
    private final String is_required;
    private final String remark;

    private final List<MdOptionDto> options;

}
