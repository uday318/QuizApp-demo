package com.Quiz.QuizApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Quiz.QuizApp.Entity.Question;
import com.Quiz.QuizApp.Entity.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Long> {

	List<Quiz> findByIsActiveTrue();
	
	Boolean  existsByQuizDatasQuestionsId(Long id);
	
	List<Quiz> findAllByQuizDatasQuestions(Question questions);
	
}
