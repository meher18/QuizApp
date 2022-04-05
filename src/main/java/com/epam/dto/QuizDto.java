package com.epam.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.epam.entity.Question;
import com.epam.util.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDto {

	public int id;

	@NotEmpty(message = "Quiz Name cannot be empty")
	public String quizName;

	public long quizCode;

	@Size(min = 1, message = "add atleast one question")
	public List<Question> questions = new ArrayList<>();

	public int totalMarks = 0;

	public String quizTag = Constants.QUIZ_NOT_HOSTED;

}
