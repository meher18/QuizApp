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
import com.epam.entity.Quiz;
import com.epam.exceptions.QuizException;
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
	public String hostTheQuiz(@RequestParam(value = "id") String id, Model model) throws QuizException {

		int quizId = Integer.parseInt(id);
		quizService.hostQuiz(quizId);
		try {
			quizService.getAllQuizzes().values();
		}catch(Exception e) {
			QuizException q = new QuizException();
			q.setModel(null);
			q.setErrorMessage("Quizzes not Found");
			throw q;
		}
		
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
	public String createTheQuiz(QuizDto quizDto, Model model) {

		System.out.println(quizDto) ;
		Map<String, String> errors = new HashMap<String, String>();

		if (quizDto.quizName == "") {
			errors.put("quizName", "Please provide the Quiz Name");
		}

		if (quizDto.questionIds.size() <= 0) {
			errors.put("questionId", "Please provide some questions");
		}

		String redirectPage = "admin/quiz/createQuiz";

		if (errors.size() <= 0) {
			Quiz quiz = new Quiz();
			quiz.setQuizName(quizDto.quizName);

//			String[] questionIds = q.split(",");
			quizDto.questionIds.forEach(id -> {
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
	public String updateTheQuiz(QuizDto quizDto, Model model) {

		System.out.println(quizDto);
		int quizId = quizDto.id;
		Map<String, String> errors = new HashMap<String, String>();

		if (quizDto.quizName == "") {
			errors.put("quizName", "Please provide the Quiz Name");
		}

		if (quizDto.questionIds.size() <= 0) {
			errors.put("questionId", "Please provide some questions");
		}

		String redirectPage = "redirect:/updateQuiz?id=" + quizId;
		if (errors.size() <= 0) {
			Quiz quiz = quizService.getQuiz(quizId);
			quiz.setQuizName(quizDto.quizName);
//			String[] questionIds = questionId.split(",");
			quiz.getQuestions().clear();
			quiz.setTotalMarks(0);
			quizDto.questionIds.forEach(id -> {
				quizService.selectQuestionAndAddToQuiz(quiz, Integer.parseInt(id));
			});

			quiz.setQuizTag(quizDto.quizTag);

			quizService.update(quiz, quizId);

			model.addAttribute("quizUpdationStatus", "UPDATED");
			model.addAttribute("quizzes", quizService.getAllQuizzes().values());
			redirectPage = "redirect:/viewQuizzes";
		}

		return redirectPage;
	}
}
