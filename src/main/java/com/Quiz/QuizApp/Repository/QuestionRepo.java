package com.Quiz.QuizApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Quiz.QuizApp.Entity.Question;
import com.Quiz.QuizApp.Enum.Level;

public interface QuestionRepo extends JpaRepository<Question, Long>{

	Boolean existsByNameIgnoreCase(String name);
	
	List<Question> findByIsActiveTrue();
	
	Page<Question> findByNameContainingIgnoreCase(String search,Pageable pageable);
	
	List<Question> findByCategoryId(Long categoryId);
	
	List<Question> findByCategoryIdAndLevel(Long categoryId , Level level);
	
	Optional<Question> findById(Long id);
	
	List<Question> findAll();
	
}
