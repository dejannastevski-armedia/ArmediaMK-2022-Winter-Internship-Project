package com.example.demo.service;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.model.Question;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService
{
    boolean checkTitle(String title);

    boolean checkQuestion(String question);

    String validateQuestionAndTitle(QuestionDTO questionDTO);

    void saveQuestion(Question question);

    void createQuestion(QuestionDTO questionDTO);

    List<Question> listAll();

    String deleteQuestion(QuestionDTO questionDTO);

}
