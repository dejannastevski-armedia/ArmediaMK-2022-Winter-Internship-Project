package com.example.demo.service;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.model.Answer;

import java.util.ArrayList;

public interface AnswerService
{
    boolean checkAnswer(String answer);

    String validateAnswer(String answer);

    void saveAnswer(Answer answer);

    ArrayList<String> createAnswer(AnswerDTO answerDTO);
}
