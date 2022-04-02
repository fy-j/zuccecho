package com.example.zuccecho.entry;


import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "questionnaire",schema = "zuccecho",catalog = "")
@Proxy(lazy = false)
public class Questionnaire {

    @Id
    @Column(name = "questionnaireId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int questionnaireId;

    @Column(name = "questions")
    public String questions;
}
