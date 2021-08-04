package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.dto.QuizDTO;
import com.fdm.qualifier.httpRequest.UpdateQuizRequest;
import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.SkillLevel;
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

	public QuizDTO updateQuiz(UpdateQuizRequest request) {
		Quiz quiz = quizRequestToQuiz(request);
		Quiz quizResult = quizRepo.save(quiz);
		return new QuizDTO(quizResult);
	}
	
	public Quiz quizRequestToQuiz(UpdateQuizRequest request) {
		Quiz quiz = new Quiz();
		quiz.setQuizId(request.getQuizId());
		quiz.setName(request.getName());
		quiz.setDescription(request.getDescription());
		quiz.setDuration(request.getDuration());
		quiz.setQuestionCount(request.getQuestionCount());
		quiz.setPassingMark(request.getPassingMark());
		quiz.setQuestions(request.getQuestions());
		return quiz;
	}

}
