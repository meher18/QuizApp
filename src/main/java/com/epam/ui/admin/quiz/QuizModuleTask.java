package com.epam.ui.admin.quiz;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum QuizModuleTask {
    CREATE_QUIZ(1, "CREATE QUIZ"),
    MODIFY_QUIZ(2, "MODIFY QUIZ"),
    DELETE_QUIZ(3, "DELETE QUIZ"),
    HOST_QUIZ(4, "HOST QUIZ"),
    VIEW_ALL_QUIZZES(5, "VIEW ALL QUIZZES "),
    ADMIN_DASHBOARD(6, "ADMIN DASHBOARD");

    private final Integer operationsCode;
    private final String description;

    QuizModuleTask(Integer operationsCode, String description) {
        this.description = description;
        this.operationsCode = operationsCode;
    }

    static final Map<Integer, String> operations = Stream.of(values())
            .collect(Collectors.toMap(k -> k.operationsCode, v -> v.description));
}
