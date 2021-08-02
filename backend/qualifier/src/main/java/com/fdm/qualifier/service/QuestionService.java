package com.fdm.qualifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.repository.QuestionRepository;

@Service
public class QuestionService {
	private QuestionRepository questionRepository;
	
	@Autowired
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}
	
	public Question save(Question question) {
		return questionRepository.save(question);
	}

}
