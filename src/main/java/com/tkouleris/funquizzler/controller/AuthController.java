package com.tkouleris.funquizzler.controller;

import com.tkouleris.funquizzler.Utils.JwtUtil;
import com.tkouleris.funquizzler.dto.LoginResponse;
import com.tkouleris.funquizzler.model.User;
import com.tkouleris.funquizzler.response.ApiResponse;
import com.tkouleris.funquizzler.service.CustomUserDetailsService;
import com.tkouleris.funquizzler.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funquizzler")
public class AuthController {

    private final UserService userService;
    private final ApiResponse apiResponse;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtTokenUtil;
    private final LoginResponse loginResonse;

    public AuthController(
            UserService userService,
            ApiResponse apiResponse,
            AuthenticationManager authenticationManager,
            CustomUserDetailsService userDetailsService,
            JwtUtil jwtTokenUtil,
            LoginResponse loginResonse)
    {
        this.userService = userService;
        this.apiResponse = apiResponse;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.loginResonse = loginResonse;
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> register(@RequestBody User user) throws Exception
    {
        User newUser = userService.createNewUser(user);
        apiResponse.setMessage("User created!");
        apiResponse.setData(newUser);

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> authenticateUser(@RequestBody User user)
    {
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        authenticationManager.authenticate(auth);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        loginResonse.setJwt(jwt);
        loginResonse.setUsername(user.getUsername());

        apiResponse.setMessage("Auth Token!");
        apiResponse.setData(loginResonse);

        return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
    }
}
