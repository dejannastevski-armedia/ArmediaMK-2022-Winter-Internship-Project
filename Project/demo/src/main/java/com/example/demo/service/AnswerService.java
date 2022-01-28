package com.example.demo.service;

import com.example.demo.dto.AnswerDTO;

import org.springframework.stereotype.Service;

@Service
public interface AnswerService
{
    boolean checkAnswer(String answer);

    String validateAnswer(AnswerDTO answerDTO);

    void createAnswer(AnswerDTO answerDTO);

}
