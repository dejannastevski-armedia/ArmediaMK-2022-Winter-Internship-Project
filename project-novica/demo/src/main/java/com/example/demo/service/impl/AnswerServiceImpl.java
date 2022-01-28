package com.example.demo.service.impl;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.model.Answer;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.AnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AnswerServiceImpl implements AnswerService
{

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public boolean checkAnswer(String answer)
    {
        if (answer == null || answer.isEmpty())
        {
            return false;
        }
        return true;
    }

    @Override
    public String validateAnswer(String answer)
    {
        String res = "";
        if (checkAnswer(answer) == false)
        {
            res += "Answer is empty";
        }
        return res;
    }

    @Override
    public void saveAnswer(Answer answer)
    {
        if (validateAnswer(String.valueOf(answer)).isEmpty())
        {
            this.answerRepository.save(answer);
        }
    }

    @Override
    public ArrayList<String> createAnswer(AnswerDTO answerDTO)
    {
        ArrayList<String> res = new ArrayList<>();
        if (checkAnswer(answerDTO.getAnswer()) == false)
        {
            res.add("Answer is empty");
        }
        if (res == null || res.isEmpty())
        {
            Answer answer = new Answer();
            answer.setAnswer(answerDTO.getAnswer());
            answer.setCreator(answerDTO.getEmail());
            answer.setDownVotes(0);
            answer.setUpVotes(0);
            answer.setQuestion(questionRepository.getById(answerDTO.getId()));
            answerRepository.save(answer);
        }
        return res;
    }
}
