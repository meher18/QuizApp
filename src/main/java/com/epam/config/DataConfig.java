package com.epam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
