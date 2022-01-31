package com.example.firstproject.repository;

import com.example.firstproject.model.Answer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>
{
}
