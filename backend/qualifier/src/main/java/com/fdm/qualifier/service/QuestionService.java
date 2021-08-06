package com.fdm.qualifier.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Question;

import com.fdm.qualifier.model.Question.QuestionType;

import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.repository.QuestionRepository;

@Service
public class QuestionService {
	private QuestionRepository questionRepository;
//	private QuizRepository quizRepository;
	
	@Autowired
	public QuestionService(QuestionRepository questionRepository/*, QuizRepository quizRepository*/) {
		this.questionRepository = questionRepository;
//		this.questionRepository = questionRepository;
	}

	public Question createNewQuestion(Quiz quizId, String content, QuestionType type, int marks) {
		Question question = new Question(content, type, marks, null, quizId);
		questionRepository.save(question);
		quizId.setQuestionCount(quizId.getQuestionCount() + 1);
		return question;
	}


	public Optional<Question> findById1(int id) {
		return questionRepository.findById(id);
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
	
	public Question save (Question question) {
		return questionRepository.save(question);
	}

}
