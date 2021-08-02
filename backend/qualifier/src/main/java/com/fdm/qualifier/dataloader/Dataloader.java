package com.fdm.qualifier.dataloader;


import java.time.LocalDate;
import java.util.Arrays;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.Stream;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.ClientService;
import com.fdm.qualifier.service.PlacementService;
import com.fdm.qualifier.service.QuestionService;
import com.fdm.qualifier.service.QuizService;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.SkillService;
import com.fdm.qualifier.service.StreamService;
import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.repository.AnswerRepository;
import com.fdm.qualifier.repository.QuestionRepository;
import com.fdm.qualifier.repository.QuizRepository;
import com.fdm.qualifier.service.SuggestedSkillService;
import com.fdm.qualifier.service.TraineeService;

@Component
public class Dataloader implements ApplicationRunner {
	
	private SuggestedSkillService suggestedSkillService;
	private PlacementService placementService;
	private SkillLevelService skillLevelService;
	private SkillService skillService;
	private ClientService clientService;
	private TraineeService traineeService;
	private StreamService streamService;
	private QuizService quizService;
	private QuestionService questionService;

	@Autowired
	public Dataloader(SuggestedSkillService suggestedSkillService, PlacementService placementService,
			SkillLevelService skillLevelService, SkillService skillService, ClientService clientService,
			TraineeService traineeService, StreamService streamService, QuizService quizService, 
			QuestionService questionService) {
		super();
		this.suggestedSkillService = suggestedSkillService;
		this.placementService = placementService;
		this.skillLevelService = skillLevelService;
		this.skillService = skillService;
		this.clientService = clientService;
		this.traineeService = traineeService;
		this.streamService = streamService;
		this.quizService = quizService;
		this.questionService = questionService;
	}

	
	@Override
	@Transactional
	@Modifying
	public void run(ApplicationArguments args) throws Exception {

		SuggestedSkill suggestedSkill = new SuggestedSkill("java");
		suggestedSkillService.save(suggestedSkill);
		
		LocalDate startDate = LocalDate.of(2020, 1, 8);
		
		Trainee trainee1 = new Trainee("username", "password");
		traineeService.save(trainee1);
		
		Stream stream1 = new Stream("Name", Arrays.asList(trainee1));
		streamService.save(stream1);
		
		Skill java = new Skill("Java");
		Skill cs = new Skill("C#");
		Skill python = new Skill("Python");
		skillService.save(java);
		skillService.save(cs);
		skillService.save(python);
		
		Quiz quiz1 = new Quiz();
		quizService.save(quiz1);
		
		SkillLevel skillLevel1 = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, java, quiz1);
		SkillLevel skillLevel2 = new SkillLevel(SkillLevel.KnowledgeLevel.INTERMEDIATE, cs, quiz1);
		SkillLevel skillLevel3 = new SkillLevel(SkillLevel.KnowledgeLevel.EXPERT, python, quiz1);
		skillLevelService.save(skillLevel1);
		skillLevelService.save(skillLevel2);
		skillLevelService.save(skillLevel3);
		
		Client client1 = new Client("ANZ");		
		Client client2 = new Client("Kmart");
		Placement placement1 = new Placement("Placement1", startDate, startDate, "test", "Melbourne", client1, trainee1, Arrays.asList(trainee1), Arrays.asList(skillLevel1, skillLevel2, skillLevel3));
		Placement placement2 = new Placement("Placement2", startDate, startDate, "test", "Sydney", client2, trainee1, Arrays.asList(trainee1), Arrays.asList(skillLevel2, skillLevel3));
		Placement placement3 = new Placement("Placement3", startDate, startDate, "test", "Sydney", client1, trainee1, Arrays.asList(trainee1), Arrays.asList(skillLevel1, skillLevel2, skillLevel3));
		client1.setPlacements(Arrays.asList(placement3));
		client2.setPlacements(Arrays.asList(placement2));
		clientService.save(client1);
		clientService.save(client2);
		placementService.save(placement1);
		placementService.save(placement2);
		placementService.save(placement3);

		System.out.println("init");
		
		byte[] imageBytes = Files.readAllBytes(Paths.get("C:\\Users\\shirl\\Desktop\\against.jpg"));
		
		Question question1 = new Question(null, "text", Question.QuestionType.MUTIPLE_CHOICE, 5, imageBytes, null);
		Answer answer1 = new Answer("answerText", question1, true);
		Quiz quiz2 = new Quiz("Java", "Java entry", 20,  10, 75, Arrays.asList(question1));
		
		question1.setQuiz(quiz2);
		question1.setAnswers(Arrays.asList(answer1));
		
		questionService.save(question1);
		//answerRepo.save(answer1);
		quizService.save(quiz2);
		
		System.out.println(question1);
		System.out.println(answer1);
		System.out.println(quiz1);
		System.out.println("init finished");

	}

}
