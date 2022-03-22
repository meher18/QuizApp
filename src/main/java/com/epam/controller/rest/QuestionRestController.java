package com.epam.controller.rest;

import java.util.List;

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

import com.epam.dto.QuestionDto;
import com.epam.service.admin.QuestionService;

@RestController
public class QuestionRestController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/questions")
	public ResponseEntity<List<QuestionDto>> all() {
		return new ResponseEntity<>(questionService.getQuestions().values().stream().toList(), HttpStatus.OK);
	}

	@GetMapping("/questions/{question_id}")
	public ResponseEntity<QuestionDto> oneQuestion(@PathVariable(value = "question_id") int id) {

		return new ResponseEntity<>(questionService.getQuestion(id), HttpStatus.OK);
	}

	@PostMapping("/questions")
	public ResponseEntity<QuestionDto> newQuestion(@RequestBody QuestionDto questionDto) {
		return new ResponseEntity<>(questionService.createQuestion(questionDto), HttpStatus.OK);
	}

	@PutMapping("/questions")
	public ResponseEntity<QuestionDto> updateQuestion(@RequestBody QuestionDto questionDto) {
		return new ResponseEntity<>(questionService.update(questionDto), HttpStatus.OK);
	}

	@DeleteMapping("/questions/{question_id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable(value = "question_id") int id) {

		questionService.delete(id);
		return new ResponseEntity<>("Question Deleted", HttpStatus.OK);
	}
}
