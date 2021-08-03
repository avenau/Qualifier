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
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.SkillLevel.KnowledgeLevel;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.repository.AnswerRepository;
import com.fdm.qualifier.repository.QuestionRepository;
import com.fdm.qualifier.repository.QuizRepository;
import com.fdm.qualifier.repository.ResultRepository;
import com.fdm.qualifier.request.UpdateQuizRequest;

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

	public QuizDTO createNewQuiz(String name, String description, double duration, int questionCount, double passingMark,
			SkillLevel skillLevel) {
		Quiz quiz = new Quiz(name, description, duration, questionCount, passingMark, skillLevel);
		quizRepo.save(quiz);
		QuizDTO newQuiz = new QuizDTO(quiz);
		return newQuiz;
	}
	
	public Quiz loadNewQuiz(String name, String description, double duration, int questionCount, double passingMark,
			SkillLevel skillLevel) {
		Quiz quiz = new Quiz(name, description, duration, questionCount, passingMark, skillLevel);
		quizRepo.save(quiz);
		return quiz;
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

	public QuizDTO update(UpdateQuizRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
