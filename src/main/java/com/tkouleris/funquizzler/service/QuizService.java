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
		R_quiz.save(newQuiz);
		
		return newQuiz;
	}
}
