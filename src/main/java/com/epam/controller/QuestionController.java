package com.epam.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.config.AppContext;
import com.epam.entity.Question;
import com.epam.entity.QuestionOption;
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
			@RequestParam(value = "mark") String mark) {

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

		return "admin/question/createQuestion";

	}

	@RequestMapping(value = "/deleteTheQuestion", params = "id")
	public String deleteQuestion(@RequestParam(value = "id") String questionId, Model model) {
	
		String deletionStatus = "Not Deleted";
		
		int id = Integer.parseInt(questionId);
		
		if(questionService.delete(id))
		{
				deletionStatus = " Deleted";
		}
		
		model.addAttribute("questions", questionService.getQuestions().values());
		model.addAttribute("deletionStatus", deletionStatus);
		return "admin/question/viewQuestions";
	}

	@RequestMapping(value = "/updateTheQuestion", params = { "id", "title", "options", "optionsVal", "topic",
			"difficulty", "answer", "mark" })
	public String updateQuestion(@RequestParam(value = "id") String id, @RequestParam(value = "title") String title,
			@RequestParam(value = "options") String optionCount,
			@RequestParam(value = "optionsVal") String optionsValue, @RequestParam(value = "topic") String topicTag,
			@RequestParam(value = "difficulty") String difficulty, @RequestParam(value = "answer") String answer,
			@RequestParam(value = "mark") String mark) {

		int questionId = Integer.parseInt(id);

		Question question = questionService.getQuestion(questionId);
		question.setQuestionTitle(title);

		String options[] = optionsValue.split(":");

		List<String> optionsList = Arrays.asList(options);

//		
//		if (optionsList.size() >= question.getOptions().size()) {
//			int i = 0;
//			for (i = 0; i < question.getOptions().size(); i++) {
//				question.getOptions().get(i).setOptionTitle(optionsList.get(i));
//			}
//
//			if (i < optionsList.size()) {
//				QuestionOption option = AppContext.getApplicationContext().getBean(QuestionOption.class);
//				option.setOptionTitle(optionsList.get(i));
//				question.setOption(option);
//				i++;
//			}
//
//		} else {
//			int j = 0;
//			for (j = 0; j < optionsList.size(); j++) {
//				question.getOptions().get(j).setOptionTitle(optionsList.get(j));
//			}
//			if (j < question.getOptions().size()) {
//				question.getOptions().remove(j);
//				j++;
//			}
//		}

	
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

		return "admin/question/updateQuestion";

	}
}
