package com.example.zuccecho.repository;

import com.example.zuccecho.entry.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire,Integer> {
    @Query(value = "select a.questionnaire_Id, b.questions from feedback a LEFT JOIN questionnaire b on a.questionnaire_Id=b.questionnaire_Id",nativeQuery = true)
    public List<Questionnaire> showAllQuestionnaire(int teacherId);
}
