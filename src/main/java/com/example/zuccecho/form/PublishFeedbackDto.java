package com.example.zuccecho.form;


import com.example.zuccecho.util.CacheUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PublishFeedbackDto implements Serializable {

    private final Integer feedbackId;
    private final Integer classId;
    private final String feedbackName;
    private final Integer teacherId;
    private final String publishTime;
    private final String deadLine;
    private final String createTime;
    private final String statues;
    private final List<FeedbackQuestionDto> questionDtos;



}
