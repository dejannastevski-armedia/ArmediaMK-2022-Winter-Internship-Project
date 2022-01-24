package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "questions")
public class Question
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String title;

    @Column(nullable = false, length = 164)
    private String question;

    @Column(nullable = false, unique = true, length = 45)
    private String creator;

    @Column(nullable = false, unique = true, length = 45)
    private String modifier;

    @Column(nullable = false, unique = true, length = 45)
    private Date date;
}
