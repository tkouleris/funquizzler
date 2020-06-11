package com.tkouleris.funquizzler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkouleris.funquizzler.dao.QuizRepository;
import com.tkouleris.funquizzler.model.Quiz;

@Controller
@RequestMapping("/funquizzler")
public class QuizController {
	
	@Autowired
	private QuizRepository R_Quiz;
	
	@PostMapping(path="/quiz/create", produces = "application/json")
	public ResponseEntity<Object> createQuiz(@RequestBody Quiz newQuiz)
	{
		R_Quiz.save(newQuiz);
		
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
}
