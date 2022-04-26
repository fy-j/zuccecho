package com.example.zuccecho.entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "answer_sheet", schema = "zuccecho", catalog = "")
public class AnswerSheet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "answersheet_Id")
    private int answersheetId;

    @Basic
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Basic
    @Column(name = "stu_Id")
    private int stuId;

    @Basic
    @Column(name = "state")
    private String state;

    @Basic
    @Column(name = "submit_time")
    private Instant submitTime;

    @Basic
    @Column(name = "start_time")
    private Instant startTime;

    @OneToMany(mappedBy = "answerSheet",cascade = CascadeType.PERSIST)
    private Set<AnswersheetDetail> details = new LinkedHashSet<>();

    public int getAnswersheetId() {
        return answersheetId;
    }

    public void setAnswersheetId(int answersheetId) {
        this.answersheetId = answersheetId;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Instant getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Instant submitTime) {
        this.submitTime = submitTime;
    }

    public Set<AnswersheetDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<AnswersheetDetail> details) {
        this.details = details;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerSheet that = (AnswerSheet) o;
        return answersheetId == that.answersheetId && stuId == that.stuId && Objects.equals(feedbackId, that.feedbackId) && Objects.equals(state, that.state) && Objects.equals(submitTime, that.submitTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answersheetId, feedbackId, stuId, state, submitTime);
    }
}
