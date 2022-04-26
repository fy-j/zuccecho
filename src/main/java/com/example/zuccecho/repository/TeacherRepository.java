package com.example.zuccecho.repository;

import com.example.zuccecho.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Teacher findByAccount(String account);
}
