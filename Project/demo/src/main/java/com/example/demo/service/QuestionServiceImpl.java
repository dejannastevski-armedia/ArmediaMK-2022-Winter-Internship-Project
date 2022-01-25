package com.example.demo.service;

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
    public String validateQuestionAndTitle(Question question)
    {
        String res = "";
        if (!checkTitle(question.getTitle()))
        {
            res += "Invalid title,";
        }
        if (!checkQuestion(question.getQuestion()))
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
    public Question createQuestion(Question question)
    {
        question.setCreator("Marija");
        question.setModifier("Marija");
        question.setTitle(question.getTitle());
        question.setQuestion(question.getQuestion());
        questionRepository.save(question);
        return question;
    }

}
