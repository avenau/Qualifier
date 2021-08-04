package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.SubmittedAnswer;
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
	private QuestionService questionService;
	private SubmittedAnswerService submittedAnswerService;
	private AnswerService answerService;
	private ResultService resultService;
	
	public QuizService() {
		super();
	}

	@Autowired
	public QuizService(ResultRepository resultRepo, QuizRepository quizRepo, QuestionRepository questionRepo,
			AnswerRepository answerRepo, QuestionService questionService, SubmittedAnswerService submittedAnswerService,
			AnswerService answerService, ResultService resultService) {
		super();
		this.resultRepo = resultRepo;
		this.quizRepo = quizRepo;
		this.questionRepo = questionRepo;
		this.answerRepo = answerRepo;
		this.questionService = questionService;
		this.submittedAnswerService = submittedAnswerService;
		this.answerService = answerService;
		this.resultService = resultService;
	}
	
	public Result saveQuizResult(Quiz finishedQuiz, double mark, Trainee trainee) {
		boolean passed = mark >=finishedQuiz.getPassingMark();
		Result result = resultRepo.save(new Result(mark, trainee, finishedQuiz, passed));	
		return result;
	}

	public Quiz saveQuiz(Quiz quiz) {
		return quizRepo.save(quiz);
	}
	public Answer saveAnswer(Answer answer) {
		return answerRepo.save(answer);
	}
	public Question saveQuestion(Question question) {
		return questionRepo.save(question);
	}
	
	public Optional<Quiz> findQuizById(int id) {
		return quizRepo.findById(id);
	}
	
	public List<Quiz> findAllQuiz(){
		return quizRepo.findAll();
	}
	
	@Transactional
	public void deleteQuiz(Quiz quiz) {
		int quizID = quiz.getQuizId();
		List<Question> questions = questionService.findAllQuestionsOfQuizID(quiz);
		
		for (Question question : questions) {
			//remove every submitted answer
			List<SubmittedAnswer> subAnswerList = submittedAnswerService.findByQuestion(question);
			for (SubmittedAnswer subAnswer : subAnswerList) {
				submittedAnswerService.delete(subAnswer);
			}
			
			//remove every answer
			List<Answer> answerList = answerService.findByQuestion(question);
			for (Answer answer : answerList) {
				answerService.delete(answer);
			}

			//remove every question
			questionService.delete(question);
		}
		
		//remove every result of the quiz by trainees
		List<Result> results = resultService.findByQuiz(quiz);
		for (Result result : results) {
			resultService.delete(result);
		}
		
		quizRepo.delete(quiz);
		
	}
}
