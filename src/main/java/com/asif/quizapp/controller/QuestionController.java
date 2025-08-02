package com.asif.quizapp.controller;

import com.asif.quizapp.entity.question;
import com.asif.quizapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {


     private final QuestionService questionService;

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<question>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<question> addQuestion(@RequestBody question q) {
        return questionService.addQuestion(q);
    }
    @DeleteMapping("/deleteQuestion/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
    }
}
