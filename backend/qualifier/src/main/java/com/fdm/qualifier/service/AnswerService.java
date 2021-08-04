package com.fdm.qualifier.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Question.QuestionType;
import com.fdm.qualifier.repository.AnswerRepository;

@Service
public class AnswerService {
	private AnswerRepository answerRepository;

	@Autowired
	public AnswerService(AnswerRepository answerRepository) {
		super();
		this.answerRepository = answerRepository;
	}

	public Answer createNewAnswer(String content, Question questionId, boolean isCorrect) {
		Answer answer = new Answer(content, questionId, isCorrect);
		answerRepository.save(answer);
		return answer;
	}

	public Optional<Answer> finById(int id) {
		return answerRepository.findById(id);
	}
	
	
}
