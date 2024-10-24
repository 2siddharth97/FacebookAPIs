package projects.facebookapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FacebookApIsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacebookApIsApplication.class, args);
    }

}
