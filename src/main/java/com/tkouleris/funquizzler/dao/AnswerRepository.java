package com.tkouleris.funquizzler.dao;

import com.tkouleris.funquizzler.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
