package com.tkouleris.funquizzler.service;

import com.tkouleris.funquizzler.dao.AnswerRepository;
import com.tkouleris.funquizzler.dao.QuestionRepository;
import com.tkouleris.funquizzler.dto.QandAResponse;
import com.tkouleris.funquizzler.dto.QuizResponse;
import com.tkouleris.funquizzler.model.Answer;
import com.tkouleris.funquizzler.model.Question;
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
	@Autowired
	private QuestionRepository R_Question;
	@Autowired
	AnswerRepository R_Answer;

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
		if(updatedQuiz.getTitle() == null)
		{
			updatedQuiz.setTitle("");
		}
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

	public QuizResponse get_full_quiz(long quiz_id)
	{
		List<Question> questions = R_Question.findByQuizId(quiz_id);

		QuizResponse quizResponse = new QuizResponse();
		for(Question question: questions)
		{
			QandAResponse qandaresponse = new QandAResponse();
			qandaresponse.question = question;
			long question_id = question.getId();
			List<Answer> answer_list = R_Answer.findByQuestionId(question_id);

			for(Answer answer: answer_list)
			{
				Answer new_answer = new Answer();
				new_answer.setId(answer.getId());
				new_answer.setAnswer(answer.getAnswer());
				new_answer.setCorrect(answer.getCorrect());
				qandaresponse.answerList.add(new_answer);
			}
			quizResponse.qandaResponse.add(qandaresponse);
		}
		return quizResponse;
	}

	private Boolean titleIsValid(String quizTitle)
	{
		return quizTitle != null && !quizTitle.isEmpty() && !quizTitle.trim().isEmpty();
	}
}
