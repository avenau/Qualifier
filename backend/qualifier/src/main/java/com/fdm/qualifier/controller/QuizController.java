package com.fdm.qualifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.service.QuizService;

@RestController
public class QuizController {
	private QuizService quizService;

	@Autowired
	public QuizController(QuizService quizService) {
		super();
		this.quizService = quizService;
	}

}
