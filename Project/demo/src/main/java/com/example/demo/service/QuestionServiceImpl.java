package com.example.demo.service;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.model.Question;
import com.example.demo.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService
{
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public boolean checkTitle(String title)
    {
        if (title == null || title.length() <= 2 || title.isEmpty())
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkQuestion(String question)
    {
        if (question == null || question.length() <= 5 || question.isEmpty())
        {
            return false;
        }
        return true;
    }

    @Override
    public String validateQuestionAndTitle(QuestionDTO questionDTO)
    {
        String res = "";
        if (!checkTitle(questionDTO.getTitle()))
        {
            res += "Invalid title,";
        }
        if (!checkQuestion(questionDTO.getQuestion()))
        {
            res += "Invalid question";
        }

        return res;
    }

    @Override
    public void saveQuestion(Question question)
    {
        questionRepository.save(question);

    }

    @Override
    public void createQuestion(QuestionDTO questionDTO)
    {
        Question question = new Question();
        question.setCreator(questionDTO.getEmail());
        question.setModifier(questionDTO.getEmail());
        question.setTitle(questionDTO.getTitle());
        question.setQuestion(questionDTO.getQuestion());
        questionRepository.save(question);

    }

}
