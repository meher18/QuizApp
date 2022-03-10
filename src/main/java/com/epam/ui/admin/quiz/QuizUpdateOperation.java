package com.epam.ui.admin.quiz;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum QuizUpdateOperation {
	
    UPDATE_QUIZ_TITLE(1, "UPDATE QUIZ TITLE"),
    ADD_QUESTIONS(2, "ADD QUESTIONS TO QUIZ"),
    DELETE_QUESTIONS(3, "DELETE QUESTIONS FROM QUIZ"),
    QUIZ_MODULE(4, "QUIZ MODULE");

    private final int code;
    private final String description;

    QuizUpdateOperation(int code, String description) {
        this.code = code;
        this.description = description;

    }
    protected static final Map<Integer, String> operations = Stream.of(values()).collect(Collectors.toMap(k-> k.code, v -> v.description));
}
