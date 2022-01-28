package com.example.firstproject.services;

import com.example.firstproject.model.Answer;

public interface AnswerService
{
    boolean checkAnswer (String answer);
    String validateAnswer(String answer);
    void saveAnswer(Answer answer);
    Answer createAnswer(String answer, String email, Long questionId);
}
