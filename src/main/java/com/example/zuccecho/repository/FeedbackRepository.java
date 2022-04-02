package com.example.zuccecho.repository;

import com.example.zuccecho.entry.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
}
