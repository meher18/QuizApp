package com.epam.data.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// this component is not used, we can use db helper if in non-spring project
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DBHelper {
    static EntityManager entityManager;

    @Autowired
    @Qualifier("entityManager")
    public void setEntityManager(EntityManager entityManager)
    {
        DBHelper.entityManager = entityManager;
    }

    public static EntityManager getManager() {
        return entityManager;
    }
}
