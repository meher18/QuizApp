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
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.QuestionDto;
import com.epam.service.admin.QuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class QuestionRestController {

	@Autowired
	QuestionService questionService;

	@Operation(description = "For Fetching all the questions")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Questions Fetched") })
	@GetMapping("/questions")
	public ResponseEntity<List<QuestionDto>> all() {
		return new ResponseEntity<>(questionService.getQuestions().values().stream().toList(), HttpStatus.OK);
	}

	@Operation(description = "For Fetching single question")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Question Fetched") })
	@GetMapping("/questions/{id}")
	public ResponseEntity<QuestionDto> oneQuestion(@PathVariable(value = "id") int id) {

		return new ResponseEntity<>(questionService.getQuestion(id), HttpStatus.OK);
	}

	@Operation(description = "For creating a new question")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "201", description = "Question created") })
	@PostMapping("/questions")
	public ResponseEntity<QuestionDto> newQuestion(@RequestBody QuestionDto questionDto) {
		return new ResponseEntity<>(questionService.createQuestion(questionDto), HttpStatus.CREATED);
	}

	@Operation(description = "For updating a  question")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "201", description = "Question updated") })
	@PutMapping("/questions/{id}")
	public ResponseEntity<QuestionDto> updateQuestion(@RequestBody QuestionDto questionDto, @PathVariable int id) {
		return new ResponseEntity<>(questionService.update(questionDto, id), HttpStatus.CREATED);
	}

	@Operation(description = "For deleting a  question")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "204", description = "Question deleted") })
	@DeleteMapping("/questions/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable(value = "id") int id) {

		questionService.delete(id);
		return new ResponseEntity<>("Question Deleted", HttpStatus.NO_CONTENT);
	}
}
