package com.backend.quizapp.controller;

import com.backend.quizapp.model.QuestionWrapper;
import com.backend.quizapp.model.Quiz;
import com.backend.quizapp.model.Response;
import com.backend.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return  quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        System.out.println("Submit endpoint hit with ID: " + id);
        return quizService.calculateQuizResult(id, responses);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Integer id){
        return quizService.deleteQuiz(id);
    }

    @GetMapping("get/allquiz")
    public ResponseEntity<List<Quiz>> getAllQuizes(){
        return quizService.getAllQuizes();
    }

}
