package first.project.service;

import first.project.model.User;

import java.util.ArrayList;

public interface AuthenticationService
{
    boolean checkEmail(String email);

    boolean checkUserName(String username);

    boolean checkPassword(String password);

    ArrayList<String> validateAndSave(User user);
}
