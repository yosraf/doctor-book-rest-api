package com.yosra.gestionpraticien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestionPraticienApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionPraticienApplication.class, args);
    }

}
