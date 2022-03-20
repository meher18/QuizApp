package com.epam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.dto.QuestionDto;
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

	@RequestMapping(value = "/addQuestion")
	public String addTheQuestion(QuestionDto questionDto, Model model, HttpServletResponse response) {

		Map<String, String> errors = new HashMap<String, String>();

		if (questionDto.questionTitle == "") {
			errors.put("title", "Please provide the title");
		}

		if (questionDto.questionOptions.size() == 0 || questionDto.questionOptions.size() < 2
				|| questionDto.questionOptions.size() > 6) {
			errors.put("options", "Please provide min 2 options, max 6 options");
		}
		if (questionDto.topicTag == "") {
			errors.put("topic", "Please provide the topic");
		}
		if (questionDto.difficultyTag == "") {
			errors.put("difficulty", "Please provide the difficulty");
		}
		if (questionDto.answer == 0) {
			errors.put("answer", "Please provide the answer");
		}
		if (questionDto.mark < 0) {
			errors.put("mark", "Please provide the mark");
		}
		String redirectPage = "redirect:/viewQuestions";

		if (errors.isEmpty()) {
			response.addHeader("questionCreationStatus", "Question Created");
//			model.addAttribute("questionCreationStatus", "Question Created");
			createQuestion(questionDto);
		} else {
			model.addAttribute("errors", errors);
			redirectPage = "admin/question/createQuestion";
		}

		return redirectPage;
	}

//	public void createQuestion(String title, String optionsValue, String topicTag, String difficulty, String answer,
	public void createQuestion(QuestionDto questionDto) {
		Question question = new Question();

		question.setQuestionTitle(questionDto.questionTitle);

//		String options[] = questionDtooptionsValue.split(":");

		List<String> optionsList = questionDto.questionOptions;
		for (String optionTitle : optionsList) {
			QuestionOption option = new QuestionOption();
			option.setOptionTitle(optionTitle);
			question.setOption(option);
		}

		question.setTopicTag(questionDto.topicTag);
		question.setDifficultyTag(questionDto.difficultyTag);
		question.setAnswer(questionDto.answer);
		question.setMark(questionDto.mark);

		questionService.createQuestion(question);
	}

	@RequestMapping(value = "/deleteTheQuestion", params = "id")
	public String deleteQuestion(@RequestParam(value = "id") String questionId, Model model) {

		String deletionStatus = "Not Deleted";

		int id = Integer.parseInt(questionId);

		try {
			questionService.validateQuestionId(id);

			if (questionService.delete(id)) {
				deletionStatus = "Deleted";
			}
		} catch (InValidQuestionId e) {
			deletionStatus = "Unable to delete, Invalid Question Id";

		} catch (InValidQuestionDeletion e) {

			deletionStatus = "Unable to delete, Question is part of some quiz";
		}

		model.addAttribute("questions", questionService.getQuestions().values());
		model.addAttribute("deletionStatus", deletionStatus);
		return "admin/question/viewQuestions";
	}

	@RequestMapping(value = "/updateTheQuestion")
	public String updateTheQuestion(QuestionDto questionDto, Model model) {

		Map<String, String> errors = new HashMap<String, String>();

		if (questionDto.questionTitle == "") {
			errors.put("title", "Please provide the title");
		}

		if (questionDto.questionOptions.size() <= 0 || questionDto.questionOptions.size() < 2 || questionDto.questionOptions.size() > 6) {
			errors.put("options", "Please provide min 2 options, max 6 options");
		}
		if (questionDto.topicTag == "") {
			errors.put("topic", "Please provide the topic");
		}
		if (questionDto.difficultyTag == "") {
			errors.put("difficulty", "Please provide the difficulty");
		}
		if (questionDto.answer <= 0) {
			errors.put("answer", "Please provide the answer");
		}
		if (questionDto.mark <= 0) {
			errors.put("mark", "Please provide the mark");
		}

		String redirectPage = "redirect:/viewQuestions";
		if (errors.size() <= 0) {
			updateQuestion(questionDto);
			model.addAttribute("questions", questionService.getQuestions().values());
			model.addAttribute("updationStatus", "UPDATED");
		} else {
			model.addAttribute("errors", errors);
			redirectPage = "redirect:/updateQuestion?id=" + questionDto.id;
		}

		return redirectPage;

	}

	private void updateQuestion(QuestionDto questoinDto) {
		int questionId = questoinDto.id;

		Question question = questionService.getQuestion(questionId);
		question.setQuestionTitle(questoinDto.questionTitle);

//		String options[] = optionsValue.split(":");

		List<String> optionsList = questoinDto.questionOptions;

		question.questionOptions.clear();

		for (String optionTitle : optionsList) {
			QuestionOption option = new QuestionOption();
			option.setOptionTitle(optionTitle);
			question.setOption(option);

		}
		question.setTopicTag(questoinDto.topicTag);
		question.setDifficultyTag(questoinDto.difficultyTag);
		question.setAnswer(questoinDto.answer);
		question.setMark(questoinDto.mark);

		questionService.update(question, questionId);
	}
}
