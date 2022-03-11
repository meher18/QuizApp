package com.epam.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.config.AppContext;
import com.epam.entity.Question;
import com.epam.entity.QuestionOption;
import com.epam.service.admin.questionservice.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	QuestionService questionService; 
	
	@RequestMapping("/createQuestion")
	public String createQuestion()
	{
		return "admin/question/createQuestion";
	}

	@RequestMapping("/viewQuestions")
	public ModelAndView viewQuestions()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/question/viewQuestions");
		modelAndView.addObject("questions",questionService.getQuestions().values());
		
		return  modelAndView;
		
	}
	
	@RequestMapping(value = "/updateQuestion", params = "id")
	public ModelAndView updateQuestion(
			@RequestParam(value = "id") String id)
	{
		int questionId = Integer.parseInt(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/question/updateQuestion");
		modelAndView.addObject("question",questionService.getQuestion(questionId));
		
		return  modelAndView;
		
	}
	
	
	
	@RequestMapping(value = "/addQuestion", params = {"title", "options", "optionsVal",
			"topic",  "difficulty", "answer", "mark"})
	public String addQuestion(
			@RequestParam(value = "title") String title,
			@RequestParam(value = "options") String optionCount,
			@RequestParam(value = "optionsVal") String optionsValue,
			@RequestParam(value = "topic") String topicTag,
			@RequestParam(value = "difficulty") String difficulty,
			@RequestParam(value = "answer") String answer,
			@RequestParam(value = "mark") String mark
			)
	{
		
		
		Question question = AppContext.getApplicationContext().getBean(Question.class);
		question.setQuestionTitle(title);
		
		String options[] = optionsValue.split(":");
	
		List<String> optionsList = Arrays.asList(options);
		for(String optionTitle : optionsList)
		{
			QuestionOption option = AppContext.getApplicationContext().getBean(QuestionOption.class);
			option.setOptionTitle(optionTitle);
			question.setOption(option);
		}
		question.setTopicTag(topicTag);
		question.setDifficultyTag(difficulty);
		question.setAnswer(Integer.parseInt(answer));
		question.setMark(Integer.parseInt(mark));
		questionService.createQuestion(question);
		
		
		return "admin/question/createQuestion";
		
	}
	
	

	@RequestMapping(value = "/updateTheQuestion", params = {"id", "title", "options", "optionsVal",
			"topic",  "difficulty", "answer", "mark"})
	public String updateQuestion(
			@RequestParam(value = "id") String id,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "options") String optionCount,
			@RequestParam(value = "optionsVal") String optionsValue,
			@RequestParam(value = "topic") String topicTag,
			@RequestParam(value = "difficulty") String difficulty,
			@RequestParam(value = "answer") String answer,
			@RequestParam(value = "mark") String mark
			){
		
		int questionId = Integer.parseInt(id);
		
		Question question = questionService.getQuestion(questionId);
		question.setQuestionTitle(title);
		
		String options[] = optionsValue.split(":");
	
		List<String> optionsList = Arrays.asList(options);
		for(String optionTitle : optionsList)
		{
			QuestionOption option = AppContext.getApplicationContext().getBean(QuestionOption.class);
			option.setOptionTitle(optionTitle);
			question.setOption(option);
		}
		question.setTopicTag(topicTag);
		question.setDifficultyTag(difficulty);
		question.setAnswer(Integer.parseInt(answer));
		question.setMark(Integer.parseInt(mark));
		questionService.update(question, questionId );
		
		
		return "admin/question/updateQuestion";
		
	}
	
	
	
	
	
	
}
