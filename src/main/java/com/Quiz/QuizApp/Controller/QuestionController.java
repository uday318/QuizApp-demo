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

import com.Quiz.QuizApp.DTO.QuestionDTO;
import com.Quiz.QuizApp.DTO.ResultDTO;
import com.Quiz.QuizApp.Entity.GenericResponseEntity;
import com.Quiz.QuizApp.Entity.Question;
import com.Quiz.QuizApp.Enum.Level;
import com.Quiz.QuizApp.Error.DataValidationException;
import com.Quiz.QuizApp.Service.QuestionService;

@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	// Add Question

	@PostMapping("/addQuestion")
	public ResponseEntity<GenericResponseEntity> saveQuestion(@RequestBody Question question)
			throws DataValidationException {

		GenericResponseEntity response = questionService.saveQuestion(question);
		return ResponseEntity.status(response.getCode()).body(response);

	}

	// Update Question By id

	@PutMapping("/updateQuestionById/{id}")
	public ResponseEntity<GenericResponseEntity> updateQuestion(@PathVariable Long id,
			@RequestBody Question updatedQuestion) {
		GenericResponseEntity response = questionService.updateQuestion(id, updatedQuestion);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

	}

	// get Question By Id

	@GetMapping("/getQuestionById/{id}")
	public Question getQuestionById(@PathVariable Long id) {
		return questionService.getQuestionById(id);
	}
	
	
	//get Questions by category
	
	@GetMapping("/getQuestionsByCategory/{categoryId}")
	public List<Question> getQuestionsByCategory(@PathVariable Long categoryId) {
		return questionService.getQuestionsByCategory(categoryId);
	}
	
	

	//get Questions by category and level
	
	@GetMapping("/getQuestionsByCategoryAndLevel/{categoryId}")
	public List<Question> getQuestionsByCategoryAndLevel(
			@PathVariable Long categoryId ,
			@RequestParam(required = false) Level level) {
		return questionService.getQuestionsByCategoryAndLevel(categoryId,level);
	}
	
	

	// get All Categories

	@GetMapping("/getAllQuestions")
	public List<Question> getAllQuestions(Question question) {
		return questionService.getAllQuestions();

	}

	// get All Categories By Pagination and Search

	@GetMapping("/getAllQuestionsByPage")
	public ResponseEntity<Page<Question>> getAllQuestionsByPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String search) {

		Page<Question> question = questionService.getAllQuestionsByPage(page, size, search);

		return ResponseEntity.ok(question);

	}
	
	//get question name and check the question used in which quizs
	
	@GetMapping("/getQuestions")
	public List<QuestionDTO> getQuestionsWithQuiz() {
		
	return questionService.getQuestionsWithQuiz();
	 
	}

}
