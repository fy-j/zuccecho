package com.example.zuccecho.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "feedback_options", schema = "zuccecho", catalog = "")
public class FeedbackOptions {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "option_id")
    private Integer optionId;
    @Basic
    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = FeedbackQuestion.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id",referencedColumnName = "question_id")
    private FeedbackQuestion question;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FeedbackQuestion getQuestion() {
        return question;
    }

    public void setQuestion(FeedbackQuestion question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackOptions that = (FeedbackOptions) o;
        return optionId == that.optionId && title.equals(that.title) && question.equals(that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, title, question);
    }
}
