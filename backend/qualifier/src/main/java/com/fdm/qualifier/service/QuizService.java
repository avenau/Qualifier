package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fdm.qualifier.dto.QuizDTO;
import com.fdm.qualifier.httpRequest.QuizRequest;
import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;

import com.fdm.qualifier.model.SkillLevel;

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
		boolean passed = mark >= finishedQuiz.getPassingMark();
		Result result = resultRepo.save(new Result(mark, trainee, finishedQuiz, passed));
		return result;
	}

	
	public void save(Quiz newQuiz) {
		quizRepo.save(newQuiz);
	}

	public QuizDTO createNewQuizDTO(String name, String description, double duration, int questionCount, double passingMark) {
		Quiz quiz = new Quiz(name, description, duration, questionCount, passingMark);
		quizRepo.save(quiz);
		QuizDTO newQuiz = new QuizDTO(quiz);
		return newQuiz;
	}

	// Used in dataloader to save result with submitted answers
	public Result saveResult(Result result) {
		return resultRepo.save(result);
	}

	public Result findResultById(int resultId) {
		Optional<Result> optionalResult = resultRepo.findById(resultId);
		if (optionalResult.isEmpty())
			return null;
		return optionalResult.get();
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

	
	public Quiz loadNewQuiz(String name, String description, double duration, int questionCount, double passingMark,
			SkillLevel skillLevel) {
		Quiz quiz = new Quiz(name, description, duration, questionCount, passingMark, skillLevel);
		quizRepo.save(quiz);
		return quiz;
	}
	
	public QuizDTO findQuizDTOById(int id) {
		Optional<Quiz> quizOptional = quizRepo.findById(id);
		Quiz quiz = quizOptional.get();
		QuizDTO quizDTO = new QuizDTO(quiz);
		return quizDTO;

	}

	public List<Quiz> findAllQuiz() {
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

	public QuizDTO updateQuiz(QuizRequest request) {
		Quiz quiz = quizRequestToQuiz(request);
		Quiz quizResult = quizRepo.save(quiz);
		return new QuizDTO(quizResult);
	}
	
	public Quiz quizRequestToQuiz(QuizRequest request) {
		Quiz quiz = new Quiz();
		quiz.setQuizId(request.getQuizId());
		quiz.setName(request.getName());
		quiz.setDescription(request.getDescription());
		quiz.setDuration(request.getDuration());
		quiz.setQuestionCount(request.getQuestionCount());
		quiz.setPassingMark(request.getPassingMark());
		quiz.setQuestionsByDTO(request.getQuestions());
		return quiz;
	}


}
