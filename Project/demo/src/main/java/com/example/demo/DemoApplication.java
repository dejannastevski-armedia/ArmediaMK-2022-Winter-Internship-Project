package com.example.demo;
<<<<<<< HEAD
=======

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
>>>>>>> dca96b3aced208d8c12d75805234b2ebe0e415ad

@SpringBootApplication
public class DemoApplication
{

<<<<<<< HEAD
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DemoApplication {

    public static void main(String[] args) {

=======
    public static void main(String[] args)
    {
>>>>>>> dca96b3aced208d8c12d75805234b2ebe0e415ad

        SpringApplication.run(DemoApplication.class, args);
    }


}
