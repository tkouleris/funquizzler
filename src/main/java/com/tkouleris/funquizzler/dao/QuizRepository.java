package com.tkouleris.funquizzler.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkouleris.funquizzler.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{

}
