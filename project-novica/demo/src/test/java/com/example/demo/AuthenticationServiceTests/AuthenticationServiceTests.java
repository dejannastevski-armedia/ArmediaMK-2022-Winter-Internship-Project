package com.example.demo.AuthenticationServiceTests;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.service.impl.AuthenticationServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTests
{
    @Autowired
    private AuthenticationServiceImpl authenticationService;

     @Test
     public void checkEmailTest()
     {
         String validEmail1 = "novica@gmail.com";
         String validEmail2 = "novica@hotmail.com";
         String validEmail3 = "novica123@gmail.com";
         String invalidEmail1 = "invalidEmail";
         String invalidEmail2 = "@InvalidEmail";
         String invalidEmail3 = "///////";

     assertThat(authenticationService.checkEmail(validEmail1)).isEqualTo(true);
     assertThat(authenticationService.checkEmail(validEmail2)).isEqualTo(true);
     assertThat(authenticationService.checkEmail(validEmail3)).isEqualTo(true);
     assertThat(authenticationService.checkEmail(invalidEmail1)).isEqualTo(false);
     assertThat(authenticationService.checkEmail(invalidEmail2)).isEqualTo(false);
     assertThat(authenticationService.checkEmail(invalidEmail3)).isEqualTo(false);
 }

@Test
public void checkPasswordTest()
{
    String invalidPassword1 = "aaa";
    String invalidPassword2 = "pass";
    String invalidPassword3 = "123";
    assertThat(authenticationService.checkEmail(invalidPassword1)).isEqualTo(false);
    assertThat(authenticationService.checkEmail(invalidPassword2)).isEqualTo(false);
    assertThat(authenticationService.checkEmail(invalidPassword3)).isEqualTo(false);
}

 // @Test
 // public void validateAndLoginInvalidEmailTest1()
 // {
 // UserDTO user = new UserDTO();
 // user.setEmail("novicagmail.com");
 // user.setPassword("Nndnds@@3");
 // UserValidationException userValidationException = assertThrows(UserValidationException.class, () -> {
 // authenticationService.login(user.getEmail(), user.getPassword(), userDTO.getRole());
 // });
 // assertThat(userValidationException.getMessage()).isEqualTo("Invalid Email");
 // }
 //
 // @Test
 // public void validateAndLoginInvalidEmailTest2()
 // {
 // UserDTO user = new UserDTO();
 // user.setEmail("InvalidEmailExample");
 // user.setPassword("Ttt$#@2");
 // UserValidationException userValidationException = assertThrows(UserValidationException.class, () -> {
 // authenticationService.login(user.getEmail(), user.getPassword(), userDTO.getRole());
 // });
 // assertThat(userValidationException.getMessage()).isEqualTo("Invalid Email");
 // }
 //
 // @Test
 // public void validateAndLoginInvalidEmailTest3()
 // {
 // UserDTO user = new UserDTO();
 // user.setEmail("novicagmail.com");
 // user.setPassword("ADDss@@3");
 // UserValidationException userValidationException = assertThrows(UserValidationException.class, () -> {
 // authenticationService.login(user.getEmail(), user.getPassword(), userDTO.getRole());
 // });
 // assertThat(userValidationException.getMessage()).isEqualTo("Invalid Email");
 // }
 //
 // @Test
 // public void validateAndLoginInvalidPasswordTest1()
 // {
 // UserDTO user = new UserDTO();
 // user.setEmail("novica@gmail.com");
 // user.setPassword("dsadsa");
 // UserValidationException userValidationException = assertThrows(UserValidationException.class, () -> {
 // authenticationService.login(user.getEmail(), user.getPassword(), userDTO.getRole());
 // });
 // assertThat(userValidationException.getMessage()).isEqualTo("Invalid password");
 // }
 //
 // @Test
 // public void validateAndLoginInvalidPasswordTest2()
 // {
 // UserDTO user = new UserDTO();
 // user.setEmail("novica@gmail.com");
 // user.setPassword("invalidpasswordexample");
 // UserValidationException userValidationException = assertThrows(UserValidationException.class, () -> {
 // authenticationService.login(user.getEmail(), user.getPassword(), userDTO.getRole());
 // });
 // assertThat(userValidationException.getMessage()).isEqualTo("Invalid password");
 // }
 //
 // @Test
 // public void validateAndLoginInvalidPasswordTest3()
 // {
 // UserDTO user = new UserDTO();
 // user.setEmail("novica@gmail.com");
 // user.setPassword("nevaliden pasvord");
 // UserValidationException userValidationException = assertThrows(UserValidationException.class, () -> {
 // authenticationService.login(user.getEmail(), user.getPassword(), userDTO.getRole());
 // });
 // assertThat(userValidationException.getMessage()).isEqualTo("Invalid password");
 // }
}