package com.tkouleris.funquizzler.service;

import com.tkouleris.funquizzler.dao.ScoreRepository;
import com.tkouleris.funquizzler.dto.LeaderboardUser;
import com.tkouleris.funquizzler.model.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ScoreServiceTest {

    protected ScoreRepository scoreRepository = Mockito.mock(ScoreRepository.class);
    protected ScoreService scoreService;

    @BeforeEach
    void initUseCase() {
        scoreService = new ScoreService(scoreRepository);
    }

    @Test
    void should_return_four_players()
    {
        List<String> leaderboard = new ArrayList<>();
        leaderboard.add("bob,30");
        leaderboard.add("jack,20");
        leaderboard.add("jenny,10");
        leaderboard.add("john,10");
        Mockito.when(scoreRepository.getLeaderboards()).thenReturn(leaderboard);

        List<LeaderboardUser> leaderboardUsers = scoreService.getLeaderboards();

        Assertions.assertEquals(4,leaderboardUsers.size());
        Assertions.assertEquals(30,leaderboardUsers.get(0).getScore());
        Assertions.assertEquals(20,leaderboardUsers.get(1).getScore());
    }

}
