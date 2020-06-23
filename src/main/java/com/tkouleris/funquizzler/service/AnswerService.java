package com.tkouleris.funquizzler.service;

import com.tkouleris.funquizzler.dao.AnswerRepository;
import com.tkouleris.funquizzler.dao.QuestionRepository;
import com.tkouleris.funquizzler.model.Answer;
import com.tkouleris.funquizzler.model.Question;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
public class AnswerService {

    protected QuestionRepository R_question;
    protected AnswerRepository R_answer;

    public AnswerService(QuestionRepository questionRepository, AnswerRepository answerRepository)
    {
        this.R_question = questionRepository;
        this.R_answer = answerRepository;
    }

    public Answer createAnswer(long question_id, Answer answer)
    {
        Question question = R_question.findById(question_id).orElse(null);
        if(question == null)
        {
            throw new PersistenceException("Question not found");
        }

        answer.setQuestion(question);
        return R_answer.save(answer);
    }

    public List<Answer> listByQuestion(long question_id)
    {
        return R_answer.findByQuestionId(question_id);
    }
}
