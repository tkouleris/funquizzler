package com.tkouleris.funquizzler.dao;

import com.tkouleris.funquizzler.model.Quiz;
import com.tkouleris.funquizzler.model.Score;
import com.tkouleris.funquizzler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findByQuizAndUser(Quiz quiz, User user);

    @Query( value="SELECT username, SUM(score.score)  FROM score " +
            "LEFT JOIN user ON(user.id = score.user_id) GROUP BY username",nativeQuery = true )
    List<String> getLeaderboards();
}
