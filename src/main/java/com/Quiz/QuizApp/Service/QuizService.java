package com.Quiz.QuizApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Quiz.QuizApp.Entity.Category;
import com.Quiz.QuizApp.Entity.GenericResponseEntity;
import com.Quiz.QuizApp.Entity.Question;
import com.Quiz.QuizApp.Entity.Quiz;
import com.Quiz.QuizApp.Entity.QuizData;
import com.Quiz.QuizApp.Entity.Result;
import com.Quiz.QuizApp.Error.DataValidationException;
import com.Quiz.QuizApp.Repository.CategoryRepo;
import com.Quiz.QuizApp.Repository.QuestionRepo;
import com.Quiz.QuizApp.Repository.QuizRepo;
import com.Quiz.QuizApp.Repository.ResultRepo;

@Service
public class QuizService {

	@Autowired
	private QuizRepo quizRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private QuestionRepo questionRepo;

	@Autowired
	private ResultRepo resultRepo;

	// create quiz
	public GenericResponseEntity createQuiz(Quiz quiz) {
		
		if(quiz.getName() == null || quiz.getName().isBlank()) {
			throw new DataValidationException("Quiz Name is Not null");
		}
		
		if(quiz.getLevel() == null) {
			throw new DataValidationException("level is not null");
		}
		
		if (quiz.getIsActive() == null) {
			throw new DataValidationException("IsActvie is not null");
		}

		if (quiz.getQuizDatas() == null) {
			throw new DataValidationException("Quiz data required");
		}

		if (quiz.getQuizDatas().size() == 0) {
			throw new DataValidationException("Quiz must have Category and Question");
		}

		for (QuizData data : quiz.getQuizDatas()) {
			
	
			
			if (data.getCategory().getId() == null) {
				throw new DataValidationException("Category field is not null");
			}

			System.out.println("data.getCategory().getId() : " + data.getCategory().getId());
			Optional<Category> categoryOptional = categoryRepo.findById(data.getCategory().getId());
			if (categoryOptional.isEmpty()) {
				throw new DataValidationException("Category not Available");
			}

			Category categoryDb = categoryOptional.get();

			for (Question question : data.getQuestions()) {
				System.out.println("question.getId() : " + question.getId());

				if (question.getId() == null) {
					throw new DataValidationException("Question Field Not null");
				}

				Optional<Question> questionOptional = questionRepo.findById(question.getId());
				
				if (questionOptional.isEmpty()) {
					throw new DataValidationException("Question not Available");
				}

				Question questionDb = questionOptional.get();

				if (questionDb.getCategory().getId() != categoryDb.getId()) {
					throw new DataValidationException("Question Category mismatch");
				}
				
				if(questionDb.getLevel() != quiz.getLevel()) {
					throw new DataValidationException("Question Level mismatch");
				}
			}

		}

		quizRepo.save(quiz);
		return new GenericResponseEntity(200, "Quiz Created successfully");
	}

	// get quiz by id
	public Quiz getQuizById(Long id) {
		Optional<Quiz> quiz = quizRepo.findById(id);

		if (!quiz.isPresent()) {
			throw new DataValidationException("Quiz Not Available");
		}
		return quiz.get();
	}

	public List<Quiz> getAllQuiz() {
		return quizRepo.findByIsActiveTrue();
	}

	public Page<Quiz> getAllQuizByPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		return quizRepo.findAll(pageable);
	}

	// submit Quiz And Calculate Score

	public GenericResponseEntity calculateResult(Long id, Quiz quiz) {

		int score = 0;

		Optional<Quiz> quizOptional = quizRepo.findById(id);

		if (!quizOptional.isPresent()) {
			throw new DataValidationException("Quiz ID Not Available");
		}
		Quiz quizDb = quizOptional.get();

		if (quiz.getQuizDatas() == null) {
			throw new DataValidationException("Quiz data required");
		}

		if (quiz.getQuizDatas().size() == 0) {
			throw new DataValidationException("Quiz must have Category and Question");
		}

		for (QuizData data : quiz.getQuizDatas()) {

			for (Question question : data.getQuestions()) {

				if (question.getId() == null) {
					throw new DataValidationException("Question Field Not null");
				}

				Optional<Question> questionOptional = questionRepo.findById(question.getId());

				if (questionOptional.isEmpty()) {
					throw new DataValidationException("Question not Available");
				}

				Question questionDb = questionOptional.get();

				Optional<Category> categoryOptional = categoryRepo.findById(data.getCategory().getId());

				Category categoryDb = categoryOptional.get();

				if (questionDb.getCategory().getId() != categoryDb.getId()) {
					throw new DataValidationException("Question Category mismatch");
				}

				if (questionDb.getCorrectAnswer().equals(question.getCorrectAnswer())) {
					score++;
				}

			}

		}
		System.out.println(+score);
		Result result = new Result();
		result.setQuiz(quizDb);
		result.setScore((long) score);

		resultRepo.save(result);

		return new GenericResponseEntity(200, "Quiz Submitted successfully");
	}
}
