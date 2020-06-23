package com.tkouleris.funquizzler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.tkouleris.funquizzler.model.Question;
import com.tkouleris.funquizzler.response.ApiResponse;
import com.tkouleris.funquizzler.service.QuestionService;

import java.util.List;

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

	@GetMapping(path="question/list/{quiz_id}" ,produces = "application/json")
	public ResponseEntity<Object> listQuestionsByQuiz(@PathVariable long quiz_id)
	{
		List<Question> question_list = questionService.listQuestionByQuiz(quiz_id);
		apiResponse.setData(question_list);
		apiResponse.setMessage("Quiz List");

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
}
