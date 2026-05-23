package com.backend.quizapp.service;

import com.backend.quizapp.doa.QuestionDoa;
import com.backend.quizapp.doa.QuizDoa;
import com.backend.quizapp.model.Question;
import com.backend.quizapp.model.QuestionWrapper;
import com.backend.quizapp.model.Quiz;
import com.backend.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDoa quizDoa;

    @Autowired
    QuestionDoa questionDoa;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        try {
            List<Question> questions = questionDoa.findRandomQuestionsByCategory(category, numQ);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDoa.save(quiz);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz = quizDoa.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q:questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateQuizResult(Integer id, List<Response> responses) {
        // Check if the quiz exists
        Optional<Quiz> optionalQuiz = quizDoa.findById(id);
        if (optionalQuiz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if quiz not found
        }

        Quiz quiz = optionalQuiz.get();
        List<Question> questions = quiz.getQuestions();

        // Validate responses size
        if (responses == null || responses.size() != questions.size()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 if responses mismatch
        }

        int right = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (responses.get(i).getResponse().equalsIgnoreCase(questions.get(i).getRightAnswer())) {
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK); // 200 OK with the score
    }

    public ResponseEntity<String> deleteQuiz(Integer id) {
        if(quizDoa.existsById(id)){
            quizDoa.deleteById(id);
            return new ResponseEntity<>("Deletion Success",HttpStatus.OK);
        }
        return new ResponseEntity<>("Quiz do not exist !!" , HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Quiz>> getAllQuizes() {
        try {
            return new ResponseEntity<>(quizDoa.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
}


