package com.Quiz.QuizApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Quiz.QuizApp.Entity.Quiz;
import com.Quiz.QuizApp.Entity.Result;

public interface ResultRepo extends JpaRepository<Result, Long> {
	
	
	List<Result> findAllByQuiz(Quiz quiz);
	
	@Query(value = "SELECT r.score FROM Result r WHERE r.quiz_id = :quizId" , nativeQuery = true)
	List<Long> findAllScoreByQuizId(@Param("quizId") Long quizId);
}
