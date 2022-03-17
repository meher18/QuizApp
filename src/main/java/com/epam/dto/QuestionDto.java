package com.epam.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionDto {

	public int id;

	public List<String> questionOptions = new ArrayList<>();

	public String questionTitle;
	public int mark = 0;
	public int answer;
	public String topicTag;
	public String difficultyTag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(List<String> questionOptions) {
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

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionOptions=" + questionOptions + ", questionTitle=" + questionTitle
				+ ", mark=" + mark + ", answer=" + answer + ", topicTag=" + topicTag + ", difficultyTag="
				+ difficultyTag + "]";
	}

}
