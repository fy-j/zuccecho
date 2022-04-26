package com.example.zuccecho.repository;

import com.example.zuccecho.entity.MdOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MdOptionsRepository extends JpaRepository<MdOptions,Integer>, JpaSpecificationExecutor<MdOptions> {
}
