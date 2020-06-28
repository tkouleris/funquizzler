package com.tkouleris.funquizzler.service;

import com.tkouleris.funquizzler.dao.AnswerRepository;
import com.tkouleris.funquizzler.dao.QuestionRepository;
import com.tkouleris.funquizzler.dto.Request.GameAnswerRequest;
import com.tkouleris.funquizzler.dto.Request.GameRequest;
import com.tkouleris.funquizzler.dto.Request.GameResponse;
import com.tkouleris.funquizzler.model.Answer;
import com.tkouleris.funquizzler.model.Question;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    protected QuestionRepository questionRepository;
    protected AnswerRepository answerRepository;
    protected GameResponse gameResponse;

    public GameService(
            QuestionRepository questionRepository,
            AnswerRepository answerRepository,
            GameResponse gameResponse)
    {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.gameResponse = gameResponse;
    }

    public GameResponse checkAnswers(GameRequest gameRequest)
    {
        int questions_answered = 0;
        int correct_answered = 0;
        Long[] checker = new Long[gameRequest.answers.size()];
        int index = 0;
        boolean questionWasAnsered;
        for(GameAnswerRequest answer: gameRequest.answers)
        {
            questionWasAnsered = false;
            for (Long aLong : checker) {
                if ((index > 0) && (aLong.intValue() == answer.question_id)) {
                    questionWasAnsered = true;
                    break;
                }
            }
            if(questionWasAnsered)
            {
                continue;
            }
            Question question_answered = questionRepository.findByQuiz_idAndId(gameRequest.quiz_id, answer.question_id);

            if(question_answered == null)
            {
                continue;
            }

            Answer selected_correct_answer = answerRepository.findById(answer.correct_answer_id).orElse(null);
            checker[index] = answer.question_id;
            index++;
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
