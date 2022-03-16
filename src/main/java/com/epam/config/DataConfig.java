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

import com.epam.entity.Question;
import com.epam.entity.QuestionAnswer;
import com.epam.entity.QuestionOption;
import com.epam.entity.Quiz;
import com.epam.entity.User;
import com.epam.entity.UserQuiz;

@Configuration
@ComponentScan("com.epam")
public class DataConfig {

//
//    @Bean("entityManager")
//    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
//    public EntityManager entityManager() {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-local-mysql");
//        return entityManagerFactory.createEntityManager();
//    }
}
