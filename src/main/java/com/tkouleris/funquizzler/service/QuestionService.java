package com.tkouleris.funquizzler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkouleris.funquizzler.dao.QuestionRepository;
import com.tkouleris.funquizzler.dao.QuizRepository;
import com.tkouleris.funquizzler.model.Question;
import com.tkouleris.funquizzler.model.Quiz;

@Service
public class QuestionService {
	
	@Autowired
	protected QuizRepository R_Quiz;
	@Autowired
	protected QuestionRepository R_Question;
	
	public Question createQuestion(long quiz_id, Question question)
	{
		Quiz selectedQuiz = R_Quiz.findById(quiz_id).orElse(null);
		question.setQuiz_id(selectedQuiz);
		return R_Question.save(question);		
	}
	
}
