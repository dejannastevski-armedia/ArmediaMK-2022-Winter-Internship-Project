package first.project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import first.project.model.User;
import first.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class UserRepositoryTests
{
    @Autowired
    private UserRepository repo;

    @Test
    public void testFoundByEmail()
    {
        User u;
        u = repo.findByEmail("kiko_slavkoski30@yahoo.com");
        assertThat(u).isNotNull();
    }

    @Test
    public void testNotFoundByEmail()
    {
        User u;
        u = repo.findByEmail("kiko_slavkoski70@yahoo.com");
        assertThat(u).isEqualTo(null);
    }

    @Test
    public void testFoundById()
    {
        User u;
        u = repo.findById(3);
        assertThat(u).isNotNull();
    }

    @Test
    public void testNotFoundById()
    {
        User u;
        u = repo.findById(-3);
        assertThat(u).isEqualTo(null);
    }

    @Test
    public void testCreateUser()
    {
        User user = new User();
        user.setEmail("kiko50_slavkoski@yahoo.com");
        user.setPassword("12qaz!QAZ2wsx@WSX");
        user.setUsername("Hristijan3");
        user.setAge(21);
        User savedUser = repo.save(user);
        User existUser = repo.findByEmail(savedUser.getEmail());
        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }
}