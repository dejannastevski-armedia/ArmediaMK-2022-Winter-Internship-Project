package com.example.demo.dto;

public class UserAnswerDTO
{
    Long answerId;

    String userEmail;

    public UserAnswerDTO()
    {
    }

    public UserAnswerDTO(Long answerId, String userEmail)
    {
        this.answerId = answerId;
        this.userEmail = userEmail;
    }

    public Long getAnswerId()
    {
        return answerId;
    }

    public void setAnswerId(Long answerId)
    {
        this.answerId = answerId;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }
}
