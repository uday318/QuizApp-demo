package com.Quiz.QuizApp.Error;

public class DataValidationException extends RuntimeException {
	
	public DataValidationException()
	{
		super();
	}
	

	public DataValidationException(String message)
	{
		super(message);
	}
	
}
