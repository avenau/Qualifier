package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Result saveQuizResult(Quiz finishedQuiz, double mark, Trainee trainee) {
		boolean passed = mark >= finishedQuiz.getPassingMark();
		Result result = resultRepo.save(new Result(mark, trainee, finishedQuiz, passed));
		return result;
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

	public List<Quiz> findAllQuiz() {
		return quizRepo.findAll();
	}
}
