package com.fdm.qualifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.service.QuestionService;

@RestController
public class QuestionController {
	private QuestionService questionService;

	@Autowired
	public QuestionController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}
	
	
}
