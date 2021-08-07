package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.SubmittedAnswer;
import com.fdm.qualifier.repository.SubmittedAnswerRepository;

/**
 * SubmittedAnswer Service
 * @author William
 *
 */
@Service
public class SubmittedAnswerService {
	private SubmittedAnswerRepository submittedRepo;

	@Autowired
	public SubmittedAnswerService(SubmittedAnswerRepository submittedRepo) {
		super();
		this.submittedRepo = submittedRepo;
	}
	
	/**
	 * Save subbitted answer to repo
	 * @param submitted
	 * @return
	 */
	public SubmittedAnswer save(SubmittedAnswer submitted){
		return submittedRepo.save(submitted);
	}
	
	/**
	 * Delete submitted answer from repo
	 * @param submitted
	 */
	public void delete(SubmittedAnswer submitted){
		submittedRepo.delete(submitted);
	}
	
	/**
	 * Get optional submitted answer from repo by id
	 * @param id
	 * @return
	 */
	public Optional<SubmittedAnswer> findById(int id){
		return submittedRepo.findById(id);
	}
	
	/**
	 * Create short answer submitted answer and save to repo
	 * @param question
	 * @param answerContent
	 * @return
	 */
	public SubmittedAnswer createNewShortAnswer(Question question, String answerContent) {
		SubmittedAnswer submittedAnswer = new SubmittedAnswer(question, answerContent);
		submittedRepo.save(submittedAnswer);
		return submittedAnswer;
	}

	/**
	 * Create select answer and save to repo
	 * @param question
	 * @param answer
	 * @param answerContent
	 * @return
	 */
	public SubmittedAnswer createNewSelectedAnswer(Question question, Answer answer, String answerContent) {
		SubmittedAnswer submittedAnswer = new SubmittedAnswer(question, answer, answerContent);
		submittedRepo.save(submittedAnswer);
		return submittedAnswer;
	}
	
	public List<SubmittedAnswer> findByQuestion(Question question) {
		return submittedRepo.findSubmittedAnswerByQuestion(question);
	}
	

}
