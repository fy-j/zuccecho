package com.example.zuccecho.repository;

import com.example.zuccecho.entry.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

    Page<Feedback> findByTeacherId(int teacherId, Pageable pageable);

}
