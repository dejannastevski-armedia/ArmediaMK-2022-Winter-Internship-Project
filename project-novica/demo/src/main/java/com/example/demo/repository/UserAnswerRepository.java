package com.example.demo.repository;

import com.example.demo.model.UserAnswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long>
{
    @Query("SELECT u FROM UserAnswer u WHERE u.user.id=?1 AND u.answer.id=?2")
    UserAnswer findUserAnswerByUserIdAndAnswerId(Long userId, Long answerId);
}
