package com.tkouleris.funquizzler.service;

import com.tkouleris.funquizzler.dao.AnswerRepository;
import com.tkouleris.funquizzler.dao.QuestionRepository;
import com.tkouleris.funquizzler.dao.QuizRepository;
import com.tkouleris.funquizzler.dto.Request.GameAnswerRequest;
import com.tkouleris.funquizzler.dto.Request.GameRequest;
import com.tkouleris.funquizzler.dto.Request.GameResponse;
import com.tkouleris.funquizzler.model.Quiz;
import com.tkouleris.funquizzler.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    protected GameService gameService;
    protected QuizRepository quizRepository = Mockito.mock(QuizRepository.class);
    protected QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
    protected AnswerRepository answerRepository = Mockito.mock(AnswerRepository.class);
    protected GameResponse gameResponse = new GameResponse();
    protected ScoreService scoreService = Mockito.mock(ScoreService.class);

    @BeforeEach
    void initUseCase() {
        gameService = new GameService(quizRepository,questionRepository,answerRepository,gameResponse,scoreService);
    }

    @Test
    void QuestionIdIsNullShouldReturnZeroScore()
    {
        // given
        GameRequest gameRequest = new GameRequest();
        gameRequest.quiz_id = 1L;
        gameRequest.answers = new ArrayList<>();
        GameAnswerRequest gameAnswerRequest_1 = new GameAnswerRequest();
        GameAnswerRequest gameAnswerRequest_2 = new GameAnswerRequest();
        gameRequest.answers.add(gameAnswerRequest_1);
        gameRequest.answers.add(gameAnswerRequest_2);

        // when
        Mockito.when(quizRepository.findById(gameRequest.quiz_id)).thenReturn(java.util.Optional.of(new Quiz()));
        GameResponse gameResponse = gameService.checkAnswers(gameRequest, new User());

        // then
        Assertions.assertEquals(0,gameResponse.correct_questions);
    }

}