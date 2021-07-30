package com.fdm.qualifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.repository.AnswerRepository;
import com.fdm.qualifier.repository.QuestionRepository;
import com.fdm.qualifier.repository.QuizRepository;
import com.fdm.qualifier.repository.ResultRepository;

@Service
public class QuizService {
	private ResultRepository resultRepo;
	private QuizRepository quizRepo;
	private QuestionRepository questionRepository;
	private AnswerRepository answerRepository;

	@Autowired
	public QuizService(ResultRepository resultRepo, QuizRepository quizRepo, QuestionRepository questionRepository,
			AnswerRepository answerRepository) {
		super();
		this.resultRepo = resultRepo;
		this.quizRepo = quizRepo;
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
	}
	
	public Quiz save(Quiz quiz) {
		return quizRepo.save(quiz);
	}

}
