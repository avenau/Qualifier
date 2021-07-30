package com.fdm.qualifier.dataloader;

import java.util.ArrayList;
import java.util.Arrays;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.QuizService;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.SkillService;
import com.fdm.qualifier.service.SuggestedSkillService;
import com.fdm.qualifier.service.TraineeService;

@Component
public class Dataloader implements ApplicationRunner {
	private SuggestedSkillService suggestedSkillService;
	private SkillService skillService;
	private SkillLevelService skillLevelService;
	private TraineeService traineeService;
	private QuizService quizService;

	private Log log = LogFactory.getLog(Dataloader.class);
	
	public Dataloader(SuggestedSkillService suggestedSkillService, SkillService skillService,
			SkillLevelService skillLevelService, TraineeService traineeService, QuizService quizService) {
		super();
		this.suggestedSkillService = suggestedSkillService;
		this.skillService = skillService;
		this.skillLevelService = skillLevelService;
		this.traineeService = traineeService;
		this.quizService = 	quizService;
	}

	@Override
	@Transactional
	@Modifying
	public void run(ApplicationArguments args) throws Exception {
		log.info("Starting Data Setup");

		createQuiz();
		createTrainee();
		
		log.info("Finished Data Setup");

	}
	
	public void createQuiz() {
		Quiz quiz = new Quiz("Java Quiz", "For Java Students", 200, 5, 50.0,new ArrayList<Question>());
		Quiz savedQuiz = quizService.saveQuiz(quiz);
		log.info("SAVED QUIZ ID: " + savedQuiz.getQuizId());
		
		
	}

	public void createTrainee() {
		//Create Suggested Skills
		SuggestedSkill suggestedSkill = new SuggestedSkill("java");
		suggestedSkillService.save(suggestedSkill);
		
		//Create Skills
		Skill java = new Skill("java");
		Skill cpp = new Skill("cpp");
		Skill react = new Skill("react");
		
		log.info("Saving Skills");
		skillService.save(Arrays.asList(java, cpp, react));
		
		//Create Skill levels
		SkillLevel javaBeginner = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, java, null);
		SkillLevel javaIntermediate = new SkillLevel(SkillLevel.KnowledgeLevel.INTERMEDIATE, java, null);
		SkillLevel javaExpert = new SkillLevel(SkillLevel.KnowledgeLevel.EXPERT, java, null);
		
		SkillLevel cppBeginner = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, cpp, null);
		SkillLevel cppIntermediate = new SkillLevel(SkillLevel.KnowledgeLevel.INTERMEDIATE, cpp, null);
		SkillLevel cppExpert = new SkillLevel(SkillLevel.KnowledgeLevel.EXPERT, cpp, null);
		
		SkillLevel reactBeginner = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, react, null);
		SkillLevel reactIntermediate = new SkillLevel(SkillLevel.KnowledgeLevel.INTERMEDIATE, react, null);
		SkillLevel reactExpert = new SkillLevel(SkillLevel.KnowledgeLevel.EXPERT, react, null);
		
		log.info("Saving Skill levels");
		skillLevelService.save(Arrays.asList(javaBeginner, javaIntermediate, javaExpert));
		skillLevelService.save(Arrays.asList(cppBeginner, cppIntermediate, cppExpert));
		skillLevelService.save(Arrays.asList(reactBeginner, reactIntermediate, reactExpert));	
		
		//Create Trainee with skills
		Trainee trainee = new Trainee("trainee1", "123");
		log.info("Saving Trainee");
		trainee = traineeService.save(trainee);
		log.debug(trainee);
		SkillLevel javaBeginnerFound = skillLevelService.save(javaBeginner);
		trainee.setSkills(Arrays.asList(javaBeginner, cppIntermediate, reactBeginner));
		
	}
}
