package com.tkouleris.funquizzler.service;

import com.tkouleris.funquizzler.dao.AnswerRepository;
import com.tkouleris.funquizzler.dto.Request.GameAnswerRequest;
import com.tkouleris.funquizzler.dto.Request.GameRequest;
import com.tkouleris.funquizzler.dto.Request.GameResponse;
import com.tkouleris.funquizzler.model.Answer;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    protected AnswerRepository answerRepository;
    protected GameResponse gameResponse;

    public GameService(AnswerRepository answerRepository, GameResponse gameResponse)
    {
        this.answerRepository = answerRepository;
        this.gameResponse = gameResponse;
    }

    public GameResponse checkAnswers(GameRequest gameRequest)
    {
        int questions_answered = 0;
        int correct_answered = 0;
        for(GameAnswerRequest answer: gameRequest.answers)
        {
            Answer selected_correct_answer = answerRepository.findById(answer.correct_answer_id).orElse(null);
            if(selected_correct_answer == null)
            {
                continue;
            }
            questions_answered++;
            if(selected_correct_answer.getCorrect())
            {
                correct_answered++;
                System.out.println("Your Answer is correct");
            }
        }
        gameResponse.questions_answered = questions_answered;
        gameResponse.correct_questions = correct_answered;

        return gameResponse;
    }
}
