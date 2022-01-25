package first.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FirstProjectApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(FirstProjectApplication.class, args);
    }

}
