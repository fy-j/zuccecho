package com.example.zuccecho.form;

import com.example.zuccecho.entity.FeedbackOptions;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FeedbackOptionDto implements Serializable {

    private final Integer mdOptionId;
    private final String title;

}
