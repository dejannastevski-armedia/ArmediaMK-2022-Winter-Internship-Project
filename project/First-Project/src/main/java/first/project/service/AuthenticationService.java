package first.project.service;

import first.project.acessingdatamysql.User;

public interface AuthenticationService
{
    boolean checkEmail(String email);

    boolean checkUserName(String username);

    boolean checkPassword(String password);

    String[] validateAndSave(User user);
}
