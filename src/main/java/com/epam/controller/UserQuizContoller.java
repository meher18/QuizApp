package com.epam.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.entity.Quiz;
import com.epam.service.admin.quizservice.QuizService;
import com.epam.service.user.UserQuizService;

@Controller
public class UserQuizContoller {
	@Autowired
	QuizService quizService;

	@Autowired
	UserQuizService userQuizService;

	@RequestMapping("/viewHostedQuizzes")
	public String viewAllHostedQuizzes(Model model) {
		List<Quiz> hostedQuizzes = quizService.getAllQuizzes().values().stream()
				.filter(quiz -> quiz.getQuizTag().equalsIgnoreCase("HOSTED")).collect(Collectors.toList());

		model.addAttribute("hostedQuizzes", hostedQuizzes);
		return "user/viewHostedQuizzes";
	}

	@RequestMapping(value = "/takeTheQuiz", params = { "quizId" })
	public String takeQuiz(@RequestParam(value = "quizId") String id, Model model) {

		int quizId = Integer.parseInt(id);

		// take from the quizzes, not directly from db
		Quiz quiz = quizService.getQuiz(quizId);

		model.addAttribute("quiz", quiz);
		return "user/quiz";
	}
}
