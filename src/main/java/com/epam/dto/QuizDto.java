package com.epam.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.epam.entity.Question;
import com.epam.util.Constants;

public class QuizDto {

	public int id;

	@NotEmpty(message = "Quiz Name cannot be empty")
	public String quizName;

	public long quizCode;

	@Size(min = 1, message = "add atleast one question")
	public List<Question> questions = new ArrayList<>();

	public int totalMarks = 0;

	public String quizTag = Constants.QUIZ_NOT_HOSTED;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public long getQuizCode() {
		return quizCode;
	}

	public void setQuizCode(long quizCode) {
		this.quizCode = quizCode;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questionIds) {
		this.questions = questionIds;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getQuizTag() {
		return quizTag;
	}

	public void setQuizTag(String quizTag) {
		this.quizTag = quizTag;
	}

	@Override
	public String toString() {
		return "QuizDto [id=" + id + ", quizName=" + quizName + ", quizCode=" + quizCode + ", questionIds="
				+ questions + ", totalMarks=" + totalMarks + ", quizTag=" + quizTag + "]";
	}

}
