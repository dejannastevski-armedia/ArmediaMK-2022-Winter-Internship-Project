package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserAnswer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    Answer answer;

    Integer likes;

    Integer dislikes;

    public UserAnswer()
    {
    }

    public UserAnswer(Long id, User user, Answer answer, Integer likes, Integer dislikes)
    {
        this.id = id;
        this.user = user;
        this.answer = answer;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Answer getAnswer()
    {
        return answer;
    }

    public void setAnswer(Answer answer)
    {
        this.answer = answer;
    }

    public Integer getLikes()
    {
        return likes;
    }

    public void setLikes(Integer likes)
    {
        this.likes = likes;
    }

    public Integer getDislikes()
    {
        return dislikes;
    }

    public void setDislikes(Integer dislikes)
    {
        this.dislikes = dislikes;
    }
}
