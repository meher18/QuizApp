package com.epam.dto;

import java.util.ArrayList;
import java.util.List;

import com.epam.util.Constants;

public class QuizDto {

	public int id;

	public String quizName;

	public long quizCode;

	// maybe it should be removed
	public List<String> questionIds = new ArrayList<>();

	public int totalMarks = 0;

	// we can set the quiz tag to "hosted" or "completed" so that user view quizzes
	// accordingly
	// NOT HOSTED is default
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

	public List<String> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<String> questionIds) {
		this.questionIds = questionIds;
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
				+ questionIds + ", totalMarks=" + totalMarks + ", quizTag=" + quizTag + "]";
	}
	

}
