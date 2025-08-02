package com.asif.quizapp.repository;

import com.asif.quizapp.entity.quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<quiz,Integer> {
}
