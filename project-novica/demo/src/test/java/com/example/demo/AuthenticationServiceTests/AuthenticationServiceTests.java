package com.example.demo.AuthenticationServiceTests;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.impl.AuthenticationServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTests
{
    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Test
    public void checkEmailTest()
    {
        String validemail1 = "novica@gmail.com";
        String validemail2 = "novica@hotmail.com";
        String validemail3 = "novica123@gmail.com";
        String invalidEmail1 = "invalidEmail";
        String invalidEmail2 = "@InvalidEmail";
        String invalidEmail3 = "///////";

        assertThat(authenticationService.checkEmail(validemail1)).isEqualTo(true);
        assertThat(authenticationService.checkEmail(validemail2)).isEqualTo(true);
        assertThat(authenticationService.checkEmail(validemail3)).isEqualTo(true);
        assertThat(authenticationService.checkEmail(invalidEmail1)).isEqualTo(false);
        assertThat(authenticationService.checkEmail(invalidEmail2)).isEqualTo(false);
        assertThat(authenticationService.checkEmail(invalidEmail3)).isEqualTo(false);
    }

    @Test
    public void validateAndLoginInvalidEmailTest1()
    {
        UserDTO user = new UserDTO();
        user.setEmail("novicagmail.com");
        user.setPassword("Nndnds@@3");
        List<String> expected = new ArrayList<>();
        expected.add("Invalid Email");
        assertThat(authenticationService.login(user.getEmail(), user.getPassword())).isEqualTo(expected);
    }

    @Test
    public void validateAndLoginInvalidEmailTest2()
    {
        UserDTO user = new UserDTO();
        user.setEmail("InvalidEmailExample");
        user.setPassword("Ttt$#@2");
        List<String> expected = new ArrayList<>();
        expected.add("Invalid Email");
        assertThat(authenticationService.login(user.getEmail(), user.getPassword())).isEqualTo(expected);
    }

    @Test
    public void validateAndLoginInvalidEmailTest3()
    {
        UserDTO user = new UserDTO();
        user.setEmail("novicagmail.com");
        user.setPassword("ADDss@@3");
        List<String> expected = new ArrayList<>();
        expected.add("Invalid Email");
        assertThat(authenticationService.login(user.getEmail(), user.getPassword())).isEqualTo(expected);
    }

    @Test
    public void validateAndLoginInvalidPasswordTest1()
    {
        UserDTO user = new UserDTO();
        user.setEmail("novica@gmail.com");
        user.setPassword("dsadsa");
        List<String> expected = new ArrayList<>();
        expected.add("Invalid password");
        assertThat(authenticationService.login(user.getEmail(), user.getPassword())).isEqualTo(expected);
    }

    @Test
    public void validateAndLoginInvalidPasswordTest2()
    {
        UserDTO user = new UserDTO();
        user.setEmail("novica@gmail.com");
        user.setPassword("invalidpasswordexample");
        List<String> expected = new ArrayList<>();
        expected.add("Invalid password");
        assertThat(authenticationService.login(user.getEmail(), user.getPassword())).isEqualTo(expected);
    }

    @Test
    public void validateAndLoginInvalidPasswordTest3()
    {
        UserDTO user = new UserDTO();
        user.setEmail("novica@gmail.com");
        user.setPassword("nevaliden pasvord");
        List<String> expected = new ArrayList<>();
        expected.add("Invalid password");
        assertThat(authenticationService.login(user.getEmail(), user.getPassword())).isEqualTo(expected);
    }
}