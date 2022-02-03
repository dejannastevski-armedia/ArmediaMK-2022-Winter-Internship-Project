package com.example.demo.repository;

import com.example.demo.model.UserAnswer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long>
{
}
