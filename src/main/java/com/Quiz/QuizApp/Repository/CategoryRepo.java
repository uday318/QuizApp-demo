package com.Quiz.QuizApp.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Quiz.QuizApp.Entity.Category;


public interface CategoryRepo extends JpaRepository<Category, Long> {

	
	Boolean existsByCode(String code);
	
	List<Category> findByIsActiveTrue();
	
	Page<Category> findByNameContainingIgnoreCase(String search,Pageable pageable);

	Boolean existsByNameIgnoreCase(String name);
	
	List<Category> findAllById(Long id);
}
