package com.epam.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.global.Constants;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quiz_name")
    private String quizName;

    @Column(name = "quiz_code")
    private long quizCode;

    //  maybe it should be removed
    @ManyToMany
    @Autowired
    @Qualifier("questions")
    Map<Integer, Question> questions = new HashMap<>();

    private int totalMarks = 0;

    // we can set the quiz tag to "hosted" or "completed" so that user view quizzes accordingly
    // NOT HOSTED is default
    private String quizTag = Constants.QUIZ_NOT_HOSTED;


    public void addQuestion(int index, Question question) {
        questions.put(index, question);
        totalMarks += question.getMark();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Map<Integer, Question> getQuestions() {
        return questions;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public long getQuizCode() {
        return quizCode;
    }

    public void setQuizCode(long quizCode) {
        this.quizCode = quizCode;
    }

    public String getQuizTag() {
        return quizTag;
    }

    public void setQuizTag(String quizTag) {
        this.quizTag = quizTag;
    }
}
