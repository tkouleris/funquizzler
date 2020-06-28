package com.tkouleris.funquizzler.dto;


import java.util.ArrayList;
import java.util.List;

public class QandAResponse {
    public QuestionResponse question;
    public List<AnswerResponse> answerList = new ArrayList<>();
}
