package com.epam.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.entity.QuestionAnswer;
import com.epam.entity.User;
import com.epam.entity.UserQuiz;
import com.epam.service.libraryservice.UserQuizLibraryService;
import com.epam.ui.user.UserSession;

@Component
public class UserQuizService {

    User user;
    // contains the quiz questions and users answers
    UserQuiz userQuiz;

    @Autowired
    UserQuizLibraryService userQuizLibrary;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Integer, UserQuiz> getAllUserQuizzes() {
        return userQuizLibrary.getUserQuizzesWithId();
    }

    public void setUserQuiz(UserQuiz userQuiz) {
        this.userQuiz = userQuiz;
    }

    public UserQuiz getUserQuiz() {
        return userQuiz;
    }

    public boolean checkIfQuizTaken(int quizId, User loggedUser) {

        boolean isQuizAlreadyTaken = false;

        int count = (int) userQuizLibrary
                .getUserQuizzesWithId()
                .values()
                .stream()
                .filter(uQuiz ->
                        uQuiz.getUser().getId() == loggedUser.getId()
                        && UserSession.getLoggedUser() != null)
                .count();
        if (count > 0) {
            isQuizAlreadyTaken = true;
        }
        return isQuizAlreadyTaken;
    }

    public void setAnswers(List<QuestionAnswer> userAnswers) {
        userQuiz.setAnswers(userAnswers);
        calculateMarks();
    }

    private void calculateMarks() {

        final int[] result = {0};

        userQuiz.getAnswers().forEach(questionAnswer -> {
            if (questionAnswer.getOriginalAnswer() == questionAnswer.getSelectedAnswer()) {
                result[0] += questionAnswer.getQuestion().getMark();
            }
        });
        userQuiz.setResult(result[0]);
    }

    public void saveQuiz() {
        // while saving the quiz Set the quiz id
        userQuiz.setUser(user);
        userQuizLibrary.addQuiz(user, userQuiz);
    }
}
