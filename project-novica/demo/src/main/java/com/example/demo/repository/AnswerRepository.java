package com.example.demo.repository;

import com.example.demo.model.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long>
{
    @Query(value = "SELECT * FROM Answer a WHERE a.question_id = :id ", nativeQuery = true)
    List<Answer> listAllAnswerPerQuestion(@Param("id") Long question);
}
