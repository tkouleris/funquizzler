package com.tkouleris.funquizzler.controller;

import com.tkouleris.funquizzler.model.Answer;
import com.tkouleris.funquizzler.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funquizzler")
public class AnswerController {

    protected AnswerService answerService;

    public AnswerController(AnswerService answerService)
    {
        this.answerService = answerService;
    }


    @PostMapping(path = "/question/{question_id}/answer/create", produces = "application/json" )
    public ResponseEntity<Object> createAnswer(@RequestBody Answer answer, @PathVariable long question_id)
    {
        answerService.createAnswer(question_id, answer);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
