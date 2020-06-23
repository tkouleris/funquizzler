package com.tkouleris.funquizzler.dao;

import com.tkouleris.funquizzler.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(long question_id);
}
