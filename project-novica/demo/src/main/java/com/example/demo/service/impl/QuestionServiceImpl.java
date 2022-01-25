package com.example.demo.service.impl;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.model.Question;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService
{

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public boolean checkTitle(String title)
    {
        if (title == null || title.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean checkQuestion(String question)
    {
        if (question == null || question.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void saveQuestion(Question question)
    {
        questionRepository.save(question);
    }

    @Override
    public String validateQuestion(String question, String title)
    {
        String res = "";
        if (question == null || question.isEmpty())
        {
            res += "Enter a question ";
        }
        if (title == null || title.isEmpty())
        {
            res += "Enter a title ";
        }
        return res;
    }

    @Override
    public String createQuestion(QuestionDTO questionDTO)
    {
        String res = "";

        if (questionDTO.getQuestion() == null || questionDTO.getQuestion().isEmpty())
        {
            res += "Question is empty ";
        }
        if (questionDTO.getTitle() == null || questionDTO.getTitle().isEmpty())
        {
            res += "Title is empty ";
        }
        if (res.isEmpty())
        {
            Question question = new Question();
            question.setCreator("Novica");
            question.setModifier("Novica");
            question.setTitle(questionDTO.getTitle());
            question.setQuestion(questionDTO.getQuestion());
            // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            // LocalDateTime now = LocalDateTime.now();
            // question.setCreatedAt(new Date(String.valueOf(now)));
            // TODO:fix date format
            // question.setCreatedAt();
            questionRepository.save(question);
        }
        return res;
    }
}
