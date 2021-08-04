package com.fdm.qualifier.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.SubmittedAnswer;
import com.fdm.qualifier.repository.SubmittedAnswerRepository;

@Service
public class SubmittedAnswerService {
	private SubmittedAnswerRepository submittedRepo;

	@Autowired
	public SubmittedAnswerService(SubmittedAnswerRepository submittedRepo) {
		super();
		this.submittedRepo = submittedRepo;
	}
	
	public SubmittedAnswer save(SubmittedAnswer submitted){
		return submittedRepo.save(submitted);
	}
	
	public void delete(SubmittedAnswer submitted){
		submittedRepo.delete(submitted);
	}
	
	public Optional<SubmittedAnswer> findById(int id){
		return submittedRepo.findById(id);
	}
	
	public SubmittedAnswer createNewShortAnswer(Question question, String answerContent) {
		SubmittedAnswer submittedAnswer = new SubmittedAnswer(question, answerContent);
		submittedRepo.save(submittedAnswer);
		return submittedAnswer;
	}

	public SubmittedAnswer createNewSelectedAnswer(Question question, Answer answer, String answerContent) {
		SubmittedAnswer submittedAnswer = new SubmittedAnswer(question, answer, answerContent);
		submittedRepo.save(submittedAnswer);
		return submittedAnswer;
	}
	
	

}
