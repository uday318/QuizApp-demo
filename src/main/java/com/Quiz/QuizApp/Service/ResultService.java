package com.Quiz.QuizApp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Quiz.QuizApp.DTO.ResultDTO;
import com.Quiz.QuizApp.Entity.Quiz;
import com.Quiz.QuizApp.Error.DataValidationException;
import com.Quiz.QuizApp.Repository.QuizRepo;
import com.Quiz.QuizApp.Repository.ResultRepo;

@Service
public class ResultService {

	@Autowired
	private ResultRepo resultRepo;

	@Autowired
	private QuizRepo quizRepo;

	public ResultDTO getScoresByQuizId(Long quizId) {

		ResultDTO resultDTO = new ResultDTO();

		Optional<Quiz> quizOptional = quizRepo.findById(quizId);

		if (!quizOptional.isPresent()) {
			throw new DataValidationException("Quiz Id  Not Available");
		}

		Quiz quizDb = quizOptional.get();

		resultDTO.setQuiz_name(quizDb.getName());

//		List<Result> resultsDb = resultRepo.findAllByQuiz(quizDb);
//		List<Long> scores = new ArrayList<Long>();
//		for (Result result : resultsDb) {
//			scores.add(result.getScore());  
//		}
//		resultDTO.setScore(scores);
		
//		List<Long> scores = resultRepo.findAllScoreByQuizId(quizId);
		
		
		resultDTO.setScore(resultRepo.findAllScoreByQuizId(quizId));

		//System.out.println(scores);

		return resultDTO;
	}
}
