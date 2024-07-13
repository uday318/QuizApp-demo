package com.Quiz.QuizApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Quiz.QuizApp.Entity.GenericResponseEntity;
import com.Quiz.QuizApp.Entity.Quiz;
import com.Quiz.QuizApp.Service.QuizService;

@RestController
public class QuizController {

	@Autowired
	private QuizService quizService;

	// create quiz with category and questions
	@PostMapping("/createQuiz")
	public ResponseEntity<GenericResponseEntity> createQuiz(@RequestBody Quiz quiz) {
		
		
		GenericResponseEntity response = quizService.createQuiz(quiz);
		return ResponseEntity.status(response.getCode()).body(response);

	}

	// get quiz by id
	@GetMapping("/getQuizById/{id}")
	public Quiz getQuizById(@PathVariable Long id) {
		return quizService.getQuizById(id);
	}
	
	
	//submit quiz
	@PostMapping("/submit/{id}")
	public GenericResponseEntity submitQuiz(@PathVariable Long id,@RequestBody Quiz quiz){
	return quizService.calculateResult(id,quiz);
	}
	

	// get All Quiz

	@GetMapping("/getAllQuiz")
	public List<Quiz> getAllQuiz(Quiz quiz) {
		return quizService.getAllQuiz();

	}

	// get All Quiz By Pagination and Search

	@GetMapping("/getAllQuizByPage")
	public ResponseEntity<Page<Quiz>> getAllQuizByPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<Quiz> quiz = quizService.getAllQuizByPage(page, size);

		return ResponseEntity.ok(quiz);

	}

}
