package com.epam.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.QuizDto;
import com.epam.service.admin.QuizService;

@RestController
public class QuizRestController {

	@Autowired
	QuizService quizService;

	@GetMapping("/quizzes")
	public ResponseEntity<List<QuizDto>> all() {
		return new ResponseEntity<>(quizService.getAllQuizzes().values().stream().toList(), HttpStatus.OK);
	}

	@GetMapping("/quizzes/{quizId}")
	public ResponseEntity<QuizDto> oneQuiz(@PathVariable(value = "quizId") int id) {

		return new ResponseEntity<>(quizService.getQuiz(id), HttpStatus.OK);
	}

	@PostMapping("/quizzes")
	public ResponseEntity<QuizDto> newQuiz(@Valid @RequestBody QuizDto quizDto) {
		return new ResponseEntity<>(quizService.createQuiz(quizDto), HttpStatus.CREATED);
	}

	@PutMapping("/quizzes")
	public ResponseEntity<QuizDto> updateQuiz(@Valid @RequestBody QuizDto quizDto) {
		return new ResponseEntity<>(quizService.update(quizDto), HttpStatus.CREATED);
	}

	@DeleteMapping("/quizzes/{quizId}")
	public ResponseEntity<String> deleteQuiz(@PathVariable(value = "quizId") int id) {
		quizService.delete(id);
		return new ResponseEntity<>("Quiz Deleted", HttpStatus.OK);
	}
}
