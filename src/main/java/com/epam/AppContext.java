package com.epam;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

public class AppContext {

    @Component
    public static class ApplicationContextProvider implements ApplicationContextAware {
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            AppContext.setApplicationContext(applicationContext);
        }
    }
    private AppContext(){}
    
    private static ApplicationContext applicationContext;
    
    public static void setApplicationContext(ApplicationContext context)
    {
        applicationContext = context;
    }
    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }


}
