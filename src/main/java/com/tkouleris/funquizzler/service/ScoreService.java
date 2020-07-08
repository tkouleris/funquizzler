package com.tkouleris.funquizzler.service;

import com.tkouleris.funquizzler.dao.ScoreRepository;
import com.tkouleris.funquizzler.dto.LeaderboardUser;
import com.tkouleris.funquizzler.model.Quiz;
import com.tkouleris.funquizzler.model.Score;
import com.tkouleris.funquizzler.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {

    protected ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public void setQuizScoreForUser(Quiz quiz, User user, int score)
    {
        Score game_score = scoreRepository.findByQuizAndUser(quiz,user).orElse(null);
        if(game_score == null)
        {
            game_score = new Score();
        }
        game_score.setQuiz(quiz);
        game_score.setUser(user);
        game_score.setScore(score);
        scoreRepository.save(game_score);
    }

    public List<LeaderboardUser> getLeaderboards()
    {
        List<String> leaderboards = scoreRepository.getLeaderboards();
        List<LeaderboardUser> scoreTable = new ArrayList<>();
        for(String record:leaderboards)
        {
            String[] fields = record.split(",");
            LeaderboardUser leaderboardUser = new LeaderboardUser();
            leaderboardUser.setUsername(fields[0]);
            leaderboardUser.setScore(Integer.parseInt(fields[1]));
            scoreTable.add(leaderboardUser);
        }
        return scoreTable;
    }
}
