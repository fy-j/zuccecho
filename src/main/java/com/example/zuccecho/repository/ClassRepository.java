package com.example.zuccecho.repository;

import com.example.zuccecho.entry.ChoiceQuestion;
import com.example.zuccecho.entry.ZuccClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassRepository extends JpaRepository<ZuccClass,Integer> {
    List<ZuccClass> findByTeacherId(int teacherId);

    @Query(value = "update class set class_Name  = ?1 WHERE class_Id = ?2",nativeQuery = true)
    int modifyById(int classId,String className);
}
