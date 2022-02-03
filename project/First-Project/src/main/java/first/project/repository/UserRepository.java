package first.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import first.project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id=?1")
    public User findById(int id);

    @Query("SELECT u FROM User u WHERE u.email=?1 AND u.password=?2")
    public User findByEmailAndPassword(String email, String password);
}