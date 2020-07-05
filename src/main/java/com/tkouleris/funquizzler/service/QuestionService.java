package com.tkouleris.funquizzler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkouleris.funquizzler.dao.QuestionRepository;
import com.tkouleris.funquizzler.dao.QuizRepository;
import com.tkouleris.funquizzler.model.Question;
import com.tkouleris.funquizzler.model.Quiz;

import java.util.List;

@Service
public class QuestionService {

	protected QuizRepository quizRepository;
	protected QuestionRepository questionRepository;

	public QuestionService(QuizRepository quizRepository, QuestionRepository questionRepository) {
		this.quizRepository = quizRepository;
		this.questionRepository = questionRepository;
	}

	public Question createQuestion(long quiz_id, Question question)
	{
		Quiz selectedQuiz = quizRepository.findById(quiz_id).orElse(null);
		question.setQuiz_id(selectedQuiz);
		return questionRepository.save(question);
	}

	public List<Question> listQuestionByQuiz(long quiz_id)
	{
		return questionRepository.findByQuizId(quiz_id);
	}
	
}
