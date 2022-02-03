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

    Boolean isLiked;

    Boolean isDisliked;

    public UserAnswer()
    {
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

    public Boolean getLiked()
    {
        return isLiked;
    }

    public void setLiked(Boolean liked)
    {
        isLiked = liked;
    }

    public Boolean getDisliked()
    {
        return isDisliked;
    }

    public void setDisliked(Boolean disliked)
    {
        isDisliked = disliked;
    }
}
