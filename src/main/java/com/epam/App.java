package com.epam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.config.AppContext;
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
