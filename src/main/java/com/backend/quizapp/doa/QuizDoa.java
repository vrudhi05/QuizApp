package com.backend.quizapp.doa;

import com.backend.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDoa extends JpaRepository<Quiz,Integer> {
}
