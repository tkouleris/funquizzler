package com.tkouleris.funquizzler.service;

import com.tkouleris.funquizzler.dao.AnswerRepository;
import com.tkouleris.funquizzler.dao.QuestionRepository;
import com.tkouleris.funquizzler.dao.QuizRepository;
import com.tkouleris.funquizzler.dto.Request.GameAnswerRequest;
import com.tkouleris.funquizzler.dto.Request.GameRequest;
import com.tkouleris.funquizzler.dto.Request.GameResponse;
import com.tkouleris.funquizzler.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    protected QuizRepository quizRepository;
    protected QuestionRepository questionRepository;
    protected AnswerRepository answerRepository;
    protected GameResponse gameResponse;
    protected ScoreService scoreService;

    public GameService(
            QuizRepository quizRepository,
            QuestionRepository questionRepository,
            AnswerRepository answerRepository,
            GameResponse gameResponse,
            ScoreService scoreService) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.gameResponse = gameResponse;
        this.scoreService = scoreService;
    }

    public GameResponse checkAnswers(GameRequest gameRequest, User user) {
        int questions_answered = 0;
        int correct_answered = 0;
        long[] questionsChecked = new long[gameRequest.answers.size()];
        int index = 0;
        for (GameAnswerRequest answer : gameRequest.answers) {
            if (answer.question_id == null) {
                continue;
            }
            if (questionWasAlreadyChecked(questionsChecked, answer.question_id)) {
                continue;
            }
            Question question_answered = questionRepository.findByQuiz_idAndId(gameRequest.quiz_id, answer.question_id);
            if (question_answered == null) {
                continue;
            }

            Answer selected_correct_answer = answerRepository.findById(answer.correct_answer_id).orElse(null);
            if (selected_correct_answer == null) {
                continue;
            }
            questionsChecked[index] = answer.question_id;
            index++;
            questions_answered++;
            if (selected_correct_answer.getCorrect()) {
                correct_answered++;
            }
        }
        gameResponse.questions_answered = questions_answered;
        gameResponse.correct_questions = correct_answered;

        Quiz quiz = quizRepository.findById(gameRequest.quiz_id).orElse(null);
        scoreService.setQuizScoreForUser(quiz, user, correct_answered);

        return gameResponse;
    }

    private boolean questionWasAlreadyChecked(long[] questionsChecked, long questionId) {
        for (long question : questionsChecked) {
            if (question == questionId) {
                return true;
            }
        }
        return false;
    }
}
