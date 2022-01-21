package com.example.demo.utill;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder
{
    public static void main(String[] args)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Ivan123#";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.printf(encodedPassword);
    }
}
