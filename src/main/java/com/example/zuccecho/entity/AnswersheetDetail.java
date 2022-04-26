package com.example.zuccecho.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "answersheet_detail", schema = "zuccecho", catalog = "")
public class AnswersheetDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "detail_id")
    private int detailId;
    @Basic
    @Column(name = "questionn_id")
    private Integer questionnId;
    @Basic
    @Column(name = "question_title")
    private String questionTitle;
    @Basic
    @Column(name = "question_category")
    private String questionCategory;
    @Basic
    @Column(name = "answer_content")
    private String answerContent;
    @Basic
    @Column(name = "answer_content_view")
    private String answerContentView;

    @ManyToOne(targetEntity = AnswerSheet.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "answersheet_Id",referencedColumnName = "answersheet_Id")
    private AnswerSheet answerSheet;

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public Integer getQuestionnId() {
        return questionnId;
    }

    public void setQuestionnId(Integer questionnId) {
        this.questionnId = questionnId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerContentView() {
        return answerContentView;
    }

    public void setAnswerContentView(String answerContentView) {
        this.answerContentView = answerContentView;
    }

    public AnswerSheet getAnswerSheet() {
        return answerSheet;
    }

    public void setAnswerSheet(AnswerSheet answerSheet) {
        this.answerSheet = answerSheet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswersheetDetail that = (AnswersheetDetail) o;
        return detailId == that.detailId && Objects.equals(questionnId, that.questionnId) && Objects.equals(questionTitle, that.questionTitle) && Objects.equals(questionCategory, that.questionCategory) && Objects.equals(answerContent, that.answerContent) && Objects.equals(answerContentView, that.answerContentView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailId, questionnId, questionTitle, questionCategory, answerContent, answerContentView);
    }
}
