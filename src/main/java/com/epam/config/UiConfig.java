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
import com.epam.ui.viewer.QuestionsViewer;

@Configuration
@ComponentScan("com.epam")
public class UiConfig {


    @Bean("questionServiceUiFunctions")
    public Map<Integer, QuestionOperationUi> getQuestionServiceUiFunctions() {
        Map<Integer, QuestionOperationUi> questionServiceUiFunctions = new HashMap<>();

        questionServiceUiFunctions.put(1, () -> AppContext.getApplicationContext().getBean(QuestionBuilderUi.class).buildQuestion());
        questionServiceUiFunctions.put(2, () -> {
            AppContext.getApplicationContext().getBean(QuestionsViewer.class).viewQuestions();
            QuestionRedirectUi.redirect();
        });
        questionServiceUiFunctions.put(3, () -> AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateQuestion());
        questionServiceUiFunctions.put(4, () -> AppContext.getApplicationContext().getBean(QuestionDeleteUi.class).deleteQuestion());
        questionServiceUiFunctions.put(5, AdminDashBoardUi::showAdminTasks);
        return questionServiceUiFunctions;
    }

    @Bean("questionUpdateOperations")
    public Map<Integer, Consumer<Question>> getQuestionUpdateOperationFunctions() {
        Map<Integer, Consumer<Question>> questionUpdateOperationFunctions = new HashMap<>();


        questionUpdateOperationFunctions.put(1, question ->
                AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateTitle(question)
        );
        questionUpdateOperationFunctions.put(2, question ->
                AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateOptions(question)
        );
        questionUpdateOperationFunctions.put(3, question ->
                AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateTopicTag(question)
        );
        questionUpdateOperationFunctions.put(4, question ->
                AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateDifficulty(question)
        );
        questionUpdateOperationFunctions.put(5, question ->
                AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateAnswer(question)
        );
        questionUpdateOperationFunctions.put(6, question ->
                AppContext.getApplicationContext().getBean(QuestionUpdateUi.class).updateMark(question)
        );

        return questionUpdateOperationFunctions;
    }

}
