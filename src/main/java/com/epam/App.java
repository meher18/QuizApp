package com.epam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.config.AppContext;
import com.epam.config.DataConfig;
import com.epam.config.EntityConfig;
import com.epam.config.GlobalConfig;
import com.epam.config.ServiceConfig;
import com.epam.config.UiConfig;
import com.epam.ui.AppUi;


public class App {
    public static final Logger LOGGER = LogManager.getLogger(App.class);
//
//    public static void main(String[] args) {
//
//        startApplication();
//    }
    public static void startApplication() {
        AppUi appUi = AppContext.getApplicationContext().getBean(AppUi.class);
        appUi.createApp();
    }
}
