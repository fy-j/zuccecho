package com.example.zuccecho.repository;

import com.example.zuccecho.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByAccount(String account);
}
