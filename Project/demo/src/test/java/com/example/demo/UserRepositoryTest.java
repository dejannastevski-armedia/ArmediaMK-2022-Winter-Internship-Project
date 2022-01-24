package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class UserRepositoryTest
{

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser()
    {
        User user = new User();
        user.setUsername("john.ray");
        user.setEmail("john@hotmail.com");
        user.setPassword("Ray123#");
        user.setAge(24);

        User savedUser = repo.save(user);

        User existUser = repo.findByEmail("john@hotmail.com");

        assertThat(savedUser.getId()).isEqualTo(existUser.getId());
    }

}
