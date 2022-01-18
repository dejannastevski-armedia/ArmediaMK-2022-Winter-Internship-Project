package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("ivan.n");
        user.setEmail("ivan@yahoo.com");
        user.setUsername("andrija.m");
        user.setPassword("andrija");
        user.setAge(28);


        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }


}
