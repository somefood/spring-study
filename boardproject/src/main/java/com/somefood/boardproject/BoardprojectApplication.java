package com.somefood.boardproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

@EnableJpaAuditing
@SpringBootApplication
public class BoardprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardprojectApplication.class, args);
	}

}
