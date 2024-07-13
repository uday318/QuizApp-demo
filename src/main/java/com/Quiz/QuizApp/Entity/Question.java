package com.Quiz.QuizApp.Entity;

import com.Quiz.QuizApp.Enum.CorrectAnswer;
import com.Quiz.QuizApp.Enum.Level;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String name;
	
	@Column(unique = true)
    @NotNull
	private String code;
	
	@Enumerated(EnumType.STRING)
	private Level level;
	
	@ManyToOne
	private Category category;
	
	
	private Boolean isActive;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	
	@Enumerated(EnumType.STRING)
	private CorrectAnswer correctAnswer;

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(Long id,String name, String code, Level level, Category category, Boolean isActive, String optionA, String optionB,
			String optionC, String optionD, CorrectAnswer correctAnswer) {
		super();
		this.id = id;
		this.name =name;
		this.code = code;
		this.level = level;
		this.category = category;
		this.isActive = isActive;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.correctAnswer = correctAnswer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setNmae(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(CorrectAnswer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", name=" + name + ", code=" + code + ", level=" + level + ", category="
				+ category + ", isActive=" + isActive + ", OptionA=" + optionA + ", optionB=" + optionB + ", optionC="
				+ optionC + ", optionD=" + optionD + ", correctAnswer=" + correctAnswer + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	

	
}
