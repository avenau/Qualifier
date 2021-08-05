package com.fdm.qualifier.dto;

import java.util.Arrays;
import java.util.List;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Question.QuestionType;

public class ResultQuestionDTO {
	private int questionId;
	private String content;
	private QuestionType type;
	private int points;
	private byte[] image;
	private int quizId;
	private List<Answer> answers;

	public ResultQuestionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultQuestionDTO(int questionId, String content, QuestionType type, int points, byte[] image, int quizId,
			List<Answer> answers) {
		super();
		this.questionId = questionId;
		this.content = content;
		this.type = type;
		this.points = points;
		this.image = image;
		this.quizId = quizId;
		this.answers = answers;
	}

	public ResultQuestionDTO(Question question) {
		super();
		this.questionId = question.getQuestionId();
		this.content = question.getContent();
		this.type = question.getType();
		this.points = question.getPoints();
		this.image = question.getImage();
		this.quizId = question.getQuiz().getQuizId();
		this.answers = question.getAnswers();
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
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

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "ResultQuestionDTO [questionId=" + questionId + ", content=" + content + ", type=" + type + ", points="
				+ points + ", image=" + Arrays.toString(image) + ", quizId=" + quizId + ", answers=" + answers + "]";
	}

}
