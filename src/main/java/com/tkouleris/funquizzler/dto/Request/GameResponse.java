package com.tkouleris.funquizzler.dto.Request;

import org.springframework.stereotype.Component;

@Component
public class GameResponse {
    public int questions_answered;
    public int correct_questions;
}
