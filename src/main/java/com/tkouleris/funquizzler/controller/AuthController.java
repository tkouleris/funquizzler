package com.tkouleris.funquizzler.controller;

import com.tkouleris.funquizzler.model.User;
import com.tkouleris.funquizzler.response.ApiResponse;
import com.tkouleris.funquizzler.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funquizzler")
public class AuthController {

    private final UserService userService;
    private final ApiResponse apiResponse;

    public AuthController(UserService userService, ApiResponse apiResponse)
    {
        this.userService = userService;
        this.apiResponse = apiResponse;
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> register(@RequestBody User user) throws Exception
    {
        User newUser = userService.createNewUser(user);
        apiResponse.setMessage("User created!");
        apiResponse.setData(newUser);

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.CREATED);
    }
}
