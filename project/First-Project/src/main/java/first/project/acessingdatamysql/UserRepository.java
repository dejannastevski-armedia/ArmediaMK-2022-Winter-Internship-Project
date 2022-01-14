package first.project.acessingdatamysql;

import first.project.acessingdatamysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    default User findByEmail(String email)
    {
        User u[] = findAll().toArray(new User[0]);
        for (int i = 0; i < u.length; i++)
        {
            if (email.equals(u[i].getEmail()))
            {
                return u[i];
            }
        }
        return null;
    }
//CRUD Operacii create update
}