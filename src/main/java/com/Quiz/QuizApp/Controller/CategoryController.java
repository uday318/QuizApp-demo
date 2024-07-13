package com.Quiz.QuizApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Quiz.QuizApp.Entity.Category;
import com.Quiz.QuizApp.Entity.GenericResponseEntity;
import com.Quiz.QuizApp.Error.DataValidationException;
import com.Quiz.QuizApp.Service.CategoryService;

@RestController
public class CategoryController {


	@Autowired
	private CategoryService categoryService;

	//Add Category 
	
	@PostMapping("/addCategory")
	public ResponseEntity<GenericResponseEntity> saveCategory(@RequestBody Category category)
			throws DataValidationException {

		GenericResponseEntity response = categoryService.saveCategory(category);
		return ResponseEntity.status(response.getCode()).body(response);

	}

	//Update Category By id 

	@PutMapping("/updateCategoryById/{id}")
	public ResponseEntity<GenericResponseEntity> updateCategory(
			@PathVariable Long id,
			@RequestBody Category updatedCategory) 
					throws DataValidationException {
		GenericResponseEntity response = categoryService.updateCategory(id, updatedCategory);
				return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

	}

	// get Category  By Id

	@GetMapping("/getCategoryById/{id}")
	public Category getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}

	// get All Categories

	@GetMapping("/getAllCategories")
	public List<Category> getAllCategories(Category category) {
		return categoryService.getAllCategories();

	}

	// get All Categories By Pagination and Search

	@GetMapping("/getAllCategoryByPage")
	public ResponseEntity<Page<Category>> getAllCategoryByPage(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(required = false) String search) {

		Page<Category> category = categoryService.getAllCategoryByPage(page, size, search);
		
		

		return ResponseEntity.ok(category);

	}
}


