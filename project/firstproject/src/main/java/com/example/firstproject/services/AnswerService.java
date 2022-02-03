package com.example.firstproject.services;

import com.example.firstproject.dto.IdentifierDTO;
import com.example.firstproject.model.Answer;

import java.util.List;

public interface AnswerService
{
    String validateAnswer(String answer);
    void saveAnswer(Answer answer);
    Answer createAnswer(String answer, String email, Long questionId);

    // void updateUpVotes(Long answerId);
    void updateUpVotes(IdentifierDTO identifierDTO);

    void updateDownVotes(IdentifierDTO identifierDTO);

    // void updateDownVotes(Long answerId);
    List<Answer> listAllAnswersByQuestion(Long questionId);
}
