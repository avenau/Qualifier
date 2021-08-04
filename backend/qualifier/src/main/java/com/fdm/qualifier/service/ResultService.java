package com.fdm.qualifier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.repository.ResultRepository;

@Service
public class ResultService {
	private ResultRepository resultRepo;

	public ResultService(ResultRepository resultRepo) {
		super();
		this.resultRepo = resultRepo;
	}
	
	public void delete(Result result) {
		resultRepo.delete(result);
	}
	
	public List<Result> findByQuiz(Quiz quiz) {
		return resultRepo.findByQuiz(quiz);
	}
}
