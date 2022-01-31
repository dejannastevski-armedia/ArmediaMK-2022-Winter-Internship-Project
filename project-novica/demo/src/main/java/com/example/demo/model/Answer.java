package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "answer")
public class Answer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    private String creator;

    @Column(updatable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "UTC")
    private Date createdDate;

    private Integer upVotes;

    private Integer downVotes;

    @ManyToOne()
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    public Answer()
    {
    }

    public Answer(Long id, String answer, String creator, Date createdDate, Integer upVotes, Integer downVotes)
    {
        this.id = id;
        this.answer = answer;
        this.creator = creator;
        this.createdDate = createdDate;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    public Integer getUpVotes()
    {
        return upVotes;
    }

    public void setUpVotes(Integer upVotes)
    {
        this.upVotes = upVotes;
    }

    public Integer getDownVotes()
    {
        return downVotes;
    }

    public void setDownVotes(Integer downVotes)
    {
        this.downVotes = downVotes;
    }
}
