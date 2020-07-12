package com.tkouleris.funquizzler.controller;

import com.tkouleris.funquizzler.model.Answer;
import com.tkouleris.funquizzler.response.ApiResponse;
import com.tkouleris.funquizzler.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/funquizzler")
public class AnswerController {

    protected AnswerService answerService;
    protected ApiResponse apiResponse;

    public AnswerController(AnswerService answerService, ApiResponse apiResponse) {
        this.answerService = answerService;
        this.apiResponse = apiResponse;
    }


    @PostMapping(path = "/question/{question_id}/answer/create", produces = "application/json")
    public ResponseEntity<Object> createAnswer(@RequestBody Answer answer, @PathVariable long question_id) {
        Answer createdAnswer = answerService.createAnswer(question_id, answer);
        apiResponse.setData(createdAnswer);
        apiResponse.setMessage("Answer created");

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.CREATED);
    }

    @GetMapping(path = "/question/{question_id}/answer", produces = "application/json")
    public ResponseEntity<Object> listAnswerByQuestion(@PathVariable long question_id) {
        List<Answer> asnwer_list = answerService.listByQuestion(question_id);
        apiResponse.setData(asnwer_list);
        apiResponse.setMessage("Answer List");

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }
}
