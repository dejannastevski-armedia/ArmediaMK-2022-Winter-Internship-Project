package com.example.demo.repository;

import com.example.demo.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long>
{
    // @Query("SELECT q FROM Question q WHERE q.id=?1")
    // public Question findById(int id);
}
