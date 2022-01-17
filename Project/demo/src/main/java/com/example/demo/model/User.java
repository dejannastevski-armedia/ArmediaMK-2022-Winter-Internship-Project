    package com.example.demo.model;

    import javax.persistence.*;

    @Entity
    @Table(name = "user")

    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column( nullable = false, length = 45)
        private String username;

        @Column(nullable = false, unique = true,length = 45)
        private String email;

        @Column(nullable = false, length = 64)
        private String password;

        @Column(name = "age", nullable = false, length = 3)
        private int age;


        public User()
        {
        }

        public int getAge()
        {
            return age;
        }

        public Long getId()
        {
            return id;
        }

        public String getUsername()
        {
            return username;
        }

        public String getEmail()
        {
            return email;
        }

        public String getPassword()
        {
            return password;
        }



        public void setId(Long id)
        {
            this.id = id;
        }

        public void setEmail(String email)
        {
            this.email = email;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }

        public void setAge(int age)
        {
            this.age = age;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }
    }
