package com.epam.data.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.epam.data.repository.QuizDao;
import com.epam.entity.Question;
import com.epam.entity.Quiz;

@Component
@Primary
public class QuizDaoImpl implements QuizDao {

    @Autowired
    EntityManager entityManager;

    public Quiz getQuiz(int quizId) {
        entityManager.getTransaction().begin();
        Quiz quiz = entityManager.find(Quiz.class, quizId);
        entityManager.getTransaction().commit();
        return quiz;
    }

    @Override
    public Map<Integer, Quiz> getAllQuiz() {
        Map<Integer, Quiz> quizzes = new HashMap<>();
        entityManager.createQuery("select q from Quiz q", Quiz.class).getResultStream().forEach(quiz -> quizzes.put(quiz.getId(), quiz));
        return quizzes;
    }

    @Override
    public void setQuiz(int index, Quiz quiz) {

        entityManager.getTransaction().begin();
        entityManager.persist(quiz);
        entityManager.getTransaction().commit();

    }

    @Override
    public void editQuiz(int id, Quiz newQuiz) {

        entityManager.getTransaction().begin();
        entityManager.persist(newQuiz);
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteQuiz(int id) {

        entityManager.getTransaction().begin();
        Quiz quiz = entityManager.find(Quiz.class, id);
        entityManager.remove(quiz);
        entityManager.getTransaction().commit();

    }

    @Override
    public void changeQuizStatus(int quizId, String status) {

        entityManager.getTransaction().begin();
        Quiz quiz = entityManager.find(Quiz.class, quizId);
        quiz.setQuizTag(status);
        entityManager.persist(quiz);
        entityManager.getTransaction().commit();

    }

    public Map<Integer, Question> getQuestionsForQuiz(Quiz quiz) {

        entityManager.getTransaction().begin();
        Map<Integer, Question> questions = entityManager.find(Quiz.class, quiz.getId()).getQuestions();
        entityManager.getTransaction().commit();

        return questions;
    }
}
