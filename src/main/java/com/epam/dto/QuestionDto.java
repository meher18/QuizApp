package com.epam.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class QuestionDto {

	public int id;

	@NotEmpty(message = "Question title cannot be empty")
	public String questionTitle;

	@Min(value = 1, message = "Minimum mark is 1")
	@Max(value = 30, message = "Maximum mark is 30")
	public int mark = 0;

	@Min(value = 1, message = "answer should not be less than 1")
	public int answer;

	@NotEmpty(message = "Topic should not be empty")
	public String topicTag;

	@NotEmpty(message = "Difficulty should not be empty")
	public String difficultyTag;

	@Size(min = 1, message = "Please provide some options")
	public List<@Valid QuestionOptionDto> questionOptions = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<QuestionOptionDto> getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(List<QuestionOptionDto> questionOptions) {
		this.questionOptions = questionOptions;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public String getTopicTag() {
		return topicTag;
	}

	public void setTopicTag(String topicTag) {
		this.topicTag = topicTag;
	}

	public String getDifficultyTag() {
		return difficultyTag;
	}

	public void setDifficultyTag(String difficultyTag) {
		this.difficultyTag = difficultyTag;
	}
}
