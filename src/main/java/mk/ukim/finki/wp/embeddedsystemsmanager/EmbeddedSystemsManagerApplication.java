package mk.ukim.finki.wp.embeddedsystemsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EmbeddedSystemsManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedSystemsManagerApplication.class, args);
    }

}
