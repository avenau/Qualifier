package com.fdm.qualifier.dataloader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.SkillService;
import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Stream;
import com.fdm.qualifier.service.ClientService;
import com.fdm.qualifier.service.PlacementService;
import com.fdm.qualifier.service.QuizService;
import com.fdm.qualifier.service.StreamService;
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

	private Log log = LogFactory.getLog(Dataloader.class);
	

	@Autowired
	public Dataloader(SuggestedSkillService suggestedSkillService, PlacementService placementService,
			SkillLevelService skillLevelService, SkillService skillService, ClientService clientService,
			TraineeService traineeService, StreamService streamService, QuizService quizService) {
		super();
		this.suggestedSkillService = suggestedSkillService;
		this.placementService = placementService;
		this.skillLevelService = skillLevelService;
		this.skillService = skillService;
		this.clientService = clientService;
		this.traineeService = traineeService;
		this.streamService = streamService;
		this.quizService = quizService;
	}
	@Transactional
	@Modifying
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Starting Data Setup");

		SuggestedSkill suggestedSkill = new SuggestedSkill("java");
		suggestedSkillService.save(suggestedSkill);

		createTrainee();
		
		LocalDate startDate = LocalDate.of(2020, 1, 8);
		
		Trainee trainee1 = new Trainee("username", "password");
		traineeService.save(trainee1);
		
		Stream stream1 = new Stream("Name", Arrays.asList(trainee1));
		streamService.save(stream1);
		
		log.debug("Creating skills");
		Skill java = new Skill("Java");
		Skill cs = new Skill("C#");
		Skill python = new Skill("Python");
		java = skillService.save(java);
		cs = skillService.save(cs);
		python = skillService.save(python);
		
		log.debug("Creating Quiz");
		Quiz quiz1 = new Quiz();
		quizService.save(quiz1);
		
		log.debug("Creating SkillLevels");
		SkillLevel skillLevel1 = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, java, quiz1);
		SkillLevel skillLevel2 = new SkillLevel(SkillLevel.KnowledgeLevel.INTERMEDIATE, cs, quiz1);
		SkillLevel skillLevel3 = new SkillLevel(SkillLevel.KnowledgeLevel.EXPERT, python, quiz1);
		skillLevelService.save(skillLevel1);
		skillLevelService.save(skillLevel2);
		skillLevelService.save(skillLevel3);

		log.debug("Creating clients and placements");
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
		
		log.debug("Find by Java");
		for(Placement p : placementService.findBySkillName("Java")) {
			log.debug(p);
		}
		log.debug("Find by name " + placementService.findByName("Placement1"));
		log.debug("Display all ");
		for(Placement p : placementService.findAll()) {
			log.debug(p);
		}
		log.debug("Find by client name");
		for(Placement p : placementService.findByClientName("ANZ")) {
			log.debug(p);
		}
		log.debug("Find by Location Sydney");
		for(Placement p : placementService.findByLocation("Sydney")) {
			log.debug(p);
		}
		log.info("Finished Data Setup");
	}

	public void createTrainee() {
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
//		trainee = traineeService.save(trainee);
		log.debug(trainee);
		trainee.setSkills(new ArrayList<>(Arrays.asList(javaBeginner, cppIntermediate, reactBeginner)));
		
		List<SkillLevel> skills = traineeService.getSkills(trainee);
		log.debug(skills);
		
		List<SkillLevel> pinnedSkills = traineeService.getPinnedSkills(trainee);
		log.debug(pinnedSkills);
		
		List<SkillLevel> allSkills = traineeService.getAllSkills(trainee);
		log.debug(allSkills);
		
		//Change pinned skills		
		traineeService.pinSkill(trainee, javaBeginner);
		log.debug(trainee);
		
		traineeService.unpinSkill(trainee, javaBeginner);
		log.debug(trainee);
	}
}
