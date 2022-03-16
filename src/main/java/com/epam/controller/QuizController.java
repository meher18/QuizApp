package com.epam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.entity.Quiz;
import com.epam.service.admin.questionservice.QuestionService;
import com.epam.service.admin.quizservice.QuizService;

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
		Quiz quiz = quizService.getQuiz(quizId);
		model.addAttribute("quiz", quiz);
		model.addAttribute("questions", questionService.getQuestions().values());
		List<String> idList = quiz.getQuestions().values().stream().map(q -> q.id + "").collect(Collectors.toList());

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

	@RequestMapping(value = "/createTheQuiz", params = { "quizName", "questionId" })
	public String createTheQuiz(@RequestParam(value = "quizName") String quizName,
			@RequestParam(value = "questionId") String questionId, Model model) {

		Map<String, String> errors = new HashMap<String, String>();

		if (quizName == "") {
			errors.put("quizName", "Please provide the Quiz Name");
		}

		if (questionId == "") {
			errors.put("questionId", "Please provide some questions");
		}

		String redirectPage = "admin/quiz/createQuiz";

		if (errors.size() <= 0) {
			Quiz quiz = new Quiz();
			quiz.setQuizName(quizName);

			String[] questionIds = questionId.split(",");
			Stream.of(questionIds).forEach(id -> {
				quizService.selectQuestionAndAddToQuiz(quiz, Integer.parseInt(id));
			});

			model.addAttribute("quizUpdationStatus", "UPDATED");
			model.addAttribute("quizzes", quizService.getAllQuizzes().values());
			quizService.saveQuiz(quiz);
			redirectPage = "redirect:/viewQuizzes";
		} else {
			model.addAttribute("questions", questionService.getQuestions().values());
			model.addAttribute("errors", errors);
		}

		return redirectPage;
	}

	@RequestMapping(value = "/updateTheQuiz", params = { "id", "quizName", "questionId", "quizTag" })
	public String updateTheQuiz(@RequestParam(value = "quizName") String quizName,
			@RequestParam(value = "id") String qId, @RequestParam(value = "questionId") String questionId,
			@RequestParam(value = "quizTag") String quizTag, Model model) {

		int quizId = Integer.parseInt(qId);
		Map<String, String> errors = new HashMap<String, String>();

		if (quizName == "") {
			errors.put("quizName", "Please provide the Quiz Name");
		}

		if (questionId == "") {
			errors.put("questionId", "Please provide some questions");
		}

		String redirectPage = "redirect:/updateQuiz?id="+quizId;
		if(errors.size() <= 0)
		{
			Quiz quiz = quizService.getQuiz(quizId);
			quiz.setQuizName(quizName);
			String[] questionIds = questionId.split(",");
			quiz.getQuestions().clear();
			quiz.setTotalMarks(0);
			Stream.of(questionIds).forEach(id -> {
				quizService.selectQuestionAndAddToQuiz(quiz, Integer.parseInt(id));
			});

			quiz.setQuizTag(quizTag);

			quizService.update(quiz, quizId);

			model.addAttribute("quizUpdationStatus", "UPDATED");
			model.addAttribute("quizzes", quizService.getAllQuizzes().values());
			redirectPage = "redirect:/viewQuizzes";
		}
		
		return redirectPage;
	}
}
