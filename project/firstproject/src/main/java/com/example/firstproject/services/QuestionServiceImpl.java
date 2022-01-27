package com.example.firstproject.services;

import com.example.firstproject.model.Question;
import com.example.firstproject.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuestionServiceImpl implements QuestionService
{
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void saveQuestion(Question question)
    {
        questionRepository.save(question);
    }

    @Override
    public boolean checkTitle(String title)
    {
        if(title.length() != 0)
            return true;
        return false;
    }

    @Override
    public boolean checkQuestion(String question)
    {
        if(question.length() != 0)
            return true;
        return false;
    }

    @Override
    public String validateQuestion(String question, String title)
    {
        StringBuilder sb = new StringBuilder();
        if(checkQuestion(question) == false)
        {
            sb.append("Question Field Blank!");
        }
        if(checkTitle(title) == false)
        {
            sb.append("Title Field Blank!");
        }
        return sb.toString();
    }

    @Override
    public Question createQuestion(String question, String title, String email)
    {
        Question newQuestion = new Question();
        newQuestion.setQuestion(question);
        newQuestion.setCreatedDate(LocalDateTime.now());
        newQuestion.setTitle(title);
        newQuestion.setCreator(email);
        newQuestion.setModifier(email);
        return newQuestion;
    }
}
