package com.tkouleris.funquizzler.dao;

import com.tkouleris.funquizzler.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
