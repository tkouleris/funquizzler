package com.tkouleris.funquizzler.dao;

import com.tkouleris.funquizzler.model.Quiz;
import com.tkouleris.funquizzler.model.Score;
import com.tkouleris.funquizzler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findByQuizAndUser(Quiz quiz, User user);
}
