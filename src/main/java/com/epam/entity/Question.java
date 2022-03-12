package com.epam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )
	@Autowired
	@Qualifier("questionOptions")
	public List<QuestionOption> questionOptions;

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

	public List<QuestionOption> getOptions() {
		return questionOptions;
	}

	public void setOption(QuestionOption questionOption) {
		questionOption.setQuestion(this);
		questionOptions.add(questionOption);
	}

	public void updateOption(int index, QuestionOption questionOption) {
		this.questionOptions.set(index, questionOption);
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

	public void setOptions(List<QuestionOption> options) {

		for (QuestionOption option : options) {
			option.setQuestion(this);
		}
		this.questionOptions = options;
	}

}
