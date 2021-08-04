package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
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
	
	public List<Question> findAllQuestionsOfQuizID(Quiz quiz) {
		return questionRepository.findQuestionByQuiz(quiz);
	}
	
	public void delete(Question question) {
		questionRepository.delete(question);
	}
	
	public Question findById(int id) {
		Optional<Question> question = questionRepository.findById(id);
		
		if(question.get() == null) {
			return null;
		} else {
			return question.get();
		}
	}
}
