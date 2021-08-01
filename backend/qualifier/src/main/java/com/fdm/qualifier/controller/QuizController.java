package com.fdm.qualifier.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;

import com.fdm.qualifier.model.SuggestedSkill;

import com.fdm.qualifier.service.QuizService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
	private QuizService quizService;

	@Autowired
	public QuizController(QuizService quizService) {
		super();
		this.quizService = quizService;
	}
	
	@PostMapping("/submitQuiz")
	public void submitQuiz(int quizId, @RequestParam List<String> answers) {
		//quizService.saveQuizResult(null, 0, null);
		System.out.println("SUBMIT QUIZ ID: " + quizId);
		//System.out.println(answers);
//		for (int hi : answers) {
//			System.out.println("ANSWER ARRAY: " + hi);
//		}
	}
	
	@GetMapping("/getQuizDetails")
	public Quiz quizDetails(int quizId) {
		System.out.println("ID adfd: " + quizId);
		Optional<Quiz> selectedQuiz = quizService.findQuizById(quizId);
		if (!selectedQuiz.isPresent()) {
			System.out.println("ERROR");
			return null;
		}
		return selectedQuiz.get();
	}
	
	@GetMapping("/getQuizQuestions")
	public List<Question> getQuizQuestions(int id) {
		System.out.println("ID adfd: " + id);
		Optional<Quiz> selectedQuiz = quizService.findQuizById(id);
		if (!selectedQuiz.isPresent()) {
			System.out.println("ERROR");
			return null;
		}
		return selectedQuiz.get().getQuestions();
	}
	
/*	@GetMapping("/loadQuizPage")
	public Quiz loadQuizPage(int id) {
		System.out.println("ID adfd: " + id);
		Optional<Quiz> selectedQuiz = quizService.findQuizById(id);
		if (!selectedQuiz.isPresent()) {
			System.out.println("ERROR");
			return null;
		}
		return selectedQuiz.get();
	}*/


}
