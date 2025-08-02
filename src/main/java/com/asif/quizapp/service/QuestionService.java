package com.asif.quizapp.service;

import com.asif.quizapp.entity.question;
import com.asif.quizapp.repository.QuestionRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<List<question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public ResponseEntity<List<question>> getQuestionsByCategory(String category) {
        try {

            return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<question> addQuestion(question q){
        try{
            return new ResponseEntity<>(questionRepo.save(q), HttpStatus.CREATED);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void deleteQuestion(Integer id) {
        questionRepo.deleteById(id);
    }
}
