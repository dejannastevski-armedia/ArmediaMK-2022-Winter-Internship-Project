package com.example.firstproject.dto;

public class QuestionDTO
{
    private String title;
    private String question;

    public QuestionDTO(String title, String question)
    {
        this.title = title;
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

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }
}
