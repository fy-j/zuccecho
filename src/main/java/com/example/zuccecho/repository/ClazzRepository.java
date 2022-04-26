package com.example.zuccecho.repository;

import com.example.zuccecho.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClazzRepository extends JpaRepository<Clazz,Integer> {

}
