package com.tkouleris.funquizzler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkouleris.funquizzler.dao.QuizRepository;
import com.tkouleris.funquizzler.model.Quiz;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
public class QuizService {
	
	@Autowired
	private QuizRepository R_quiz;

	public Quiz create(Quiz newQuiz)
	{
		if( !titleIsValid(newQuiz.getTitle()) )
		{
			throw new IllegalArgumentException("Your quiz must have a title");
		}						
		R_quiz.save(newQuiz);		
		return newQuiz;
	}

	public Quiz update(Quiz updatedQuiz)
	{
		long quiz_id = updatedQuiz.getId();
		Quiz currentQuiz = R_quiz.findById(quiz_id).orElse(null);
		currentQuiz.setTitle(updatedQuiz.getTitle());
		currentQuiz.setPublished_at(updatedQuiz.getPublished_at());
		R_quiz.save(currentQuiz);
		
		return currentQuiz;
	}

	public void delete(long quiz_id)
	{
		Quiz toDeleteQuiz = R_quiz.findById(quiz_id).orElse(null);
		if( toDeleteQuiz == null)
		{
			throw new PersistenceException("Quiz not found");
		}
		R_quiz.delete(toDeleteQuiz);
	}

	public List<Quiz> list()
	{
		return R_quiz.findAll();
	}

	private Boolean titleIsValid(String quizTitle)
	{
		return quizTitle != null && !quizTitle.isEmpty() && !quizTitle.trim().isEmpty();
	}
}
