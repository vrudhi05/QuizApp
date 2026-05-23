package com.backend.quizapp.service;

import com.backend.quizapp.model.Question;
import com.backend.quizapp.doa.QuestionDoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDoa questionDoa;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDoa.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsBycategory(String category) {
        try {
            if(questionDoa.existsByCategory(category)) {
                return new ResponseEntity<>(questionDoa.findByCategory(category), HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDoa.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateQuestion(Question question, Integer id) {
        try {
            if (questionDoa.existsById(id)) {
                question.setId(id);
                questionDoa.save(question);
                return new ResponseEntity<>("update success",HttpStatus.OK);
            } else
                return new ResponseEntity<>("Question not present",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {

        try {
            if (questionDoa.existsById(id)) {
                questionDoa.deleteById(id);
                return new ResponseEntity<>("delete success",HttpStatus.OK);
            } else
                return new ResponseEntity<>("question not found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
        }
    }
}
