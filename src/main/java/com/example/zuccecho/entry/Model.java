package com.example.zuccecho.entry;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "model",schema = "zuccecho",catalog = "")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "modelId")
public class Model {

    @Id
    @Column(name = "modelId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int modelId;

    @Column(name = "modelName")
    public String modelName;

    @Column(name = "illustration")
    public String illustration;

    @ManyToMany(targetEntity = ChoiceQuestion.class,cascade = CascadeType.ALL)
    @JoinTable(name = "choice",
                joinColumns = {@JoinColumn(name = "modelId",referencedColumnName = "modelId")},
                inverseJoinColumns = {@JoinColumn(name = "questionId",referencedColumnName = "questionId")})
    Set<ChoiceQuestion> choiceQuestions = new HashSet<>();

    @ManyToMany(targetEntity = SubjectiveQuestion.class,cascade = CascadeType.ALL)
    @JoinTable(name = "subjective",
            joinColumns = {@JoinColumn(name = "modelId",referencedColumnName = "modelId")},
            inverseJoinColumns = {@JoinColumn(name = "questionId",referencedColumnName = "questionId")})
    Set<SubjectiveQuestion> subjectiveQuestions = new HashSet<>();

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration;
    }

    public Set<ChoiceQuestion> getChoiceQuestions() {
        return choiceQuestions;
    }

    public void setChoiceQuestions(Set<ChoiceQuestion> choiceQuestions) {
        this.choiceQuestions = choiceQuestions;
    }

    public Set<SubjectiveQuestion> getSubjectiveQuestions() {
        return subjectiveQuestions;
    }

    public void setSubjectiveQuestions(Set<SubjectiveQuestion> subjectiveQuestions) {
        this.subjectiveQuestions = subjectiveQuestions;
    }
}
