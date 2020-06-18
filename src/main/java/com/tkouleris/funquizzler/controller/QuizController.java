package com.tkouleris.funquizzler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
		
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.CREATED);
	}
	
	@PutMapping(path="/quiz/update", produces = "application/json")
	public ResponseEntity<Object> updateQuiz(@RequestBody Quiz updatedQuiz)
	{
		Quiz updated_quiz = QuizService.update(updatedQuiz);
		apiResponse.setMessage("Quiz updated!");
		apiResponse.setData(updated_quiz);
		
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}

	@DeleteMapping(path="/quiz/delete/{quiz_id}")
	public ResponseEntity<Object> deleteQuiz(@PathVariable long quiz_id)
	{
		QuizService.delete(quiz_id);
		apiResponse.setMessage("Quiz deleted");
		apiResponse.setData(null);
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.NO_CONTENT);
	}
}
