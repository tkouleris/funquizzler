package com.tkouleris.funquizzler.controller;

import com.tkouleris.funquizzler.dto.Request.GameRequest;
import com.tkouleris.funquizzler.dto.Request.GameResponse;
import com.tkouleris.funquizzler.response.ApiResponse;
import com.tkouleris.funquizzler.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funquizzler")
public class GameController {

    protected GameService gameService;
    protected ApiResponse apiResponse;

    public GameController(GameService gameService, ApiResponse apiResponse)
    {
        this.gameService = gameService;
        this.apiResponse = apiResponse;
    }

    @PostMapping(path="/game/result", produces = "application/json")
    public ResponseEntity<Object> getGameResult(@RequestBody GameRequest gameRequest)
    {
        GameResponse gameResponse = gameService.checkAnswers(gameRequest);
        apiResponse.setData(gameResponse);
        apiResponse.setMessage("results");

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }
}
