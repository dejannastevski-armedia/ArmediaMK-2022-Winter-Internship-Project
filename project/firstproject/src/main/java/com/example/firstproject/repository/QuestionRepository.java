package com.example.firstproject.repository;

import com.example.firstproject.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long>
{
}
