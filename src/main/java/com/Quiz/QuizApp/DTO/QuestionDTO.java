package com.Quiz.QuizApp.DTO;

import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {

	private String questionName;
	
	private List<String> quizes = new ArrayList<>();

	public QuestionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public List<String> getQuizes() {
		return quizes;
	}

	public void setQuizes(List<String> quizes) {
		this.quizes = quizes;
	}

	
}
