package com.epam.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epam.dto.QuestionDto;
import com.epam.exceptions.InValidQuestionDeletion;
import com.epam.service.admin.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	QuestionService questionService;

	private static final String QUESTIONS = "questions";

	@RequestMapping("/createQuestion")
	public String createQuestion() {
		return "admin/question/createQuestion";
	}

	@RequestMapping("/viewQuestions")
	public String viewQuestions(Model model) {

		model.addAttribute(QUESTIONS, questionService.getQuestions().values());
		return "admin/question/viewQuestions";
	}

	@RequestMapping(value = "/updateQuestion", params = "id")
	public String updateQuestion(@RequestParam(value = "id") String id, Model model) {
		int questionId = Integer.parseInt(id);
		model.addAttribute("question", questionService.getQuestion(questionId));
		return "admin/question/updateQuestion";

	}

	@RequestMapping(value = "/addQuestion")
	public String addTheQuestion(QuestionDto questionDto, BindingResult bindingResult, Model model,
			HttpServletResponse response) {

		String redirectPage = "redirect:/viewQuestions";
		if (!bindingResult.hasErrors()) {
			questionService.createQuestion(questionDto);
		} else {
			redirectPage = "admin/question/createQuestion";
		}
		return redirectPage;
	}

	@RequestMapping(value = "/deleteTheQuestion", params = "id")
	public String deleteQuestion(@RequestParam(value = "id") String questionId, RedirectAttributes redirectAttributes) {

		String deletionStatus = "Not Deleted";

		int id = Integer.parseInt(questionId);

		try {
			questionService.validateQuestionId(id);

			if (questionService.delete(id)) {
				deletionStatus = "Deleted";
			}
		} catch (InValidQuestionDeletion e) {

			deletionStatus = "Unable to delete, Question is part of some quiz";
		}

//		redirectAttributes.addFlashAttribute(QUESTIONS, questionService.getQuestions().values());
		redirectAttributes.addFlashAttribute("deletionStatus", deletionStatus);
		return "redirect:/viewQuestions";
	}

	@RequestMapping(value = "/updateTheQuestion")
	public String updateTheQuestion(QuestionDto questionDto, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {

		String redirectPage = "redirect:/viewQuestions";
		if (!bindingResult.hasErrors()) {

			questionService.update(questionDto);

			redirectAttributes.addFlashAttribute("updationStatus", "UPDATED");
		} else {
			redirectAttributes.addFlashAttribute("updationStatus", "UNABLE TO UPDATE");
			redirectPage = "redirect:/updateQuestion?id=" + questionDto.id;
		}

		return redirectPage;

	}

}
