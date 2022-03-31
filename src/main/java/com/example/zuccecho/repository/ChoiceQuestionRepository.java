package com.example.zuccecho.repository;

import com.example.zuccecho.entry.ChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChoiceQuestionRepository extends JpaRepository<ChoiceQuestion,Integer>, JpaSpecificationExecutor<ChoiceQuestion> {
}
