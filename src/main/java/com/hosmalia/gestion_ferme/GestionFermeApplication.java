package com.hosmalia.gestion_ferme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Active l'audit pour que Spring Boot remplisse automatiquement les champs
					// createdAt et updatedAt sans que vous n'ayez à faire de
					// .setCreatedAt(LocalDate.now()) manuellement
@SpringBootApplication
public class GestionFermeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionFermeApplication.class, args);
	}

}
