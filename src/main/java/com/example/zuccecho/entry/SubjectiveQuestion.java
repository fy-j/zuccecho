package com.example.zuccecho.entry;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "subjective_question",schema = "zuccecho", catalog = "")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "questionId")
public class SubjectiveQuestion {

    @Id
    @Column(name = "questionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int questionId;

    @Column(name = "questionDescribe")
    public String questionDescribe;

    @ManyToMany(mappedBy = "subjectiveQuestions")
    public Set<Model> models = new HashSet<>();

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDescribe() {
        return questionDescribe;
    }

    public void setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

}
