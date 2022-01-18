package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import com.example.demo.util.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHashing passwordHashing;

    @Override
    public boolean checkUserName(String userName) {
        if (userName != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String password) {
        boolean isValid = true;
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String specialChars = "(.*[@,#,$,%].*$)";
        if ((password.length() < 7) || !password.matches(upperCaseChars) || !password.matches(lowerCaseChars) || !password.matches(specialChars)) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    public boolean validateAndSave(User user) {
        if (checkUserName(user.getUserName()) && checkPassword(user.getPassword())) //falit validacija za email
        {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Long id, User user) {
        User user1 = this.userRepository.getById(id);

        user1.setUserName(user.getUserName());
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());

        save(user1);
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<String> login(String email, String password) {
        List<String> lista = new ArrayList<>();
        if (email == null || email.isEmpty() || !checkEmail(email)) {
            lista.add("Invalid Email");
        }
        if (password == null || password.isEmpty() || !checkPassword(password)) {
            lista.add("Invalid password");
        }
        if (lista.isEmpty()) {
            Optional<User> user = this.userRepository.findByEmail(email);
            if (!user.isPresent()) {
                lista.add("There is not user with that email");
            } else {
                String hashedPassword = user.get().getPassword();
                if (!passwordHashing.encoder().matches(password, hashedPassword)) {
                    lista.add("Password doesn't matches");
                }
            }
        }
        return lista;
    }


    @Override
    public List<String> register(String email, String password, String userName, Integer age) {
        List<String> lista = new ArrayList<>();
        if (!checkUserName(userName)) {
            // throw new InvalidArgumentException("Enter valid user name");
            lista.add("Enter valid username ");
        }
        if (!checkPassword(password)) {
            //throw new InvalidArgumentException("Enter valid password");
            lista.add("Enter valid password ");
        }
        if (!checkEmail(email)) {
            //throw new InvalidArgumentException("Enter valid email");
            lista.add("Enter valid email");
        }
        if (this.userRepository.findByEmail(email).isPresent()) {
            //throw new EmailAlreadyExistException("Already existing email address");
            lista.add("Already existing email address ");
        }
        if (userName == null || userName.isEmpty()) {
            lista.add("Invalid username");
        }
        if (lista.size() == 0) {
            String encodedPassword = passwordHashing.encoder().encode(password);
            User user = new User(email, encodedPassword, userName, age);
            this.userRepository.save(user);
        }
        return lista;
    }

    @Override
    public boolean checkEmail(String email) {
        String regex = "^(.+)@(.+)$";
        if (email != null && !email.isEmpty() && email.matches(regex)) {
            return true;
        }
        return false;
    }
}