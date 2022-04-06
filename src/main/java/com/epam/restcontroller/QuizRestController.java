package com.epam.restcontroller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.QuizDto;
import com.epam.service.admin.QuizService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class QuizRestController {

	@Autowired
	QuizService quizService;

	@Operation(description = "For fetching all quizzes")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Quizzes Fetched") })
	@GetMapping("/quizzes")
	public ResponseEntity<List<QuizDto>> all() {
		return new ResponseEntity<>(quizService.getAllQuizzes().values().stream().toList(), HttpStatus.OK);
	}

	@Operation(description = "For fetching a single quiz")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Quiz Fetched") })
	@GetMapping("/quizzes/{quiz_id}")
	public ResponseEntity<QuizDto> oneQuiz(@PathVariable(value = "quiz_id") int id) {

		return new ResponseEntity<>(quizService.getQuiz(id), HttpStatus.OK);
	}

	@Operation(description = "For creating a new quiz")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "201", description = "Quiz created") })
	@PostMapping("/quizzes")
	public ResponseEntity<QuizDto> newQuiz(@RequestBody QuizDto quizDto,
			@RequestParam(value = "questions") String[] questions) {
		return new ResponseEntity<>(quizService.createQuiz(quizDto, questions), HttpStatus.CREATED);
	}

	@Operation(description = "For udpating a  quiz")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "201", description = "Quiz updated") })
	@PutMapping("/quizzes")
	public ResponseEntity<QuizDto> updateQuiz(@RequestBody QuizDto quizDto, @RequestParam String[] questions) {
		return new ResponseEntity<>(quizService.update(quizDto, questions), HttpStatus.CREATED);
	}

	@Operation(description = "For deleting a quiz")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "204", description = "Quiz deleted") })
	@DeleteMapping("/quizzes/{quiz_id}")
	public ResponseEntity<String> deleteQuiz(@PathVariable(value = "quiz_id") int id) {
		quizService.delete(id);
		return new ResponseEntity<>("Question Deleted", HttpStatus.NO_CONTENT);
	}
}
