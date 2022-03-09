package com.epam.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.epam.entity.Quiz;
import com.epam.entity.User;
import com.epam.entity.UserQuiz;

@Configuration
@ComponentScan("com.epam")
@PropertySource("classpath:application.properties")
public class DataConfig {

    @Value("${app.persistence.unit.name}")
    String persistenceUnitName;

    @Bean("entityManager")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public EntityManager entityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public Map<Integer, Quiz> quizMap() {
        return new HashMap<>();
    }

    @Bean
    public List<User> users() {
        return new ArrayList<>();
    }

    @Bean
    public Map<User, UserQuiz> userUserQuizMap() {
        return new HashMap<>();
    }

}
