package com.example.zuccecho.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "feekback_question", schema = "zuccecho", catalog = "")
public class FeedbackQuestion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "question_id")
    private int questionId;

    @Basic
    @Column(name = "category")
    private String category;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "remark")
    private String remark;
    @Basic
    @Column(name = "is_required")
    private String isRequired;

    @ManyToOne(targetEntity = Feedback.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_id",referencedColumnName = "feedback_id")
    private Feedback feedback;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<FeedbackOptions> options = new LinkedHashSet<>();

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Set<FeedbackOptions> getOptions() {
        return options;
    }

    public void setOptions(Set<FeedbackOptions> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackQuestion that = (FeedbackQuestion) o;
        return questionId == that.questionId  && Objects.equals(category, that.category) && Objects.equals(title, that.title) && Objects.equals(remark, that.remark) && Objects.equals(isRequired, that.isRequired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, category, title, remark, isRequired);
    }
}
