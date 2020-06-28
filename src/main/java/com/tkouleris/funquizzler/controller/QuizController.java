package com.tkouleris.funquizzler.controller;

import com.tkouleris.funquizzler.dao.AnswerRepository;
import com.tkouleris.funquizzler.dao.QuestionRepository;
import com.tkouleris.funquizzler.dto.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private QuizService QuizService;
	@Autowired
	private ApiResponse apiResponse;
	@Autowired
	private QuestionRepository R_Question;
	@Autowired
	private AnswerRepository R_Answer;

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

	@GetMapping(path="/quiz/list")
	public ResponseEntity<Object> list()
	{
		List<Quiz> quiz_list = QuizService.list();
		apiResponse.setMessage("Quiz list");
		apiResponse.setData(quiz_list);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}

	@GetMapping(path="/quiz/{quiz_id}/full", produces="application/json")
	public ResponseEntity<Object> getFullQuiz(@PathVariable long quiz_id)
	{
		QuizResponse quizResponse = QuizService.getFullQuiz(quiz_id);
		apiResponse.setMessage("full quiz");
		apiResponse.setData(quizResponse);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
}
