package com.example.firstproject.services;

import com.example.firstproject.model.Answer;
import com.example.firstproject.repository.AnswerRepository;
import com.example.firstproject.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerServiceImpl implements AnswerService
{
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public boolean checkAnswer(String answer)
    {
        if(answer != null && answer.length() != 0)
            return true;
        return false;
    }

    @Override
    public String validateAnswer(String answer)
    {
        StringBuilder sb = new StringBuilder();
        if(checkAnswer(answer) == false)
        {
            sb.append("Answer Field Blank!");
        }
        return sb.toString();
    }

    @Override
    public void saveAnswer(Answer answer)
    {
        answerRepository.save(answer);
    }

    @Override
    public Answer createAnswer(String answer, String email, Long questionId)
    {
        Answer newAnswer = new Answer();
        newAnswer.setAnswer(answer);
        newAnswer.setCreator(email);
        newAnswer.setCreatedDate(LocalDateTime.now());
        newAnswer.setDownVotes(0);
        newAnswer.setUpVotes(0);
        newAnswer.setQuestion(questionRepository.getById(questionId));
        return newAnswer;
    }
}
