package com.epam.ui.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.util.CorrectInput;

public class Utility {
    public static final Logger LOGGER = LogManager.getLogger(Utility.class);

    private Utility()
    {}
    public static int takeInputForConfirmation() {
        LOGGER.info("Are you sure want to delete question ?");
        LOGGER.info("Press 1 > yes");
        LOGGER.info("Press 2 > no");
        return CorrectInput.getInteger();
    }

}
