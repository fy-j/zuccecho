package com.example.zuccecho.entry;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "answersheet",schema = "zuccecho",catalog = "")
public class AnswerSheet {

    @Id
    @Column(name = "answersheetId")
    public int answersheetId;

    @Column(name = "feedbackId")
    public int feedbackId;

    @Column(name = "stuId")
    public int stuId;

    @Column(name = "answers")
    public String answers;

    @Column(name = "state")
    public int state;
}
