package com.tkouleris.funquizzler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkouleris.funquizzler.dao.QuizRepository;
import com.tkouleris.funquizzler.model.Quiz;

@Service
public class QuizService {
	@Autowired
	private QuizRepository R_quiz;
	
	public Quiz create(Quiz newQuiz)
	{
		if( titleIsValid(newQuiz.getTitle()) == false)
		{
			throw new IllegalArgumentException("Your quiz must have a title");
		}						
		R_quiz.save(newQuiz);		
		return newQuiz;
	}
	
	private Boolean titleIsValid(String quizTitle)
	{
		return quizTitle != null && !quizTitle.isEmpty() && !quizTitle.trim().isEmpty();
	}
}
