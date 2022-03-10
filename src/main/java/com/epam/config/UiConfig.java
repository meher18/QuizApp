package com.epam.config;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.epam.entity.Question;
import com.epam.ui.admin.AdminDashBoardUi;
import com.epam.ui.admin.question.QuestionBuilderUi;
import com.epam.ui.admin.question.QuestionDeleteUi;
import com.epam.ui.admin.question.QuestionOperationUi;
import com.epam.ui.admin.question.QuestionRedirectUi;
import com.epam.ui.admin.question.QuestionUpdateUi;
import com.epam.ui.admin.quiz.QuizBuilderUi;
import com.epam.ui.admin.quiz.QuizDeleteUi;
import com.epam.ui.admin.quiz.QuizHostingServiceUi;
import com.epam.ui.admin.quiz.QuizModifyUi;
import com.epam.ui.admin.quiz.QuizModuleUi;
import com.epam.ui.admin.quiz.QuizOperationUi;
import com.epam.ui.admin.quiz.QuizRedirectUi;
import com.epam.ui.viewer.QuestionsViewer;
import com.epam.ui.viewer.QuizViewer;

@Configuration
@ComponentScan("com.epam")
public class UiConfig {

	@Bean("questionServiceUiFunctions")
	public Map<Integer, QuestionOperationUi> questionServiceUiFunctions() {
		Map<Integer, QuestionOperationUi> questionServiceUiFunctions = new HashMap<>();

		questionServiceUiFunctions.put(1,
				() -> AppContext.getApplicationContext().getBean(QuestionBuilderUi.class).buildQuestion());
		questionServiceUiFunctions.put(2, () -> {
			AppContext.getApplicationContext().getBean(QuestionsViewer.class).viewQuestions();
			QuestionRedirectUi.redirect();
		});
		questionServiceUiFunctions.put(3,
				() -> AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateQuestion());
		questionServiceUiFunctions.put(4,
				() -> AppContext.getApplicationContext().getBean(QuestionDeleteUi.class).deleteQuestion());
		questionServiceUiFunctions.put(5,
				() -> AppContext.getApplicationContext().getBean(AdminDashBoardUi.class).showAdminTasks());
		return questionServiceUiFunctions;
	}

	@Bean("questionUpdateOperations")
	public Map<Integer, Consumer<Question>> questionUpdateOperationFunctions() {
		Map<Integer, Consumer<Question>> questionUpdateOperationFunctions = new HashMap<>();

		questionUpdateOperationFunctions.put(1,
				question -> AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateTitle(question));
		questionUpdateOperationFunctions.put(2,
				question -> AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateOptions(question));
		questionUpdateOperationFunctions.put(3, question -> AppContext.getApplicationContext()
				.getBean(QuestionUpdateUi.class).updateTopicTag(question));
		questionUpdateOperationFunctions.put(4, question -> AppContext.getApplicationContext()
				.getBean(QuestionUpdateUi.class).updateDifficulty(question));
		questionUpdateOperationFunctions.put(5,
				question -> AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateAnswer(question));
		questionUpdateOperationFunctions.put(6,
				question -> AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateMark(question));

		return questionUpdateOperationFunctions;
	}

	@Bean("quizModuleOperations")
	public Map<Integer, QuizOperationUi> quizServiceOperations() {
		Map<Integer, QuizOperationUi> questionServiceUiFunctions = new HashMap<>();

		questionServiceUiFunctions.put(1,
				() -> AppContext.getApplicationContext().getBean(QuizBuilderUi.class).buildQuiz());
		questionServiceUiFunctions.put(2,
				() -> AppContext.getApplicationContext().getBean(QuizModifyUi.class).updateQuiz());
		questionServiceUiFunctions.put(3,
				() -> AppContext.getApplicationContext().getBean(QuizDeleteUi.class).deleteQuiz());
		questionServiceUiFunctions.put(4,
				() -> AppContext.getApplicationContext().getBean(QuizHostingServiceUi.class).hostQuiz());
		questionServiceUiFunctions.put(5, () -> {
			AppContext.getApplicationContext().getBean(QuizViewer.class).viewAllQuizzes();
			QuizRedirectUi.redirect();
		});
		questionServiceUiFunctions.put(6,
				() -> AppContext.getApplicationContext().getBean(AdminDashBoardUi.class).showAdminTasks());
		return questionServiceUiFunctions;
	}

	@Bean("quizUpdateOperations")
	public Map<Integer, QuizOperationUi> quizUpdateOperations() {

		Map<Integer, QuizOperationUi> quizUpdateFunctions = new HashMap<>();

		quizUpdateFunctions.put(1,
				() -> AppContext.getApplicationContext().getBean(QuizModifyUi.class).updateQuizTitle());
		quizUpdateFunctions.put(2,
				() -> AppContext.getApplicationContext().getBean(QuizModifyUi.class).addQuestionToQuiz());
		quizUpdateFunctions.put(3,
				() -> AppContext.getApplicationContext().getBean(QuizModifyUi.class).deleteQuestionFromQuiz());
		quizUpdateFunctions.put(4,
				() -> AppContext.getApplicationContext().getBean(QuizModuleUi.class).showQuizModuleTasks());

		return quizUpdateFunctions;
	}

}
