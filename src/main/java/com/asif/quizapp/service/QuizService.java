package com.asif.quizapp.service;

import com.asif.quizapp.entity.QuestionWrapper;
import com.asif.quizapp.entity.Response;
import com.asif.quizapp.entity.question;
import com.asif.quizapp.entity.quiz;
import com.asif.quizapp.repository.QuestionRepo;
import com.asif.quizapp.repository.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepo quizRepo;

    private final QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<question> questions = questionRepo.findRandomQuestionByCategory(category,numQ);

        quiz newQuiz = new quiz();
        newQuiz.setTitle(title);
        newQuiz.setQuestions(questions);
        quiz savedQuiz = quizRepo.save(newQuiz);

        return ResponseEntity.ok("Quiz created successfully with id: " + savedQuiz.getId());
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<quiz> quiz = quizRepo.findById(id);
        List<question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = questionsFromDB.stream()
                .map(q -> new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()))
                .toList();  // Convert the list of questions to a list of QuestionWrapper objects
        return ResponseEntity.ok(questionForUser);
    }

    public ResponseEntity<Integer> submitQuiz(int id, List<Response> responses) {
        quiz quiz = quizRepo.findById(id).get();
        List<question> questions = quiz.getQuestions();
        int score = 0;
        int i =0;
        for(Response response: responses) {
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                score++;
            }
                i++;
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
