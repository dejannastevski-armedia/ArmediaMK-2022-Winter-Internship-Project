package com.example.demo.repository;

import com.example.demo.model.Answer;
import com.example.demo.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Long>
{
    @Query("SELECT a FROM Question a WHERE a.question = ?1")
    public Question findByAnswer(String answer);
}
