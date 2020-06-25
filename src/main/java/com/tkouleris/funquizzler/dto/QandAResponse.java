package com.tkouleris.funquizzler.dto;

import com.tkouleris.funquizzler.model.Answer;
import com.tkouleris.funquizzler.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QandAResponse {
    public Question question;
    public List<Answer> answerList = new ArrayList<>();
}
