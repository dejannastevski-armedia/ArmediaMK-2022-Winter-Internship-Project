package com.example.demo.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
@RunWith(SpringRunner.class)
public class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateAndFindUser()
    {
        User user = new User();
        user.setEmail("novicds13123@hotmail.com");
        user.setPassword("Novica@");
        user.setUserName("Novica123");
        user.setAge(21);

        User savedUser = userRepository.save(user);
        Optional<User> existUser = userRepository.findByEmail(savedUser.getEmail());

        assertThat(existUser.get().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFoundByEmail()
    {
        Optional<User> u;
        u = userRepository.findByEmail("novica@hotmail.com");
        assertThat(u.get()).isNotNull();
    }

    @Test
    public void testNotFoundByEmail()
    {
        Optional<User> u;
        u = userRepository.findByEmail("novica@gmail.com");
        assertThat(u).isPresent();
    }
}
