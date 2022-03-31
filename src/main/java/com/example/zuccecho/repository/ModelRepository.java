package com.example.zuccecho.repository;

import com.example.zuccecho.entry.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ModelRepository extends JpaRepository<Model,Integer>, JpaSpecificationExecutor<Model> {

}
