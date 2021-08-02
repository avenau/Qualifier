package com.fdm.qualifier.dataloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.repository.AnswerRepository;
import com.fdm.qualifier.service.AnswerService;
import com.fdm.qualifier.service.QuestionService;
import com.fdm.qualifier.service.QuizService;
import com.fdm.qualifier.service.SuggestedSkillService;

@Component
public class Dataloader implements ApplicationRunner {
	Logger logger = LogManager.getLogger();
	
	private SuggestedSkillService suggestedSkillService;
	private AnswerService answerService;
	private QuizService quizService;
	private QuestionService questionService;

	@Autowired
	public Dataloader(SuggestedSkillService suggestedSkillService, AnswerService answerService, QuizService quizService,
			QuestionService questionService) {
		super();
		this.suggestedSkillService = suggestedSkillService;
		this.answerService = answerService;
		this.quizService = quizService;
		this.questionService = questionService;
	}

	@Override
	@Transactional
	@Modifying
	public void run(ApplicationArguments args) throws Exception {
		SuggestedSkill suggestedSkill = new SuggestedSkill("java");
		
		suggestedSkillService.save(suggestedSkill);
		
		logger.info("quiz init started");
//		byte[] imageBytes = Files.readAllBytes(Paths.get("C:\\Users\\shirl\\Desktop\\against.jpg"));
		
//		Question question1 = new Question(null, "text", Question.QuestionType.MUTIPLE_CHOICE, 5, null, null);
//		Answer answer1 = new Answer("answerText", question1, true);
//		Quiz quiz1 = new Quiz("Java", "Java entry", 20,  10, 75, List.of(question1));
//		
//		question1.setQuiz(quiz1);
//		question1.setAnswers(List.of(answer1));
//		
//		
//		questionRepo.save(question1);
//		answerRepo.save(answer1);
//		quizRepo.save(quiz1);
		
		
		Quiz javaBeginner = quizService.createNewQuiz("Java Beginner Level Quiz", "", 20, 75);
		Question javaBq1 = questionService.createNewQuestion(javaBeginner, "Question Content", Question.QuestionType.MUTIPLE_CHOICE, 4, null);
		Answer javaBq1aA = answerService.createNewAnswer("Answer Content", javaBq1, true);
		Answer javaBq1aB = answerService.createNewAnswer("Answer Content", javaBq1, false);
		Answer javaBq1aC = answerService.createNewAnswer("Answer Content", javaBq1, false);
		Answer javaBq1aD = answerService.createNewAnswer("Answer Content", javaBq1, false);
		Question javaBq2 = questionService.createNewQuestion(javaBeginner, "Question Content", Question.QuestionType.MULTI_SELECT, 4, null);
		Answer javaBq2aA = answerService.createNewAnswer("Answer Content", javaBq2, false);
		Answer javaBq2aB = answerService.createNewAnswer("Answer Content", javaBq2, true);
		Answer javaBq2aC = answerService.createNewAnswer("Answer Content", javaBq2, true);
		Answer javaBq2aD = answerService.createNewAnswer("Answer Content", javaBq2, false);
		logger.info("quiz init finished");
	}

}
