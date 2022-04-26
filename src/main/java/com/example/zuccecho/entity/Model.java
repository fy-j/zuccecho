package com.example.zuccecho.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Model {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "model_id")
    private Integer modelId;

    @Basic
    @Column(name = "model_name")
    private String modelName;

    @Basic
    @Column(name = "illustration")
    private String illustration;

    @OneToMany(mappedBy = "model",cascade = CascadeType.PERSIST)
    private Set<MdQuestions> mdQuestions = new HashSet<>();

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

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Set<MdQuestions> getMdQuestions() {
        return mdQuestions;
    }

    public void setMdQuestions(Set<MdQuestions> mdQuestions) {
        this.mdQuestions = mdQuestions;
    }

    public Integer getModelId() {
        return modelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return modelId == model.modelId && Objects.equals(modelName, model.modelName) && Objects.equals(illustration, model.illustration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, modelName, illustration);
    }
}
