package com.fdm.qualifier.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Question.QuestionType;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.repository.QuestionRepository;
import com.fdm.qualifier.repository.QuizRepository;

@Service
public class QuestionService {
	private QuestionRepository questionRepository;
	private QuizRepository quizRepository;
	
	@Autowired
	public QuestionService(QuestionRepository questionRepository, QuizRepository quizRepository) {
		this.questionRepository = questionRepository;
		this.questionRepository = questionRepository;
	}

	public Question createNewQuestion(Quiz quizId, String content, QuestionType type, int marks, Object image) {
		Question question = new Question(content, type, marks, null, quizId);
		questionRepository.save(question);
		quizId.setQuestionCount(quizId.getQuestionCount() + 1);
		return question;
	}

	public Optional<Question> findById(int id) {
		return questionRepository.findById(id);
	}
	
}
