package com.example.zuccecho.entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Feedback {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "feedback_id")
    private Integer feedbackId;
    @Basic
    @Column(name = "class_id")
    private int classId;
    @Basic
    @Column(name = "teacher_id")
    private int teacherId;
    @Basic
    @Column(name = "feedback_name")
    private String feedbackName;
    @Basic
    @Column(name = "create_time")
    private Instant createTime;
    @Basic
    @Column(name = "publish_time")
    private Instant publishTime;
    @Basic
    @Column(name = "dead_line")
    private Instant deadLine;
    @Basic
    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "feedback",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<FeedbackQuestion> questions = new HashSet<>();

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getPulishTime() {
        return publishTime;
    }

    public void setPulishTime(Instant pulishTime) {
        this.publishTime = pulishTime;
    }

    public Instant getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Instant deadLine) {
        this.deadLine = deadLine;
    }

    public Set<FeedbackQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<FeedbackQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return feedbackId == feedback.feedbackId && classId == feedback.classId && teacherId == feedback.teacherId && feedbackName.equals(feedback.feedbackName) && createTime.equals(feedback.createTime) && publishTime.equals(feedback.publishTime) && deadLine.equals(feedback.deadLine) && status.equals(feedback.status) && questions.equals(feedback.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackId, classId, teacherId, feedbackName, createTime, publishTime, deadLine, status, questions);
    }
}
