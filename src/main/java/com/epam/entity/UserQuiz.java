package com.epam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.AppContext;
import com.epam.service.libraryservice.QuizLibraryService;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
public class UserQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @OneToOne
    private Quiz quiz;

    @OneToMany(mappedBy = "userQuiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Autowired
    @Qualifier("questionAnswers")
    List<QuestionAnswer> answers;

    private int result;


    public void setQuizById(int idOfQuiz) {
        this.quiz = AppContext.getApplicationContext().getBean(QuizLibraryService.class).getQuizzes().get(idOfQuiz);
    }

    public int getQuizId() {
        return id;
    }

    public void setQuizId(int quizId) {
        this.id = quizId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<QuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        answers.forEach(questionAnswer -> questionAnswer.setUserQuiz(this));
        this.answers = answers;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
