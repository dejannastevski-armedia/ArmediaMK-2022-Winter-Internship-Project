package first.project.service;

import first.project.acessingdatamysql.User;
import first.project.acessingdatamysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{
    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean checkEmail(String email)
    {
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
        if (username.equals(""))
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
    public String[] validateAndSave(User user)
    {
        String res[] = new String[4];
        for (int i = 0; i < 4; i++)
        {
            res[i] = "";
        }
        if (checkEmail(user.getEmail()) == false)
        {
            res[0] += "The email is invalid\n";
        }
        User u = userRepo.findByEmail(user.getEmail());
        if (u != null)
        {
            res[0] += "The email is already in use\n";
        }
        if (checkPassword(user.getPassword()) == false)
        {
            res[1] += "The password is invalid\n";
        }
        if (checkUserName(user.getUsername()) == false)
        {
            res[2] += "The username is invalid\n";
        }
        boolean ok = true;
        for (int i = 0; i < 4; i++)
        {
            if (res[i] != "")
            {
                ok = false;
            }
        }
        if (ok == true)
        {
            res[3] = "true";
            userRepo.save(user);
        } else
        {
            res[3] = "false";
        }
        return res;
    }
}
