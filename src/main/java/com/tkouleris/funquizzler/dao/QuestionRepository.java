package com.tkouleris.funquizzler.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tkouleris.funquizzler.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}
