package com.Quiz.QuizApp.Entity;

import java.util.ArrayList;
import java.util.List;

import com.Quiz.QuizApp.Enum.Level;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Boolean isActive;

	@Enumerated(EnumType.STRING)
	private Level level;

	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	private List<QuizData> quizDatas = new ArrayList<QuizData>();

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", isActive=" + isActive + ", level=" + level + ", name=" + name + ", quizDatas="
				+ quizDatas + "]";
	}

	public List<QuizData> getQuizDatas() {
		return quizDatas;
	}

	public void setQuizDatas(List<QuizData> quizDatas) {
		this.quizDatas = quizDatas;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
