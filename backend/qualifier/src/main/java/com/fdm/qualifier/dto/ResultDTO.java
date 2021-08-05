package com.fdm.qualifier.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.SubmittedAnswer;

public class ResultDTO {
	
	private int resultId;
	private double mark;
	private boolean passed;
	private boolean marked;
	private int traineeId;
	private ResultQuizDTO quiz;
	private List<SubmittedAnswerDTO> submittedAnswers;

	public ResultDTO() {
		super();
	}
	
	@JsonCreator
	public ResultDTO(@JsonProperty("resultId") int resultId, 
			@JsonProperty("mark") double mark, 
			@JsonProperty("passed") boolean passed, 
			@JsonProperty("marked") boolean marked, 
			@JsonProperty("traineeId") int traineeId, 
			@JsonProperty("quiz") ResultQuizDTO quiz,
			@JsonProperty("submittedAnswers") List<SubmittedAnswerDTO> submittedAnswers) {
		super();
		this.resultId = resultId;
		this.mark = mark;
		this.passed = passed;
		this.marked = marked;
		this.traineeId = traineeId;
		this.quiz = quiz;
		this.submittedAnswers = submittedAnswers;
	}


	public ResultDTO(Result result) {
		this.resultId = result.getResultId();
		this.mark = result.getMark();
		this.passed = result.isPassed();
		this.marked = result.isMarked();
		this.traineeId = result.getTrainee().getUserId();
		this.quiz = new ResultQuizDTO(result.getQuiz());
		this.submittedAnswers = new ArrayList<SubmittedAnswerDTO>();
		for(SubmittedAnswer submittedAnswer : result.getSubmittedAnswers()) {
			this.submittedAnswers.add(new SubmittedAnswerDTO(submittedAnswer));
		}
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public int getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

	public ResultQuizDTO getQuiz() {
		return quiz;
	}

	public void setQuiz(ResultQuizDTO quiz) {
		this.quiz = quiz;
	}

	public List<SubmittedAnswerDTO> getSubmittedAnswers() {
		return submittedAnswers;
	}

	public void setSubmittedAnswers(List<SubmittedAnswerDTO> submittedAnswers) {
		this.submittedAnswers = submittedAnswers;
	}

	@Override
	public String toString() {
		return "ResultDTO [resultId=" + resultId + ", mark=" + mark + ", passed=" + passed + ", marked=" + marked
				+ ", traineeId=" + traineeId + ", quiz=" + quiz + ", submittedAnswers=" + submittedAnswers + "]";
	}

}
