package com.epam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuizAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(QuizAppApplication.class, args);
//		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		App.startApplication();

	}

}
