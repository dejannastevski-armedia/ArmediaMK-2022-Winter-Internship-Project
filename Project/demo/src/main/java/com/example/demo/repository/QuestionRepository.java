package com.example.demo.repository;

import com.example.demo.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long>
{
    @Query("SELECT q FROM Question q WHERE q.title = ?1")
    public Question findByTitle(String title);
}
