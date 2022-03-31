package com.example.zuccecho.repository;

import com.example.zuccecho.entry.SubjectiveQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubjectiveQuestionRepository extends JpaRepository<SubjectiveQuestion,Integer>, JpaSpecificationExecutor<SubjectiveQuestion> {
}
