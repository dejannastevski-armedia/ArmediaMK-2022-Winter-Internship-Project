package first.project.service;

import first.project.model.User;
import first.project.repository.UserRepository;
import first.project.util.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordHashing passwordHashing;

    @Override
    public boolean checkEmail(String email)
    {
        if ((email == null) || (email.length() == 0))
        {
            return false;
        }
        String regex = "^[A-Za-z0-9_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches())
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUserName(String username)
    {
        if ("".equals(username))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPassword(String password)
    {
        String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{7,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches())
        {
            return true;
        }
        return false;
    }

    @Override
    public void passwordEncode(User user)
    {
        String newPass = passwordHashing.passwordEncoder().encode(user.getPassword());
        user.setPassword(newPass);
    }

    @Override
    public ArrayList<String> validateAndSave(User user)
    {
        ArrayList<String> res = new ArrayList<String>();
        if (checkEmail(user.getEmail()) == false)
        {
            res.add("The email is invalid");
        }
        User u = userRepo.findByEmail(user.getEmail());
        if (u != null)
        {
            res.add("The email is already in use");
        }
        if (checkPassword(user.getPassword()) == false)
        {
            res.add("The password is invalid");
        }
        if (checkUserName(user.getUsername()) == false)
        {
            res.add("The username is invalid");
        }
        if (res.isEmpty())
        {
            passwordEncode(user);
            userRepo.save(user);
        }
        return res;
    }
}
