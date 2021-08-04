package com.fdm.qualifier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.repository.AnswerRepository;

@Service
public class AnswerService {
	private AnswerRepository answerRepo;

	@Autowired
	public AnswerService(AnswerRepository answerRepo) {
		super();
		this.answerRepo = answerRepo;
	}
	
	public List<Answer> findByQuestion(Question question) {
		return answerRepo.findByQuestion(question);
	}
	
	public void delete(Answer answer) {
		answerRepo.delete(answer);
	}
}
