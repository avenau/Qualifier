package com.fdm.qualifier.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Question;

import com.fdm.qualifier.model.Question.QuestionType;

import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.repository.QuestionRepository;

/**
 * Question Service
 * 
 * @author William
 *
 */
@Service
public class QuestionService {
	private QuestionRepository questionRepository;

	@Autowired
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	/**
	 * Creates a question in repo
	 * 
	 * @param quizId
	 * @param content
	 * @param type
	 * @param marks
	 * @return
	 */
	public Question createNewQuestion(Quiz quizId, String content, QuestionType type, int marks) {
		Question question = new Question(content, type, marks, null, quizId);
		questionRepository.save(question);
		quizId.setQuestionCount(quizId.getQuestionCount() + 1);
		return question;
	}


	/**
	 * Find all questions based on quiz id from repo
	 * 
	 * @param quiz
	 * @return
	 */
	public List<Question> findAllQuestionsOfQuizID(Quiz quiz) {
		return questionRepository.findQuestionByQuiz(quiz);
	}

	/**
	 * Delete a question from repo
	 * 
	 * @param question
	 */
	public void delete(Question question) {
		questionRepository.delete(question);
	}

	/**
	 * Find question by id from repo
	 * 
	 * @param id
	 * @return
	 */
	public Question findById(int id) {
		Optional<Question> question = questionRepository.findById(id);

		if (question.isEmpty()) {
			return null;
		} else {
			return question.get();
		}
	}

	/**
	 * Save a question to repo
	 * 
	 * @param question
	 * @return
	 */
	public Question save(Question question) {
		return questionRepository.save(question);
	}

}
