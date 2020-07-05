package com.tkouleris.funquizzler.controller;

import com.tkouleris.funquizzler.dto.QuizResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.tkouleris.funquizzler.model.Quiz;
import com.tkouleris.funquizzler.response.ApiResponse;
import com.tkouleris.funquizzler.service.QuizService;

import java.util.List;

@Controller
@RequestMapping("/funquizzler")
public class QuizController {

	private final QuizService quizService;
	private final ApiResponse apiResponse;

	public QuizController( QuizService quizService, ApiResponse apiResponse )
	{
		this.quizService = quizService;
		this.apiResponse = apiResponse;
	}

	@PostMapping(path="/quiz/create", produces = "application/json")
	public ResponseEntity<Object> createQuiz(@RequestBody Quiz newQuiz)
	{
		Quiz created_quiz = quizService.create(newQuiz);
		apiResponse.setMessage("New quiz created!");
		apiResponse.setData(created_quiz);
		
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.CREATED);
	}
	
	@PutMapping(path="/quiz/update", produces = "application/json")
	public ResponseEntity<Object> updateQuiz(@RequestBody Quiz updatedQuiz)
	{
		Quiz updated_quiz = quizService.update(updatedQuiz);
		apiResponse.setMessage("Quiz updated!");
		apiResponse.setData(updated_quiz);
		
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}

	@DeleteMapping(path="/quiz/delete/{quiz_id}")
	public ResponseEntity<Object> deleteQuiz(@PathVariable long quiz_id)
	{
		quizService.delete(quiz_id);
		apiResponse.setMessage("Quiz deleted");
		apiResponse.setData(null);
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.NO_CONTENT);
	}

	@GetMapping(path="/quiz/list")
	public ResponseEntity<Object> list()
	{
		List<Quiz> quiz_list = quizService.list();
		apiResponse.setMessage("Quiz list");
		apiResponse.setData(quiz_list);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}

	@GetMapping(path="/quiz/{quiz_id}/full", produces="application/json")
	public ResponseEntity<Object> getFullQuiz(@PathVariable long quiz_id)
	{
		QuizResponse quizResponse = quizService.getFullQuiz(quiz_id);
		apiResponse.setMessage("full quiz");
		apiResponse.setData(quizResponse);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
}
