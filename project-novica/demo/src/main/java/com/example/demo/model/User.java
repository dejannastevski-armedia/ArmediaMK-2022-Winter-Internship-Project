package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "users")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

    @Column(name = "age", nullable = true)
    private Integer age;

    @OneToMany(mappedBy = "user")
    List<UserAnswer> userAnswerList;

    public User()
    {
    }

    public User(Long id, String email, String password, String userName, Integer age)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.age = age;
    }

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String userName, Integer age)
    {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.age = age;
    }

    public User(String email, String password, String userName)
    {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }
}