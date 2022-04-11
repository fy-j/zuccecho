package com.example.zuccecho.repository;

import com.example.zuccecho.entry.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire,Integer> {
    @Query(value = "select a.questionnaire_Id, b.questions from feedback a LEFT JOIN questionnaire b on a.questionnaire_Id=b.questionnaire_Id",nativeQuery = true)
    List<Questionnaire> showAllQuestionnaire(int teacherId);

    @Transactional
    @Modifying
    @Query(value = "update questionnaire p set p.questions = ?2 where p.questionnaire_Id= ?1",nativeQuery = true)
    int updateQuestionnaireById(int questionnaireId,String questions);
}
