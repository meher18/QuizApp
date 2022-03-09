package com.epam.ui.admin.question;


import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum QuestionUpdateOperation {
    UPDATE_QUESTION_TITLE(1, "UPDATE QUESTION TITLE"),
    UPDATE_QUESTION_OPTION(2, "UPDATE QUESTION OPTION"),
    UPDATE_QUESTION_TOPIC_TAG(3, "UPDATE QUESTION TOPIC TAG"),
    UPDATE_QUESTION_DIFFICULTY(4, "UPDATE QUESTION DIFFICULTY"),
    UPDATE_QUESTION_ANSWER(5, "UPDATE QUESTION ANSWER"),
    UPDATE_QUESTION_MARK(6, "UPDATE QUESTION MARK");

    private final int code;
    private final String description;

    QuestionUpdateOperation(int code, String description) {
        this.code = code;
        this.description = description;

    }

    protected static final Map<Integer, String> operations = Stream.of(values()).collect(Collectors.toMap(k -> k.code, v -> v.description));
}
