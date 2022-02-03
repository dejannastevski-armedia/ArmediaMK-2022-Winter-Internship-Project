package com.example.demo.repository;

import com.example.demo.model.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long>
{
    @Query(value = "SELECT * FROM Answer a WHERE a.question_id = :id ", nativeQuery = true)
    List<Answer> listAllAnswersPerQuestionId(@Param("id") Long questionId);

    @Transactional
    @Modifying
    @Query("UPDATE Answer a SET a.downVotes = :downVotes WHERE a.id = :answerId")
    int updateDownVotes(@Param("answerId") Long answerId, @Param("downVotes") int downVotes);

}
