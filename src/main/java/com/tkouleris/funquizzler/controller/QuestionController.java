package com.tkouleris.funquizzler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkouleris.funquizzler.model.Question;
import com.tkouleris.funquizzler.response.ApiResponse;
import com.tkouleris.funquizzler.service.QuestionService;

@Controller
@RequestMapping("/funquizzler")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private ApiResponse apiResponse;

	@PostMapping(path="/question/create/{quiz_id}", produces = "application/json")
	public ResponseEntity<Object>createQuestion(@RequestBody Question question, @PathVariable long quiz_id)
	{
		Question created_question = questionService.createQuestion(quiz_id, question);
		apiResponse.setData(created_question );
		apiResponse.setMessage("Question created");

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.CREATED);
	}
}
