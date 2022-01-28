package com.example.demo.repository;

import com.example.demo.model.Answer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>
{
    // @Query("SELECT q FROM Question q WHERE q.id=?1")
    // public Question findById(int id);
}
