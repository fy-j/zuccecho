package com.example.zuccecho.repository;

import com.example.zuccecho.entry.SubjectiveQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SubjectiveQuestionRepository extends JpaRepository<SubjectiveQuestion,Integer>, JpaSpecificationExecutor<SubjectiveQuestion> {
    @Query(value = "UPDATE subjective_question set  question_Describe=?2 where quesstion_Id = ?1",nativeQuery=true)
    public Integer subjectiveModify(int id,String des);

}
