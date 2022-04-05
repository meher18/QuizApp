package com.epam.dto;

import javax.validation.constraints.NotEmpty;

import com.epam.entity.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class QuestionOptionDto {

	public int id;

	@NotEmpty(message = "title cannot be empty")
	public String title;

	@JsonIgnore
	public Question question;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setTitle(String optionObj) {
		this.title = optionObj;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "QuestionOption [id=" + id + ", title=" + title + ", question=" + question + "]";
	}
}
