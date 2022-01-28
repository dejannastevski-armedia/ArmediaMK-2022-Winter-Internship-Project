package com.example.firstproject.repository;

import com.example.firstproject.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long>
{
    @Override
    @Query("SELECT q FROM Question q WHERE q.id=?1")
    public Question getById(Long id);
}
