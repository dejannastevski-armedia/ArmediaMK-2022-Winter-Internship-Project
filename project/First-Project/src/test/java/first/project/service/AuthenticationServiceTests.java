package first.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import first.project.dto.UserDTO;
import first.project.exceptions.UserValidationException;
import first.project.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTests
{
    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Test
    public void checkEmailTest()
    {
        String invalidEmail1 = "kiko_slavkoski.com";
        String invalidEmail2 = "kiko_slavkoski";
        String validEmail1 = "kiko_slavkoski@yahoo.com";
        assertThat(authenticationService.checkEmail(invalidEmail1)).isEqualTo(false);
        assertThat(authenticationService.checkEmail(invalidEmail2)).isEqualTo(false);
        assertThat(authenticationService.checkEmail(validEmail1)).isEqualTo(true);
    }

    @Test
    public void validateAndSaveInvalidEmailTest()
    {
        User user = new User();
        user.setEmail("kiko4_slavkoski.com");
        user.setPassword("12qaz!QAZ2wsx@WSX");
        user.setUsername("Hristijan3");
        user.setAge(21);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("The email is invalid");
        assertThat(authenticationService.validateAndSave(user)).isEqualTo(expected);
    }

    @Test
    public void validateAndSaveInvalidUsernameTest()
    {
        User user = new User();
        user.setEmail("kiko40_slavkoski@yahoo.com");
        user.setPassword("12qaz!QAZ2wsx@WSX");
        user.setUsername("");
        user.setAge(21);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("The username is invalid");
        assertThat(authenticationService.validateAndSave(user)).isEqualTo(expected);
    }

    @Test
    public void validateAndSaveInvalidPasswordTest()
    {
        User user = new User();
        user.setEmail("kiko40_slavkoski@yahoo.com");
        user.setPassword("12345678");
        user.setUsername("Hristijan");
        user.setAge(21);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("The password is invalid");
        assertThat(authenticationService.validateAndSave(user)).isEqualTo(expected);
    }

    @Test
    public void validateAndSaveValidRegisterTest()
    {
        User user = new User();
        user.setEmail("kiko40_slavkoski@yahoo.com");
        user.setPassword("1qaz!QAZ2wsx@WSX");
        user.setUsername("Hristijan");
        user.setAge(21);
        ArrayList<String> expected = new ArrayList<>();
        assertThat(authenticationService.validateAndSave(user)).isEqualTo(expected);
    }

    @Test
    public void validateAndSaveInvalidPasswordInvalidUsernameTest()
    {
        User user = new User();
        user.setEmail("kiko40_slavkoski@yahoo.com");
        user.setPassword("12345678");
        user.setUsername("");
        user.setAge(21);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("The password is invalid");
        expected.add("The username is invalid");
        assertThat(authenticationService.validateAndSave(user)).isEqualTo(expected);
    }

    public void validateAndSaveInvalidEmailInvalidUsernameTest()
    {
        User user = new User();
        user.setEmail("kiko4_slavkoski");
        user.setPassword("1qaz!QAZ2wsx@WSX");
        user.setUsername("");
        user.setAge(21);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("The email is invalid");
        expected.add("The username is invalid");
        assertThat(authenticationService.validateAndSave(user)).isEqualTo(expected);
    }

    public void validateAndSaveInvalidEmailInvalidPasswordTest()
    {
        User user = new User();
        user.setEmail("kiko4_slavkoski");
        user.setPassword("12345678");
        user.setUsername("Hristijan");
        user.setAge(21);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("The email is invalid");
        expected.add("The password is invalid");
        assertThat(authenticationService.validateAndSave(user)).isEqualTo(expected);
    }

    public void validateAndSaveInvalidEmailInvalidPasswordInvalidUsernameTest()
    {
        User user = new User();
        user.setEmail("kiko4_slavkoski");
        user.setPassword("12345678");
        user.setUsername("");
        user.setAge(21);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("The email is invalid");
        expected.add("The password is invalid");
        expected.add("The username is invalid");
        assertThat(authenticationService.validateAndSave(user)).isEqualTo(expected);
    }

    @Test
    public void validateAndLoginInvalidEmailTest() throws UserValidationException
    {
        UserDTO user = new UserDTO();
        user.setEmail("kiko4_slavkoski.com");
        user.setPassword("12qaz!QAZ2wsx@WSX");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("The email is not registered");
        assertThat(authenticationService.validateAndLogin(user)).isEqualTo(expected);
    }

    @Test
    public void validateAndLoginEmailAndPasswordDoesNotMatchTest() throws UserValidationException
    {
        UserDTO user = new UserDTO();
        user.setEmail("kiko4_slavkoski@yahoo.com");
        user.setPassword("12qaz!QAZ2wsx@WSX");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("The email and password does not match");
        assertThat(authenticationService.validateAndLogin(user)).isEqualTo(expected);
    }

    @Test
    public void validateAndLoginValidLoginTest() throws UserValidationException
    {
        UserDTO user = new UserDTO();
        user.setEmail("kiko_slavkoski30@yahoo.com");
        user.setPassword("3edc#EDC");
        ArrayList<String> expected = new ArrayList<>();
        assertThat(authenticationService.validateAndLogin(user)).isEqualTo(expected);
    }
}