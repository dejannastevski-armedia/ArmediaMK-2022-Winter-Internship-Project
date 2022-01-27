package first.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import first.project.dto.UserDTO;
import first.project.exceptions.UserValidationException;
import first.project.model.User;
import first.project.repository.UserRepository;
import first.project.util.PasswordHashing;

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
    public ArrayList<String> validateAndSave(User user)
    {
        ArrayList<String> res = new ArrayList<>();
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
            String newPass = passwordHashing.passwordEncoder().encode(user.getPassword());
            user.setPassword(newPass);
            userRepo.save(user);
        }
        return res;
    }

    @Override
    public int checkLoginEmail(String email)
    {
        User u = userRepo.findByEmail(email);
        if (u != null)
        {
            return u.getId();
        }
        return -1;
    }

    @Override
    public User checkLoginPassword(String password, int id)
    {
        User u = userRepo.findById(id);
        if (passwordHashing.passwordEncoder().matches(password, u.getPassword()) == true)
        {
            return u;
        }
        return null;
    }

    @Override
    public User validateAndLogin(UserDTO userDTO) throws UserValidationException
    {
        User u = userRepo.findByEmail(userDTO.getEmail());
        if (u == null)
        {
            throw new UserValidationException("The email is not registered");
        }
        if (passwordHashing.passwordEncoder().matches(userDTO.getPassword(), u.getPassword()) == false)
        {
            throw new UserValidationException("The email and password does not match");
        }
        return u;
    }
}
