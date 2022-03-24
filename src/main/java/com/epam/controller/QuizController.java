package com.epam.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epam.dto.QuizDto;
import com.epam.service.admin.QuestionService;
import com.epam.service.admin.QuizService;

@Controller
public class QuizController {

	@Autowired
	QuizService quizService;

	@Autowired
	QuestionService questionService;

	private static final String QUIZZES = "quizzes";

	@RequestMapping("/viewQuizzes")
	public String viewQuizzes(Model model) {
		model.addAttribute(QUIZZES, quizService.getAllQuizzes().values());
		return "admin/quiz/viewQuizzes";
	}

	@RequestMapping(value = "/updateQuiz", params = { "id" })
	public String updateQuiz(@RequestParam(value = "id") String id, Model model) {

		int quizId = Integer.parseInt(id);
		QuizDto quizDto = quizService.getQuiz(quizId);

		model.addAttribute("quiz", quizDto);
		model.addAttribute("questions", questionService.getQuestions().values());

		List<String> idList = quizDto.getQuestions().stream().map(q -> q.id + "").collect(Collectors.toList());
		model.addAttribute("ids", idList);

		return "admin/quiz/updateQuiz";
	}

	@RequestMapping("/createQuiz")
	public String createQuiz(Model model) {
		model.addAttribute("questions", questionService.getQuestions().values());
		return "admin/quiz/createQuiz";
	}

	@RequestMapping(value = "/hostTheQuiz", params = { "id" })
	public String hostTheQuiz(@RequestParam(value = "id") String id, Model model) {

		int quizId = Integer.parseInt(id);
		quizService.hostQuiz(quizId);
		model.addAttribute(QUIZZES, quizService.getAllQuizzes().values());
		return "redirect:/viewQuizzes";
	}

	@RequestMapping(value = "/deleteTheQuiz", params = { "id" })
	public String deleteTheQuiz(@RequestParam(value = "id") String id, Model model,
			RedirectAttributes redirectAttributes) {

		int quizId = Integer.parseInt(id);
		quizService.delete(quizId);

		redirectAttributes.addFlashAttribute("quizDeletionStatus", "Quiz Deleted");
		return "redirect:/viewQuizzes";
	}

	@RequestMapping(value = "/createTheQuiz")
	public String createTheQuiz(QuizDto quizDto, BindingResult bindingResult, String[] questions) {

		String redirectPage = "admin/quiz/createQuiz";

		if (!bindingResult.hasErrors()) {

			quizService.createQuiz(quizDto, questions);

			redirectPage = "redirect:/viewQuizzes";
		}

		return redirectPage;
	}

	@RequestMapping(value = "/updateTheQuiz")
	public String updateTheQuiz(QuizDto quizDto, String[] questions, BindingResult bindingResult) {

		quizService.update(quizDto, questions);

		return "redirect:/viewQuizzes";
	}
}
