package com.Quiz.QuizApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Quiz.QuizApp.DTO.ResultDTO;
import com.Quiz.QuizApp.Service.ResultService;

@RestController
public class ResultController {

	@Autowired
	private ResultService resultService;
	

	
	@GetMapping("/getScoresByQuizId/{quizId}")
	public ResponseEntity<ResultDTO> getScoresByQuizId(@PathVariable Long quizId) {
		
		ResultDTO resultDTO = resultService.getScoresByQuizId(quizId);
		return ResponseEntity.ok(resultDTO);  
	}
	
}
