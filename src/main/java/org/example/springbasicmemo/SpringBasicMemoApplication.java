package org.example.springbasicmemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBasicMemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBasicMemoApplication.class, args);
    }

}
