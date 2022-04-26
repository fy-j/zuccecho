package com.example.zuccecho.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "md_options", schema = "zuccecho", catalog = "")
public class MdOptions {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "md_option_id")
    private Integer mdOptionId;

    @Basic
    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = MdQuestions.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id",referencedColumnName = "question_id")
    private MdQuestions questions;

    public Integer getMdOptionId() {
        return mdOptionId;
    }

    public void setMdOptionId(Integer mdOptionId) {
        this.mdOptionId = mdOptionId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MdQuestions getQuestions() {
        return questions;
    }

    public void setQuestions(MdQuestions questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdOptions mdOptions = (MdOptions) o;
        return mdOptionId == mdOptions.mdOptionId && Objects.equals(title, mdOptions.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdOptionId, title);
    }
}
