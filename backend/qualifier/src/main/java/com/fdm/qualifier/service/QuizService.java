package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.dto.QuizDTO;
import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.repository.AnswerRepository;
import com.fdm.qualifier.repository.QuestionRepository;
import com.fdm.qualifier.repository.QuizRepository;
import com.fdm.qualifier.repository.ResultRepository;

@Service
public class QuizService {
	private ResultRepository resultRepo;
	private QuizRepository quizRepo;
	private QuestionRepository questionRepo;
	private AnswerRepository answerRepo;

	@Autowired
	public QuizService(ResultRepository resultRepo, QuizRepository quizRepo, QuestionRepository questionRepository,
			AnswerRepository answerRepository) {
		super();
		this.resultRepo = resultRepo;
		this.quizRepo = quizRepo;
		this.questionRepo = questionRepository;
		this.answerRepo = answerRepository;
	}
	
	public void save(Quiz newQuiz) {
		quizRepo.save(newQuiz);
	}

	public Quiz createNewQuiz(String name, String description, int duration, int passingMark) {
		Quiz newQuiz = new Quiz(name, description, duration, passingMark);
		quizRepo.save(newQuiz);
		return newQuiz;
	}
	
	public QuizDTO findQuizById(int id) {
		Optional<Quiz> quizOptional = quizRepo.findById(id);
		Quiz quiz = quizOptional.get();
		QuizDTO quizDTO = new QuizDTO(quiz);
		return quizDTO;
	}
	
	public List<Quiz> findAllQuiz(){
		return quizRepo.findAll();
	}
	
	
	public Result saveQuizResult(Quiz finishedQuiz, double mark, Trainee trainee) {
		boolean passed = mark >=finishedQuiz.getPassingMark();
		Result result = resultRepo.save(new Result(mark, trainee, finishedQuiz, passed));	
		return result;
	}

}
