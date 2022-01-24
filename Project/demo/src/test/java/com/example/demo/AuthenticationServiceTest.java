package com.example.demo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTest
{
    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void emailTest()
    {
        String invalidEmail1 = "ora.com";
        String invalidEmail2 = "ora@gmail";
        String validEmail = "ora@gmail.com";
        assertThat(authenticationService.validateEmail(invalidEmail1)).isEqualTo(false);
        assertThat(authenticationService.validateEmail(invalidEmail2)).isEqualTo(false);
        assertThat(authenticationService.validateEmail(validEmail)).isEqualTo(true);
    }

    @Test
    public void usernameTest()
    {
        String invalidUsername1 = "";
        String invalidUsername2 = "a";
        String validUsername = "rita.ora";
        assertThat(authenticationService.validateUsername(invalidUsername1)).isEqualTo(false);
        assertThat(authenticationService.validateUsername(invalidUsername2)).isEqualTo(false);
        assertThat(authenticationService.validateUsername(validUsername)).isEqualTo(true);
    }

    @Test
    public void passwordTest()
    {
        String invalidPassword1 = "oraaaa";
        String invalidPassword2 = "ora123#";
        String validPassword = "Ora123#";
        assertThat(authenticationService.validatePassword(invalidPassword1)).isEqualTo(false);
        assertThat(authenticationService.validatePassword(invalidPassword2)).isEqualTo(false);
        assertThat(authenticationService.validatePassword(validPassword)).isEqualTo(true);
    }

    @Test
    public void validateUserTest()
    {
        User invalidUser1 = new User();
        invalidUser1.setUsername("rita.ora");
        invalidUser1.setEmail("oragmail.com");
        invalidUser1.setPassword("Rita123#");
        invalidUser1.setAge(29);

        User invalidUser2 = new User();
        invalidUser2.setUsername("rita.ora");
        invalidUser2.setEmail("oragmail.com");
        invalidUser2.setPassword("Ritaaaa");
        invalidUser2.setAge(29);

        User invalidUser3 = new User();
        invalidUser3.setUsername("rita.ora");
        invalidUser3.setEmail("ora@gmail.com");
        invalidUser3.setPassword("Ritaaaa");
        invalidUser3.setAge(29);

        User validUser1 = new User();
        validUser1.setUsername("rita.ora");
        validUser1.setEmail("ora@gmail.com");
        validUser1.setPassword("Rita123#");
        validUser1.setAge(29);

        assertThat(authenticationService.validateUser(invalidUser2)).isEqualTo("Invalid email,Invalid password,");
        assertThat(authenticationService.validateUser(invalidUser1)).isEqualTo("Invalid email,");
        assertThat(authenticationService.validateUser(invalidUser3)).isEqualTo("Invalid password,");
        assertThat(authenticationService.validateUser(validUser1)).isEqualTo("Email already exist");
    }
}
