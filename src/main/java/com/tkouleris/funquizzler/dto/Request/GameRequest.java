package com.tkouleris.funquizzler.dto.Request;

import java.util.List;

public class GameRequest {
    public Long quiz_id;
    public List<GameAnswerRequest> answers;
}
