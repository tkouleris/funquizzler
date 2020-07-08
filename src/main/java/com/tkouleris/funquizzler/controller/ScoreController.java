package com.tkouleris.funquizzler.controller;

import com.tkouleris.funquizzler.response.ApiResponse;
import com.tkouleris.funquizzler.service.ScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funquizzler")
public class ScoreController {

    private final ScoreService scoreService;
    private final ApiResponse apiResponse;
    public ScoreController(ScoreService scoreService, ApiResponse apiResponse) {
        this.scoreService = scoreService;
        this.apiResponse = apiResponse;
    }

    @GetMapping(path = "/leaderboards", produces = "application/json")
    public ResponseEntity<Object> getLeaderboards()
    {
        apiResponse.setData(this.scoreService.getLeaderboards());
        apiResponse.setMessage("Leaderboards");

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }
}
