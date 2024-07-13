package com.Quiz.QuizApp.DTO;

import java.util.ArrayList;
import java.util.List;

public class ResultDTO {
	
	private String quiz_name;
	
	private List<Long> score = new ArrayList<>();

	public String getQuiz_name() {
		return quiz_name;
	}

	public void setQuiz_name(String quiz_name) {
		this.quiz_name = quiz_name;
	}

	public List<Long> getScore() {
		return score;
	}

	public void setScore(List<Long> score) {
		this.score = score;
	}

	public ResultDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
