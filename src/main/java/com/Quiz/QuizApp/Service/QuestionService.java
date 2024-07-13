package com.Quiz.QuizApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Quiz.QuizApp.DTO.QuestionDTO;
import com.Quiz.QuizApp.Entity.GenericResponseEntity;
import com.Quiz.QuizApp.Entity.Question;
import com.Quiz.QuizApp.Entity.Quiz;
import com.Quiz.QuizApp.Entity.QuizData;
import com.Quiz.QuizApp.Enum.Level;
import com.Quiz.QuizApp.Error.DataValidationException;
import com.Quiz.QuizApp.Repository.QuestionRepo;
import com.Quiz.QuizApp.Repository.QuizRepo;

import jakarta.validation.Valid;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepo questionRepo;

	@Autowired
	private QuizRepo quizRepo;

	public GenericResponseEntity saveQuestion(@Valid Question question) {

		if (question.getName() == null || question.getName().isBlank()) {
			throw new DataValidationException("Name Should Not Null or Blank");
		}

		question.setName(question.getName().trim());

		if (question.getCode() == null || question.getCode().isBlank()) {
			throw new DataValidationException("Code Should not null or Blank");
		}

		question.setCode(question.getCode().trim());

		if (question.getLevel() == null) {
			throw new DataValidationException("Level Should not null");
		}

		if (question.getCategory() == null) {
			throw new DataValidationException("Category Should not null");
		}

		if (question.getIsActive() == null) {
			throw new DataValidationException("IsActive should not null");
		}

		if (question.getOptionB() == null || question.getOptionB().isBlank()) {
			throw new DataValidationException("option B should not null or empty");
		}

		if (question.getOptionA() == null || question.getOptionA().isBlank()) {
			throw new DataValidationException("option A should not null or empty");
		}

		if (question.getOptionC() == null || question.getOptionC().isBlank()) {
			throw new DataValidationException("option C should not null or empty");
		}

		if (question.getOptionD() == null || question.getOptionD().isBlank()) {
			throw new DataValidationException("option D should not null or empty");
		}

		if (question.getCorrectAnswer() == null) {
			throw new DataValidationException("CorrectAnswer sholud not null");
		}

		questionRepo.save(question);

		return new GenericResponseEntity(200, "Question saved successfully");

	}

	// update Question By ID
	public GenericResponseEntity updateQuestion(Long id, Question updatedQuestion) {

		Optional<Question> questionOptional = questionRepo.findById(id);

		if (!questionOptional.isPresent()) {
			throw new DataValidationException("Question not Available");
		}
		Question question = questionOptional.get();

		if (!question.getName().equalsIgnoreCase(updatedQuestion.getName())) {

			if (questionRepo.existsByNameIgnoreCase(updatedQuestion.getName())) {
				throw new DataValidationException("Question Already Exists");
			}
		}

		// Mandatory fields

		if (question.getCategory() == null) {
			throw new DataValidationException("Category Should not null");
		}

		if (question.getIsActive() == null) {
			throw new DataValidationException("IsActive should not null");
		}

		if (question.getOptionB() == null || question.getOptionB().isBlank()) {
			throw new DataValidationException("option B should not null or empty");
		}

		if (question.getOptionA() == null || question.getOptionA().isBlank()) {
			throw new DataValidationException("option A should not null or empty");
		}

		if (question.getOptionC() == null || question.getOptionC().isBlank()) {
			throw new DataValidationException("option C should not null or empty");
		}

		if (question.getOptionD() == null || question.getOptionD().isBlank()) {
			throw new DataValidationException("option D should not null or empty");
		}

		if (question.getCorrectAnswer() == null) {
			throw new DataValidationException("CorrectAnswer sholud not null");
		}

		if (updatedQuestion.getOptionA().equalsIgnoreCase(updatedQuestion.getOptionB())) {
			throw new DataValidationException("same fields");
		}
		if (updatedQuestion.getOptionB().equalsIgnoreCase(updatedQuestion.getOptionC())) {
			throw new DataValidationException("same fields");
		}
		if (updatedQuestion.getOptionC().equalsIgnoreCase(updatedQuestion.getOptionD())) {
			throw new DataValidationException("same fields");
		}
		if (updatedQuestion.getOptionD().equalsIgnoreCase(updatedQuestion.getOptionA())) {
			throw new DataValidationException("same fields");
		}
		if (updatedQuestion.getOptionB().equalsIgnoreCase(updatedQuestion.getOptionD())) {
			throw new DataValidationException("same fields");
		}
		if (updatedQuestion.getOptionA().equalsIgnoreCase(updatedQuestion.getOptionC())) {
			throw new DataValidationException("same fields");
		}

		// check when we update question level it already used in any quiz

		if (!question.getLevel().equals(updatedQuestion.getLevel())) {
			if (quizRepo.existsByQuizDatasQuestionsId(id)) {
				throw new DataValidationException("Question Already used in Quiz it cannot change level");
			}
		}

		if (!question.getCategory().equals(updatedQuestion.getCategory())) {
			if (quizRepo.existsByQuizDatasQuestionsId(id)) {
				throw new DataValidationException("Question Already used in Quiz it cannot change Category");
			}
		}

		question.setName(updatedQuestion.getName());
		question.setLevel(updatedQuestion.getLevel());
		question.setCategory(updatedQuestion.getCategory());
		question.setIsActive(updatedQuestion.getIsActive());
		question.setOptionA(updatedQuestion.getOptionA());
		question.setOptionB(updatedQuestion.getOptionB());
		question.setOptionC(updatedQuestion.getOptionC());
		question.setOptionD(updatedQuestion.getOptionD());

		questionRepo.save(question);
		return new GenericResponseEntity(202, "Question Updated successfully");
	}

	// get Question by Id
	public Question getQuestionById(Long id) {
		Optional<Question> question = questionRepo.findById(id);

		if (!question.isPresent()) {
			throw new DataValidationException("Question Not Available");
		}
		return question.get();
	}

	// get All Questions
	public List<Question> getAllQuestions() {
		return questionRepo.findByIsActiveTrue();
	}

	public Page<Question> getAllQuestionsByPage(int page, int size, String search) {
		Pageable pageable = PageRequest.of(page, size);

		if (search != null && !search.isBlank()) {
			return questionRepo.findByNameContainingIgnoreCase(search, pageable);
		}

		return questionRepo.findAll(pageable);
	}

	// get questions by category
	public List<Question> getQuestionsByCategory(Long categoryId) {

		return questionRepo.findByCategoryId(categoryId);
	}

	// get questions by category and level

	public List<Question> getQuestionsByCategoryAndLevel(Long categoryId, Level level) {

		if (level == null) {
			return questionRepo.findByCategoryId(categoryId);
		}

		return questionRepo.findByCategoryIdAndLevel(categoryId, level);
	}

	// get question name and check the question used in which quizs

	public List<QuestionDTO> getQuestionsWithQuiz() {

		List<Question> questionList = questionRepo.findAll();

		List<QuestionDTO> questionDTOs = new ArrayList<>();

		for (Question question : questionList) {

			QuestionDTO questionDTO = new QuestionDTO();


			List<Quiz> quizList = quizRepo.findAllByQuizDatasQuestions(question);
			
			List<String> quizNames = new ArrayList<String>();
			for (Quiz quiz : quizList) {
				quizNames.add(quiz.getName());
			}
			
			questionDTO.setQuestionName(question.getName());
			questionDTO.setQuizes(quizNames);
			
			questionDTOs.add(questionDTO);
			
			
		}

		return questionDTOs;
	}

}
