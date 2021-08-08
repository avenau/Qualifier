package com.fdm.qualifier.service;


import java.util.Optional;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;

import com.fdm.qualifier.repository.AnswerRepository;

/**
 * Answer Service
 * @author William
 *
 */
@Service
public class AnswerService {

	private AnswerRepository answerRepository;

	@Autowired
	public AnswerService(AnswerRepository answerRepository) {
		super();
		this.answerRepository = answerRepository;
	}

	/**
	 * Creates new answer in repo
	 * @param content
	 * @param questionId
	 * @param isCorrect
	 * @return
	 */
	public Answer createNewAnswer(String content, Question questionId, boolean isCorrect) {
		Answer answer = new Answer(content, questionId, isCorrect);
		answerRepository.save(answer);
		return answer;
	}

	/**
	 * Gets answer by id
	 * @param id
	 * @return
	 */
	public Optional<Answer> finById(int id) {
		return answerRepository.findById(id);
	}
	
	/**
	 * Finds answer from repo by question
	 * @param question
	 * @return
	 */
	public List<Answer> findByQuestion(Question question) {
		return answerRepository.findByQuestion(question);
	}
	
	/**
	 * Deletes answer from repo
	 * @param answer
	 */
	public void delete(Answer answer) {
		answerRepository.delete(answer);
	}
	
}
