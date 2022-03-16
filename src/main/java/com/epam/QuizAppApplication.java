package com.epam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizAppApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(QuizAppApplication.class, args);
//		ApplicationContext context =
//                new AnnotationConfigApplicationContext(
//                        GlobalConfig.class,
//                        DataConfig.class,
//                        UiConfig.class);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		App.startApplication();
		
	}

}
