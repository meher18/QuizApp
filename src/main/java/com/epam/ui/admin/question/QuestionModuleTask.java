package com.epam.ui.admin.question;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum QuestionModuleTask {
    ADD_QUESTION(1, "ADD QUESTION"),
    VIEW_QUESTIONS(2, "VIEW QUESTIONS"),
    UPDATE_QUESTION(3, "UPDATE QUESTION"),
    DELETE_QUESTION(4, "DELETE QUESTION"),
    ADMIN_DASHBOARD(5, "ADMIN DASHBOARD");

    private final Integer operationsCode;
    private final String description;


    QuestionModuleTask(Integer operationsCode, String description) {
        this.description = description;
        this.operationsCode = operationsCode;
    }

    static final Map<Integer, String> operations = Stream.of(values())
            .collect(Collectors.toMap(k -> k.operationsCode, v -> v.description));
}



