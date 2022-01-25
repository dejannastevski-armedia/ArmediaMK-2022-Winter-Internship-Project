package com.example.demo.service;

import com.example.demo.model.Question;

import org.springframework.stereotype.Service;

@Service
public interface QuestionService
{
    boolean checkTitle(String title);

    boolean checkQuestion(String question);

    String validateQuestionAndTitle(Question question);

    void saveQuestion(Question question);

    Question createQuestion(Question question);

}
