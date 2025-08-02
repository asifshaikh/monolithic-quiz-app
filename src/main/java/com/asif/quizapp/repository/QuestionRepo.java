package com.asif.quizapp.repository;

import com.asif.quizapp.entity.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<question,Integer> {
    List<question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q where q.category = :category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
    List<question> findRandomQuestionByCategory(String category, int numQ);
}
