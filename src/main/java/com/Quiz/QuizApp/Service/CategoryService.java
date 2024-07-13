package com.Quiz.QuizApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Quiz.QuizApp.Entity.Category;
import com.Quiz.QuizApp.Entity.GenericResponseEntity;
import com.Quiz.QuizApp.Error.DataValidationException;
import com.Quiz.QuizApp.Repository.CategoryRepo;

import jakarta.validation.Valid;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	// Save Category
	public GenericResponseEntity saveCategory(@Valid Category category) {

		// if Category name will passed null
		if (category.getName() == null) {
			throw new DataValidationException("Category name cannot be null");
		}
		// if Category name will pass empty and white spaces

		if (category.getName().isBlank()) {
			throw new DataValidationException("Category name not empty or contain white spaces");
		}
		
		category.setName(category.getName().trim());
		

		// if Category name already present in table with ignore case
		if (categoryRepo.existsByNameIgnoreCase(category.getName())) {
			throw new DataValidationException("Category already present ");
		}

		// check category code is already exists or not
		if (categoryRepo.existsByCode(category.getCode())) {
			throw new DataValidationException("Category code already present ");
		}

		if (category.getIsActive() == null) {
			throw new DataValidationException("Category isActive is null ");
		}
		
		if (category.getCode()== null) {
			throw new DataValidationException("Category code is null ");
		}

		categoryRepo.save(category);

		return new GenericResponseEntity(200, "Category saved successfully");
	}

	// update category by id
	public GenericResponseEntity updateCategory(Long id, Category updatedCategory) {
		Optional<Category> cate = categoryRepo.findById(id);

		if (!cate.isPresent()) {
			throw new DataValidationException("Category not Available");
		}
		Category category = cate.get();

		

		if (!category.getName().equals(updatedCategory.getName())) {

			if (categoryRepo.existsByNameIgnoreCase(category.getName())) {
				throw new DataValidationException("Category Already Exists");
			}

		}
		
		category.setName(updatedCategory.getName());
		categoryRepo.save(category);
		return new GenericResponseEntity(202, "Category Updated successfully");

	}

	// get category by id

	public Category getCategoryById(Long id) {
		Optional<Category> category = categoryRepo.findById(id);

		if (!category.isPresent()) {
			throw new DataValidationException("Category Not Available");
		}
		return category.get();
	}

	// get All Categories

	public List<Category> getAllCategories() {
		return categoryRepo.findByIsActiveTrue();
	}

	// get all categories by pagination

	public Page<Category> getAllCategoryByPage(int page, int size, String search) {
		Pageable pageable = PageRequest.of(page, size);

		if (search != null && !search.isBlank()) {
			return categoryRepo.findByNameContainingIgnoreCase(search, pageable);
		}

		return categoryRepo.findAll(pageable);
	}
}
