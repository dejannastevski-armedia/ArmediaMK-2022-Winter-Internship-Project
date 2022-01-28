package com.example.demo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "answer")
@EntityListeners(AuditingEntityListener.class)
public class Answer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String answer;

    private String creator;

    @CreatedDate
    @Column(name = "date", nullable = false)
    private Instant createDate;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    private Integer upVotes;

    private Integer downVotes;

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

    public Instant getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Instant createDate)
    {
        this.createDate = createDate;
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

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }
}
