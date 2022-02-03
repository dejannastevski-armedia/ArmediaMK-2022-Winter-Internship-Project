package com.example.demo.service;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.dto.UserAnswerDTO;
import com.example.demo.model.Answer;

import java.util.List;

public interface AnswerService
{

    List<Answer> listAllAnswers();

    String createAnswer(AnswerDTO answerDTO);

    List<Answer> listAllAnswersPerQuestion(Long id);

    void upVote(UserAnswerDTO userAnswerDTO);

    void downVote(UserAnswerDTO userAnswerDTO);

}
