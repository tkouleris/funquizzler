package com.tkouleris.funquizzler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkouleris.funquizzler.model.Quiz;
import com.tkouleris.funquizzler.response.ApiResponse;
import com.tkouleris.funquizzler.service.QuizService;

@Controller
@RequestMapping("/funquizzler")
public class QuizController {
	
	@Autowired
	private QuizService QuizService;
	@Autowired
	private ApiResponse apiResponse;
	
	@PostMapping(path="/quiz/create", produces = "application/json")
	public ResponseEntity<Object> createQuiz(@RequestBody Quiz newQuiz)
	{
		Quiz created_quiz = QuizService.create(newQuiz);
		apiResponse.setMessage("New quiz created!");
		apiResponse.setData(created_quiz);
		
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
}
