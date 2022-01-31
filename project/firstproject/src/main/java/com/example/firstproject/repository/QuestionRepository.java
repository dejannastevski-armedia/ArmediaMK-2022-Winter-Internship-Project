package com.example.firstproject.repository;

import com.example.firstproject.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long>
{
    @Override
    public Optional<Question> findById(Long id);
}
