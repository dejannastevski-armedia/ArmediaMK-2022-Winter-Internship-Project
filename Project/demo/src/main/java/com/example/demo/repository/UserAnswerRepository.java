package com.example.demo.repository;

import com.example.demo.model.UserAnswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long>
{
    @Query("SELECT u FROM  UserAnswer u WHERE u.answer.id= ?1 and u.user.id= ?2")
    public UserAnswer findStatusByAnswerAndUser(Long answerId, Long userId);

}
