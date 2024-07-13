package com.Quiz.QuizApp.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long score;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Quiz quiz;
	
	
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result(Long id,Long score) {
		super();
		this.id = id;
		this.score = score;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Result [id=" + id + ",score=" + score + "]";
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	
}
