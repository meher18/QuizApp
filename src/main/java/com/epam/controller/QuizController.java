package com.epam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.dto.QuizDto;
import com.epam.exceptions.QuizException;
import com.epam.service.admin.QuestionService;
import com.epam.service.admin.QuizService;

@Controller
public class QuizController {

	@Autowired
	QuizService quizService;

	@Autowired
	QuestionService questionService;

	@RequestMapping("/viewQuizzes")
	public String viewQuizzes(Model model) {
		model.addAttribute("quizzes", quizService.getAllQuizzes().values());
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
	public String hostTheQuiz(@RequestParam(value = "id") String id, Model model) throws QuizException {

		int quizId = Integer.parseInt(id);
		quizService.hostQuiz(quizId);
//		try {
//			quizService.getAllQuizzes().values();
//		} catch (Exception e) {
//			QuizException q = new QuizException();
//			q.setModel(null);
//			q.setErrorMessage("Quizzes not Found");
//			throw q;
//		}

		model.addAttribute("quizzes", quizService.getAllQuizzes().values());
		return "redirect:/viewQuizzes";
	}

	@RequestMapping(value = "/deleteTheQuiz", params = { "id" })
	public String deleteTheQuiz(@RequestParam(value = "id") String id, Model model) {

		int quizId = Integer.parseInt(id);
		quizService.delete(quizId);

		model.addAttribute("quizzes", quizService.getAllQuizzes().values());
		return "redirect:/viewQuizzes";
	}

	@RequestMapping(value = "/createTheQuiz")
	public String createTheQuiz(QuizDto quizDto, String[] questions, Model model) {

		Map<String, String> errors = new HashMap<>();

		if (quizDto.quizName.equals("")) {
			errors.put("quizName", "Please provide the Quiz Name");
		}

		if (quizDto.questions.isEmpty()) {
			errors.put("questionId", "Please provide some questions");
		}

		String redirectPage = "admin/quiz/createQuiz";

		if (errors.size() <= 0) {

			quizService.createQuiz(quizDto, questions);

			model.addAttribute("quizUpdationStatus", "UPDATED");
			model.addAttribute("quizzes", quizService.getAllQuizzes().values());

			redirectPage = "redirect:/viewQuizzes";
		} else {
			model.addAttribute("questions", questionService.getQuestions().values());
			model.addAttribute("errors", errors);
		}

		return redirectPage;
	}

	@RequestMapping(value = "/updateTheQuiz", params = { "id", "quizName", "questionId", "quizTag" })
	public String updateTheQuiz(QuizDto quizDto, String[] questions, Model model) {

		Map<String, String> errors = new HashMap<>();

		if (quizDto.quizName == "") {
			errors.put("quizName", "Please provide the Quiz Name");
		}

		if (quizDto.questions.isEmpty()) {
			errors.put("questionId", "Please provide some questions");
		}

		String redirectPage = "redirect:/updateQuiz?id=" + quizDto.id;
		if (errors.size() <= 0) {

			quizService.update(quizDto, questions);

			model.addAttribute("quizUpdationStatus", "UPDATED");
			model.addAttribute("quizzes", quizService.getAllQuizzes().values());
			redirectPage = "redirect:/viewQuizzes";
		}

		return redirectPage;
	}
}
