package com.example.zuccecho.form;


import com.example.zuccecho.entity.FeedbackOptions;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FeedbackQuestionDto implements Serializable {

    private final Integer questionId;
    private final String category;
    private final String title;
    private final String remark;
    private final String is_required;
    private final List<FeedbackOptionDto> feedbackOptionsDtos;

}
