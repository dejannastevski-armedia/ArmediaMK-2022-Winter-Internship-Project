package first.project.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable
{

    public User(Integer id, String email, String password, String username, Integer age)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
    }

    public User()
    {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "age", nullable = false, length = 10)
    private Integer age;

    @OneToMany(mappedBy = "user")
    private List<UserAnswerStatus> userAnswerStatusList;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String name)
    {
        this.username = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

}