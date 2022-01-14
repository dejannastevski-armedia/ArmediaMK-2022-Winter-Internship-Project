package com.example.firstproject.repository;

import com.example.firstproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    public User findByEmail(String email);
}
