package first.project.acessingdatamysql;

import first.project.acessingdatamysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    public User findByEmail(String email);
//CRUD Operacii create update
}