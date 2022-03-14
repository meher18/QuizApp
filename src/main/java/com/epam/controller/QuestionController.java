package com.epam.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.config.AppContext;
import com.epam.entity.Question;
import com.epam.entity.QuestionOption;
import com.epam.exceptions.InValidQuestionDeletion;
import com.epam.exceptions.InValidQuestionId;
import com.epam.service.admin.questionservice.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@RequestMapping("/createQuestion")
	public String createQuestion() {
		return "admin/question/createQuestion";
	}

	@RequestMapping("/viewQuestions")
	public String viewQuestions(Model model) {
		
		model.addAttribute("admin/question/viewQuestions");
		model.addAttribute("questions", questionService.getQuestions().values());
		return "admin/question/viewQuestions";
	}

	@RequestMapping(value = "/updateQuestion", params = "id")
	public String updateQuestion(@RequestParam(value = "id") String id, Model model) {
		int questionId = Integer.parseInt(id);
		model.addAttribute("question", questionService.getQuestion(questionId));
		return "admin/question/updateQuestion";

	}

	@RequestMapping(value = "/addQuestion", params = { "title", "options", "optionsVal", "topic", "difficulty",
			"answer", "mark" })
	public String addQuestion(@RequestParam(value = "title") String title,
			@RequestParam(value = "options") String optionCount,
			@RequestParam(value = "optionsVal") String optionsValue, @RequestParam(value = "topic") String topicTag,
			@RequestParam(value = "difficulty") String difficulty, @RequestParam(value = "answer") String answer,
			@RequestParam(value = "mark") String mark,
			Model model) {

		Map<String, String> errors = new HashMap<String, String>();
		
		
		if(title == "")
		{
			errors.put("title", "Please provide the title");
		}
		
		if(optionCount == "" || Integer.parseInt(optionCount) < 2 || Integer.parseInt(optionCount) > 6 )
		{
			errors.put("options", "Please provide min 2 options, max 6 options");
		}
		if(optionsValue == "")
		{
			errors.put("optionsVal", "Please provide the options");
		}
		if(topicTag == "")
		{
			errors.put("topic", "Please provide the topic");
		}
		if(difficulty == "")
		{
			errors.put("difficulty", "Please provide the difficulty");
		}
		if(answer == "")
		{
			errors.put("answer", "Please provide the answer");
		}
		if(mark == "")
		{
			errors.put("mark", "Please provide the mark");
		}
		String redirectPage = "redirect:/viewQuestions";
		
		if(errors.size() <= 0)
		{
			createQuestion(title, optionsValue, topicTag, difficulty, answer, mark);
			model.addAttribute("questionCreationStatus", "Question Created");
		}
		else {
			model.addAttribute("errors", errors);
			redirectPage = "admin/question/createQuestion";
		}
		

		return redirectPage;

	}

	private void createQuestion(String title, String optionsValue, String topicTag, String difficulty, String answer,
			String mark) {
		Question question = AppContext.getApplicationContext().getBean(Question.class);

		question.setQuestionTitle(title);

		String options[] = optionsValue.split(":");

		List<String> optionsList = Arrays.asList(options);
		for (String optionTitle : optionsList) {
			QuestionOption option = AppContext.getApplicationContext().getBean(QuestionOption.class);
			option.setOptionTitle(optionTitle);
			question.setOption(option);
		}

		question.setTopicTag(topicTag);
		question.setDifficultyTag(difficulty);
		question.setAnswer(Integer.parseInt(answer));
		question.setMark(Integer.parseInt(mark));

		questionService.createQuestion(question);
	}
	
	

	@RequestMapping(value = "/deleteTheQuestion", params = "id")
	public String deleteQuestion(@RequestParam(value = "id") String questionId, Model model) {
	
		String deletionStatus = "Not Deleted";
		
		int id = Integer.parseInt(questionId);
		
	
		try {
			questionService.validateQuestionId(id);

			if(questionService.delete(id))
			{
					deletionStatus = " Deleted";
			}
		} catch (InValidQuestionId e) {
			deletionStatus = "Unable to delete, Invalid Question Id";
			
			e.printStackTrace();
		} catch (InValidQuestionDeletion e) {
			
			deletionStatus = "Unable to delete, Question is part of some quiz";
			e.printStackTrace();
		}
	
		model.addAttribute("questions", questionService.getQuestions().values());
		model.addAttribute("deletionStatus", deletionStatus);
		return "admin/question/viewQuestions";
	}

	@RequestMapping(value = "/updateTheQuestion", params = { "id", "title", "optionsVal", "topic",
			"difficulty", "answer", "mark" })
	public String updateQuestion(@RequestParam(value = "id") String id, @RequestParam(value = "title") String title,
			@RequestParam(value = "optionsVal") String optionsValue, @RequestParam(value = "topic") String topicTag,
			@RequestParam(value = "difficulty") String difficulty, @RequestParam(value = "answer") String answer,
			@RequestParam(value = "mark") String mark,
			Model model) {

		Map<String, String> errors = new HashMap<String, String>();
		
		
		if(title == "")
		{
			errors.put("title", "Please provide the title");
		}
		
		if(optionsValue == "")
		{
			errors.put("optionsVal", "Please provide the options");
		}
		if(topicTag == "")
		{
			errors.put("topic", "Please provide the topic");
		}
		if(difficulty == "")
		{
			errors.put("difficulty", "Please provide the difficulty");
		}
		if(answer == "")
		{
			errors.put("answer", "Please provide the answer");
		}
		if(mark == "")
		{
			errors.put("mark", "Please provide the mark");
		}
		
		String redirectPage = "admin/question/viewQuestions";
		if(errors.size() <= 0)
		{
			updateQuestion(id, title, optionsValue, topicTag, difficulty, answer, mark);
			model.addAttribute("questions", questionService.getQuestions().values());
			model.addAttribute("updationStatus", "UPDATED");
		}
		else {
			model.addAttribute("errors", errors);
			redirectPage = "redirect:/updateQuestion?id="+id;
		}
		
		return redirectPage;

	}

	private void updateQuestion(String id, String title, String optionsValue, String topicTag, String difficulty,
			String answer, String mark) {
		int questionId = Integer.parseInt(id);

		Question question = questionService.getQuestion(questionId);
		question.setQuestionTitle(title);

		String options[] = optionsValue.split(":");

		List<String> optionsList = Arrays.asList(options);

	
		question.questionOptions.clear();
		
		for (String optionTitle : optionsList) {
			if (!question.questionOptions.contains(optionTitle)) {
				QuestionOption option = AppContext.getApplicationContext().getBean(QuestionOption.class);
				option.setOptionTitle(optionTitle);
				question.setOption(option);
			}
		}
		question.setTopicTag(topicTag);
		question.setDifficultyTag(difficulty);
		question.setAnswer(Integer.parseInt(answer));
		question.setMark(Integer.parseInt(mark));
		
		questionService.update(question, questionId);
	}
}
