package com.fdm.qualifier.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int questionId;
	private String content;
	private QuestionType type;
	private int points;
	@Lob
	private byte[] image;

	@ManyToOne
	@JsonBackReference(value = "quiz")

	private Quiz quiz;
	
	@OneToMany(mappedBy = "question")
	private List<Answer> answers;
	
	@OneToMany(mappedBy = "question")
	private List<SubmittedAnswer> submittedAnswers;
	
	public enum QuestionType {
		MUTIPLE_CHOICE,
		MULTI_SELECT,
		SHORT_ANSWER
	}

	public Question() {
		super();
	}
	
	public Question(String content, QuestionType type, int points, byte[] image, Quiz quiz) {
		super();
		this.content = content;
		this.type = type;
		this.points = points;
		this.image = image;
		this.quiz = quiz;
	}

	public Question(String content, QuestionType type, int points, byte[] image, Quiz quiz, List<Answer> answers,
			List<SubmittedAnswer> submittedAnswers) {
		super();
		this.content = content;
		this.type = type;
		this.points = points;
		this.image = image;
		this.quiz = quiz;
		this.answers = answers;
		this.submittedAnswers = submittedAnswers;
	}

	public Question(Quiz quiz, String content, QuestionType type, int points,
			List<Answer> answers) {
		super();
		this.quiz = quiz;
		this.content = content;
		this.type = type;
		this.points = points;
		this.answers = answers;
		this.image = "random".getBytes();;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public void addAnswers(Answer answer) {
		this.answers.add(answer);
	}
	

	public List<SubmittedAnswer> getSubmittedAnswers() {
		return submittedAnswers;
	}

	public void setSubmittedAnswers(List<SubmittedAnswer> submittedAnswers) {
		this.submittedAnswers = submittedAnswers;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", content=" + content + ", type=" + type + ", points=" + points
				+ ", image=" + Arrays.toString(image) + "]";

	}
	
	
	
	

	
}
