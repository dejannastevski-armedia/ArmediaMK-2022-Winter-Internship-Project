package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.exceptions.UserValidationException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import com.example.demo.util.PasswordHashing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHashing passwordHashing;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean checkUserName(String userName)
    {
        if (userName != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String password)
    {
        boolean isValid = true;
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String specialChars = "(.*[@,#,$,%].*$)";
        if ((password.length() < 7) || !password.matches(upperCaseChars) || !password.matches(lowerCaseChars)
                || !password.matches(specialChars))
        {
            isValid = false;
        }
        return isValid;
    }

    @Override
    public boolean validateAndSave(User user)
    {
        if (checkUserName(user.getUserName()) && checkPassword(user.getPassword()))
        {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public void save(User user)
    {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id)
    {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Long id, User user)
    {
        User user1 = this.userRepository.getById(id);

        user1.setUserName(user.getUserName());
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());

        save(user1);
    }

    @Override
    public void deleteUserById(Long id)
    {
        this.userRepository.deleteById(id);
    }

    @Override
    public User login(UserDTO userDTO) throws UserValidationException
    {
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty() || !checkEmail(userDTO.getEmail()))
        {
            throw new UserValidationException("Invalid Email");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty() || !checkPassword(userDTO.getPassword()))
        {
            throw new UserValidationException("Invalid password");
        }
        Optional<User> user = this.userRepository.findByEmail(userDTO.getEmail());
        if (!user.isPresent())
        {
            throw new UserValidationException("Invalid credentials");
        }
        else
        {
            String hashedPassword = user.get().getPassword();
            if (!passwordHashing.encoder().matches(userDTO.getPassword(), hashedPassword))
            {
                throw new UserValidationException("Invalid credentials");
            }
        }
        return user.get();
    }

    @Override
    public List<String> register(String email, String password, String userName, Integer age, String role)
    {
        List<String> lista = new ArrayList<>();
        if (!checkUserName(userName))
        {
            lista.add("Enter valid username ");
        }
        if (!checkPassword(password))
        {
            lista.add("Enter valid password ");
        }
        if (!checkEmail(email))
        {

            lista.add("Enter valid email");
        }
        if (this.userRepository.findByEmail(email).isPresent())
        {

            lista.add("Already existing email address ");
        }
        if (userName == null || userName.isEmpty())
        {
            lista.add("Invalid username");
        }
        if (lista.size() == 0)
        {
            String encodedPassword = passwordHashing.encoder().encode(password);
            Role role1 = roleRepository.getById(Long.parseLong(role));
            User user = new User(email, encodedPassword, userName, age, role1);
            this.userRepository.save(user);
        }
        return lista;
    }

    @Override
    public boolean checkEmail(String email)
    {
        String regex = "^(.+)@(.+)$";
        if (email != null && !email.isEmpty() && email.matches(regex))
        {
            return true;
        }
        return false;
    }

    @Override
    public List<User> listAllUsersWithRoleUser()
    {
        return userRepository.findAllByRoleUser();
    }
}