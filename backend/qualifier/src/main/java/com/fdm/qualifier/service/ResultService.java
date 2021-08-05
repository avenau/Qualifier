package com.fdm.qualifier.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;

import com.fdm.qualifier.model.SubmittedAnswer;
import com.fdm.qualifier.model.Trainee;

import com.fdm.qualifier.repository.ResultRepository;

@Service
public class ResultService {

	private ResultRepository resultRepository;

	@Autowired
	public ResultService(ResultRepository resultRepository) {
		super();
		this.resultRepository = resultRepository;
	}

	public Result createNewResult(double mark, boolean passed, boolean marked, Trainee trainee, Quiz quiz, List<SubmittedAnswer> submittedAnswers) {
		Result result = new Result(mark, passed, marked, trainee, quiz, submittedAnswers);
		result = resultRepository.save(result);
		trainee.addResults(result);
		return result;
	}
	
	public void delete(Result result) {
		resultRepository.delete(result);
	}
	
	public List<Result> findByQuiz(Quiz quiz) {
		return resultRepository.findByQuiz(quiz);
	}
	
}
