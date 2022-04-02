package com.example.zuccecho.repository;

import com.example.zuccecho.entry.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByStuAccount(String account);
}
