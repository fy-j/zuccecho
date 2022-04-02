package com.example.zuccecho.repository;

import com.example.zuccecho.entry.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire,Integer> {
}
