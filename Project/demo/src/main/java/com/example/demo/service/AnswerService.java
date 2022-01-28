package com.example.demo.service;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.model.Answer;

import org.springframework.stereotype.Service;

@Service
public interface AnswerService
{
    boolean checkAnswer(String answer);

    String validateAnswer(AnswerDTO answerDTO);

    Answer createAnswer(AnswerDTO answerDTO);

}
