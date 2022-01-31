package com.example.firstproject.services;

import com.example.firstproject.model.Answer;

import java.util.List;

public interface AnswerService
{
    String validateAnswer(String answer);
    void saveAnswer(Answer answer);
    Answer createAnswer(String answer, String email, Long questionId);

    List<Answer> listAllAnswers(Long questionId);
}
