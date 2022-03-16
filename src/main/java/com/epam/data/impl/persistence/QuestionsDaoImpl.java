package com.epam.data.impl.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.epam.config.AppContext;
import com.epam.data.dao.QuestionDao;
import com.epam.data.dao.QuizDao;
import com.epam.entity.Question;

public class QuestionsDaoImpl implements QuestionDao {

	
	EntityManager entityManager;

	Map<Integer, Question> questionMap = new HashMap<Integer, Question>();

	public Question getQuestion(int questionId) {
		entityManager.getTransaction().begin();
		Question question = entityManager.find(Question.class, questionId);
		entityManager.getTransaction().commit();
		return question;
	}

	@Override
	public Map<Integer, Question> getAllQuestions() {

		Map<Integer, Question> questionMap = new HashMap<Integer, Question>();
		List<Question> questionList = entityManager.createQuery("SELECT q FROM Question q", Question.class)
				.getResultList();

		questionList.forEach(question -> questionMap.put(question.getId(), question));

		return questionMap;
	}

	@Override
	public void setQuestion(int index, Question question) {
		entityManager.getTransaction().begin();
		entityManager.persist(question);
		entityManager.getTransaction().commit();
	}

	@Override
	public void editQuestion(int id, Question question) {
		entityManager.getTransaction().begin();
		entityManager.persist(question);
		entityManager.getTransaction().commit();
	}

	@Override
	public void deleteQuestion(int index) {
		try {
			entityManager.getTransaction().begin();
			Question question = entityManager.find(Question.class, index);
			entityManager.remove(question);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
		}
		
	}

	@Override
	public int getNoOfQuizzesForQuestionId(int questionId) {
		AtomicInteger no = new AtomicInteger();
		no.set(0);
		QuizDao quizDao = AppContext.getApplicationContext().getBean(QuizDaoImpl.class);
		quizDao.getAllQuiz().values().stream().forEach(quiz -> {
			if (quiz.getQuestions().containsKey(questionId)) {
				no.getAndIncrement();
			}
		});
//        System.out.println(entityManager.createQuery("SELECT qq FROM quiz_question qq")
//                .getResultList().size());
//        return entityManager.createQuery("SELECT qq FROM quiz_question qq WHERE qq.questions_id = :id")
//                .setParameter("id", questionId).getResultList().size();
		return no.get();
	}

}
