package com.fdm.qualifier.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.service.QuizService;

@RestController
public class QuizController {
	private QuizService quizService;

	@Autowired
	public QuizController(QuizService quizService) {
		super();
		this.quizService = quizService;
	}
	
	/*@PostMapping("/saveSuggestedSkill")
	public void save(SuggestedSkill suggestedSkill) {
		log.trace("save() called");
		log.info("Saving suggested skill: " + suggestedSkill);
		suggestedSkillService.save(suggestedSkill);
	}*/
	
	@GetMapping("/getStartQuizDetails")
	public Quiz startQuizDetails(int id) {
		System.out.println("ID adfd: " + id);
		/*Optional<Quiz> selectedQuiz = quizService.findQuizById(id);
		if (!selectedQuiz.isPresent()) {
			System.out.println("ERROR");
			return null;
		}*/
		Quiz mockQuiz = new Quiz("Java Quiz", "For Java Students", 200, 5, 50.0,new ArrayList<Question>());
		return mockQuiz;
	}


}
