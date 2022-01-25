package com.example.demo.service;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.model.Question;

public interface QuestionService
{
    boolean checkTitle(String title);

    boolean checkQuestion(String question);

    void saveQuestion(Question question);

    String validateQuestion(String question, String title);

    String createQuestion(QuestionDTO questionDTO);
}
