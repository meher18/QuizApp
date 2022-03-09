package com.epam.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.epam.entity.Question;
import com.epam.entity.QuestionAnswer;
import com.epam.entity.QuestionOption;

@Configuration
@ComponentScan("com.epam")
@PropertySource("classpath:application.properties")
public class EntityConfig {

    @Bean("questionOptions")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public List<QuestionOption> questionOptions()
    {
        return new ArrayList<>();
    }

    @Bean("questionAnswers")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public List<QuestionAnswer> questionAnswers()
    {
        return new ArrayList<>();
    }

    @Bean("questions")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Map<Integer, Question> questionMap()
    {
        return new HashMap<>();
    }
}
