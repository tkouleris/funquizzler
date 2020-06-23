package com.tkouleris.funquizzler.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tkouleris.funquizzler.model.Question;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    List<Question> findByQuizId(long quiz_id);
}
