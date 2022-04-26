package com.example.zuccecho.repository;

import com.example.zuccecho.entity.MdQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MdQuestionsRepository extends JpaRepository<MdQuestions,Integer>, JpaSpecificationExecutor<MdQuestions> {
}
