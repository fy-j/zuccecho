package com.example.zuccecho.entry;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "choice_question",schema = "zuccecho",catalog = "")
@Proxy(lazy = false)
public class ChoiceQuestion {

    @Id
    @Column(name = "questionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int questionId;

    @Column(name = "problemDescribe")
    public String problemDescribe;

    @Column(name = "choiceA")
    public String choiceA;

    @Column(name = "choiceB")
    public String choiceB;

    @Column(name = "choiceC")
    public String choiceC;

    @Column(name = "choiceD")
    public String choiceD;

    @JsonIgnore
    @ManyToMany(mappedBy = "choiceQuestions")
    public Set<Model> models = new HashSet<>();

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getProblemDescribe() {
        return problemDescribe;
    }

    public void setProblemDescribe(String problemDescribe) {
        this.problemDescribe = problemDescribe;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }
}
