package com.example.zuccecho.entry;


import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "feedback",schema = "zuccecho",catalog = "")
public class Feedback {

    @Id
    @Column(name = "feedback")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int feedbackId;

    @Column(name = "questionnaireId")
    public int questionnaireId;

    @Column(name = "classId")
    public int classId;

    @Column(name = "teacherId")
    private int teacherId;
}
