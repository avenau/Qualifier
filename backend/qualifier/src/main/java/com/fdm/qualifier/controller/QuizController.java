package com.fdm.qualifier.controller;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.dto.QuizDTO;
import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.request.UpdateQuizRequest;
import com.fdm.qualifier.model.SkillLevel.KnowledgeLevel;
import com.fdm.qualifier.service.QuizService;
import com.fdm.qualifier.service.SkillLevelService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
	Logger logger = LogManager.getLogger();
	
	private QuizService quizService;
	private SkillLevelService skillLevelService;

	@Autowired
	public QuizController(QuizService quizService, SkillLevelService skillLevelService) {
		super();
		this.quizService = quizService;
		this.skillLevelService = skillLevelService;
	}
	
	@GetMapping("/quiz/get/{id}")
	public QuizDTO getQuizDetails(@PathVariable("id") String id) {
		int quizId = Integer.parseInt(id);
		QuizDTO quizDTO = quizService.findQuizById(quizId);
		return quizDTO;
	}
	
	@GetMapping("/quiz/create/{id}")
	public QuizDTO createQuizDetails(@PathVariable("id") String id) {
		int skillLevelId = Integer.parseInt(id);
		Optional<SkillLevel> skillLevel = skillLevelService.findById(skillLevelId);
		if (!skillLevel.isPresent()) {
			logger.error("Skill level id could not be found");
			return null;
		}
		QuizDTO quizDTO = quizService.createNewQuiz(null, null, 0, 0, 0, skillLevel.get());
		return quizDTO;
	}
	
	@PostMapping("/quiz/update")
	public QuizDTO updateQuizDetails(@RequestBody UpdateQuizRequest request) {
		return quizService.update(request);
	}
	
	@GetMapping("/getAllQuizzes")
	public List<Quiz> getAllQuizzes() {
		return quizService.findAllQuiz();
	}

}
