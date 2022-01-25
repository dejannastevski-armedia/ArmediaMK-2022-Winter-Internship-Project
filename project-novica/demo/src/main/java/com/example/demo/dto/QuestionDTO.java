package com.example.demo.dto;

public class QuestionDTO
{
    String question;
    String title;

    public QuestionDTO()
    {
    }

    public QuestionDTO(String question, String title)
    {
        this.question = question;
        this.title = title;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
