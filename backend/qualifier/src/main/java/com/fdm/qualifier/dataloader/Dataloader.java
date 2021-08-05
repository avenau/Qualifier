package com.fdm.qualifier.dataloader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Question.QuestionType;
import com.fdm.qualifier.model.SkillLevel.KnowledgeLevel;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.Stream;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.model.Trainer;
import com.fdm.qualifier.model.User;
import com.fdm.qualifier.service.AnswerService;
import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Question.QuestionType;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.ClientService;
import com.fdm.qualifier.service.PlacementService;
import com.fdm.qualifier.service.QuestionService;
import com.fdm.qualifier.service.QuizService;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.SkillService;
import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.Stream;
import com.fdm.qualifier.model.SubmittedAnswer;
import com.fdm.qualifier.service.ClientService;
import com.fdm.qualifier.service.PlacementService;
import com.fdm.qualifier.service.StreamService;
import com.fdm.qualifier.service.SubmittedAnswerService;
import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.repository.AnswerRepository;
import com.fdm.qualifier.repository.QuestionRepository;
import com.fdm.qualifier.repository.QuizRepository;
import com.fdm.qualifier.service.SuggestedSkillService;
import com.fdm.qualifier.service.TraineeService;
import com.fdm.qualifier.service.TrainerService;
import com.fdm.qualifier.service.UserService;

@Component
public class Dataloader implements ApplicationRunner {
	private Log log = LogFactory.getLog(Dataloader.class);

	private SuggestedSkillService suggestedSkillService;
	private AnswerService answerService;
	private PlacementService placementService;
	private SkillLevelService skillLevelService;
	private SkillService skillService;
	private ClientService clientService;
	private TraineeService traineeService;
	private StreamService streamService;
	private QuizService quizService;
	private QuestionService questionService;
	private SubmittedAnswerService submittedAnswerService;
	private UserService userService;
	private TrainerService trainerService;

	@Autowired
	public Dataloader(SuggestedSkillService suggestedSkillService, PlacementService placementService,
			SkillLevelService skillLevelService, SkillService skillService, ClientService clientService,
			TraineeService traineeService, StreamService streamService, QuizService quizService, 
			QuestionService questionService, AnswerService answerService , SubmittedAnswerService submittedAnswerService, UserService userService, TrainerService trainerService) {
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
		this.userService = userService;
		this.submittedAnswerService = submittedAnswerService;
		this.trainerService = trainerService; 

		this.answerService = answerService;

	}

	@Override
	@Transactional
	@Modifying
	public void run(ApplicationArguments args) throws Exception {
		log.info("Starting Data Setup");

//		createQuiz();
		createTrainee();
		createResult();

		SuggestedSkill suggestedSkill = new SuggestedSkill("java");
		suggestedSkillService.save(suggestedSkill);

		LocalDate startDate = LocalDate.of(2020, 1, 8);

		log.debug("Creating skills");
		Skill java = new Skill("Java");
		Skill cs = new Skill("C#");
		Skill python = new Skill("Python");
		java = skillService.save(java);
		cs = skillService.save(cs);
		python = skillService.save(python);

		// SkillLevel skillLevel1 = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER,
		// java, null);
		// SkillLevel skillLevel2 = new
		// SkillLevel(SkillLevel.KnowledgeLevel.INTERMEDIATE, cs, null);
		// SkillLevel skillLevel3 = new SkillLevel(SkillLevel.KnowledgeLevel.EXPERT,
		// python, null);
		log.debug("Creating Quiz");
		Quiz quiz1 = new Quiz();
		quizService.saveQuiz(quiz1);

		log.debug("Creating SkillLevels");
		SkillLevel skillLevel1 = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, java, null);
		SkillLevel skillLevel2 = new SkillLevel(SkillLevel.KnowledgeLevel.INTERMEDIATE, cs, null);
		SkillLevel skillLevel3 = new SkillLevel(SkillLevel.KnowledgeLevel.EXPERT, python, null);
		SkillLevel skillLevel4 = new SkillLevel(SkillLevel.KnowledgeLevel.UNVERIFIED, python, null);
		skillLevelService.save(skillLevel1);
		skillLevelService.save(skillLevel2);
		skillLevelService.save(skillLevel3);
		skillLevelService.save(skillLevel4);

		List<SkillLevel> skillSet = new ArrayList<>();
		List<SkillLevel> pinnedSkillSet = new ArrayList<>();
		skillSet.add(skillLevel1);
		skillSet.add(skillLevel2);
		pinnedSkillSet.add(skillLevel3);

		// save trainee
		Trainee trainee1 = new Trainee("username", "password");
		Trainee trainee2 = new Trainee("fink", "asdf");
		Trainee trainee3 = new Trainee("Barney", "dinosaur");
		User admin = new User("admin", "password", "admin");
		User sales = new User("sales", "password", "sales");
		Trainer trainer = new Trainer("trainer", "password");
		userService.saveUser(sales);
		userService.saveUser(admin);
		trainerService.saveTrainer(trainer);
		
		
		
		trainee1.setEmail("trainee@mail.com");
		trainee1.setCity("Sydney");
		trainee1.setAddress("123 Fake Street");
		trainee1.setPhoneNumber(1234567890);
		trainee1.setSkills(skillSet);

		trainee1.setPinnedSkills(pinnedSkillSet);

		trainee1.setFirstName("Stacy");
		trainee1.setLastName("Mancy");

		trainee2.setEmail("ink@mail.com");
		trainee2.setCity("Sydney");
		trainee2.setAddress("Soccerfield");
		trainee2.setPhoneNumber(5464646);
		trainee2.setSkills(skillSet);
		trainee2.setFirstName("Rick");
		trainee2.setLastName("Pickle");

		trainee3.setEmail("roseEater@mail.com");
		trainee3.setCity("Melbourne");
		trainee3.setAddress("tissue box");
		trainee3.setPhoneNumber(656465486);
		trainee3.setSkills(skillSet);
		trainee3.setFirstName("Necro");
		trainee3.setLastName("Mancy");

		traineeService.save(trainee1);
		traineeService.save(trainee2);
		traineeService.save(trainee3);

		// stream
		Stream stream1 = new Stream("Name", Arrays.asList(trainee1));
		streamService.save(stream1);

		log.debug("Creating clients and placements");
		Client client1 = new Client("ANZ");
		Client client2 = new Client("Kmart");
		Placement placement1 = new Placement("Placement1", startDate, startDate, "test", "Melbourne", client1, trainee1,
				Arrays.asList(trainee1), Arrays.asList(skillLevel1, skillLevel2, skillLevel3));
		Placement placement2 = new Placement("Placement2", startDate, startDate, "test", "Sydney", client2, trainee1,
				Arrays.asList(trainee1), Arrays.asList(skillLevel2, skillLevel3));
		Placement placement3 = new Placement("Placement3", startDate, startDate, "test", "Sydney", client1, trainee1,
				Arrays.asList(trainee1), Arrays.asList(skillLevel1, skillLevel2, skillLevel3));
		client1.setPlacements(Arrays.asList(placement3));
		client2.setPlacements(Arrays.asList(placement2));
		clientService.save(client1);
		clientService.save(client2);
		placementService.save(placement1);
		placementService.save(placement2);
		placementService.save(placement3);

		log.info("quiz init started");
//		byte[] imageBytes = Files.readAllBytes(Paths.get("C:\\Users\\shirl\\Desktop\\against.jpg"));		
		Quiz javaBeginner = quizService.loadNewQuiz("Java Beginner Level Quiz", "Following quiz provides Multiple Choice and Multi Select Questions related to Core Java (Beginner Level). You will have to read all the given answers and click over the correct answer.", 600, 0, 75, skillLevel1);
		skillLevel1.setQuiz(javaBeginner);
		Question javaBq1 = questionService.createNewQuestion(javaBeginner, "Q 1 - Which of the following is false about String?", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq1aA = answerService.createNewAnswer("A - String is immutable.", javaBq1, false);
		Answer javaBq1aB = answerService.createNewAnswer("B - String can be created using new operator.", javaBq1, false);
		Answer javaBq1aC = answerService.createNewAnswer("C - String is a primary data type.", javaBq1, true);
		Answer javaBq1aD = answerService.createNewAnswer("D - None of the above.", javaBq1, false);
		Question javaBq2 = questionService.createNewQuestion(javaBeginner, "Q 2 - What is TreeSet Interface?", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq2aA = answerService.createNewAnswer("A - It is a Set implemented when we want elements in a tree based order.", javaBq2, false);
		Answer javaBq2aB = answerService.createNewAnswer("B - It is a Set implemented when we want elements in a sorted order.", javaBq2, true);
		Answer javaBq2aC = answerService.createNewAnswer("C - It is a Set implemented when we want elements in a binary tree format.", javaBq2, false);
		Answer javaBq2aD = answerService.createNewAnswer("D - It is a Set implemented when we want elements in a hiearchical order.", javaBq2, false);
		Question javaBq3 = questionService.createNewQuestion(javaBeginner, "Q 3 - Method Overriding is an example of", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq3aA = answerService.createNewAnswer("A - Static Binding.", javaBq3, false);
		Answer javaBq3aB = answerService.createNewAnswer("B - Dynamic Binding.", javaBq3, true);
		Answer javaBq3aC = answerService.createNewAnswer("C - Both of the above.", javaBq3, false);
		Answer javaBq3aD = answerService.createNewAnswer("D - None of the above.", javaBq3, false);
		Question javaBq4 = questionService.createNewQuestion(javaBeginner, "Q 4 - What is synchronization?", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq4aA = answerService.createNewAnswer("A - Synchronization is the capability to control the access of multiple threads to shared resources.", javaBq4, true);
		Answer javaBq4aB = answerService.createNewAnswer("B - Synchronization is the process of writing the state of an object to another object.", javaBq4, false);
		Answer javaBq4aC = answerService.createNewAnswer("C - Synchronization is the process of writing the state of an object to byte stream.", javaBq4, false);
		Answer javaBq4aD = answerService.createNewAnswer("D - None of the above.", javaBq4, false);
		Question javaBq5 = questionService.createNewQuestion(javaBeginner, "Q 5 - This is the parent of Error and Exception classes.", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq5aA = answerService.createNewAnswer("A - Throwable", javaBq5, true);
		Answer javaBq5aB = answerService.createNewAnswer("B - Catchable", javaBq5, false);
		Answer javaBq5aC = answerService.createNewAnswer("C - MainError", javaBq5, false);
		Answer javaBq5aD = answerService.createNewAnswer("D - MainException", javaBq5, false);
		Question javaBq6 = questionService.createNewQuestion(javaBeginner, "Q 6 - Inheritance", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq6aA = answerService.createNewAnswer("A - Enables to add additional features to an existing class without modifying it", javaBq6, false);
		Answer javaBq6aB = answerService.createNewAnswer("B - Supports the concept of hierarchical classifications", javaBq6, false);
		Answer javaBq6aC = answerService.createNewAnswer("C - Is a process by which objects of one class acquire the properties of objects of another class", javaBq6, false);
		Answer javaBq6aD = answerService.createNewAnswer("D - All of these", javaBq6, true);
		Question javaBq7 = questionService.createNewQuestion(javaBeginner, "Q 7 - The thread may be in", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq7aA = answerService.createNewAnswer("A - Various waiting states", javaBq7, false);
		Answer javaBq7aB = answerService.createNewAnswer("B - Ready state", javaBq7, false);
		Answer javaBq7aC = answerService.createNewAnswer("C - Running state", javaBq7, false);
		Answer javaBq7aD = answerService.createNewAnswer("D - All of these", javaBq7, true);
		Question javaBq8 = questionService.createNewQuestion(javaBeginner, "Q 8 - The correct statement from the following is/are", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq8aA = answerService.createNewAnswer("A - Integer types are always signed in java", javaBq8, false);
		Answer javaBq8aB = answerService.createNewAnswer("B - The user of final to a variable in java is similar to the use of const in C and C++", javaBq8, false);
		Answer javaBq8aC = answerService.createNewAnswer("C - Java supports labeled break and labeled continue statement", javaBq8, false);
		Answer javaBq8aD = answerService.createNewAnswer("D - All of these", javaBq8, true);
		Question javaBq9 = questionService.createNewQuestion(javaBeginner, "Q 9 - The methods wait ( ) and notify ( ) are defined in", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq9aA = answerService.createNewAnswer("A - Java. Lang. thread", javaBq9, false);
		Answer javaBq9aB = answerService.createNewAnswer("B - Java. Lang. string", javaBq9, false);
		Answer javaBq9aC = answerService.createNewAnswer("C - Java. Lang. thread group", javaBq9, false);
		Answer javaBq9aD = answerService.createNewAnswer("D - Java. Lang. object", javaBq9, true);
		Question javaBq10 = questionService.createNewQuestion(javaBeginner, "Q 10 - For the method that does not return a value, the keyword used is", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq10aA = answerService.createNewAnswer("A - Boolean", javaBq10, false);
		Answer javaBq10aB = answerService.createNewAnswer("B - Void", javaBq10, true);
		Answer javaBq10aC = answerService.createNewAnswer("C - Null", javaBq10, false);
		Answer javaBq10aD = answerService.createNewAnswer("D - Short", javaBq10, false);
		Question javaBq11 = questionService.createNewQuestion(javaBeginner, "Q 11 - Which one of the following is not a wrap class?", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq11aA = answerService.createNewAnswer("A - Random", javaBq11, true);
		Answer javaBq11aB = answerService.createNewAnswer("B - Byte", javaBq11, false);
		Answer javaBq11aC = answerService.createNewAnswer("C - Double", javaBq11, false);
		Answer javaBq11aD = answerService.createNewAnswer("D - Short", javaBq11, false);
		Question javaBq12 = questionService.createNewQuestion(javaBeginner, "Q 12 - Object oriented programming language, is", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq12aA = answerService.createNewAnswer("A - C++", javaBq12, false);
		Answer javaBq12aB = answerService.createNewAnswer("B - Java", javaBq12, false);
		Answer javaBq12aC = answerService.createNewAnswer("C - Both (a) and (b)", javaBq12, true);
		Answer javaBq12aD = answerService.createNewAnswer("D - None of these", javaBq12, false);
		Question javaBq13 = questionService.createNewQuestion(javaBeginner, "Q 13 - ?: is: a/an", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq13aA = answerService.createNewAnswer("A - Conditional operator", javaBq13, true);
		Answer javaBq13aB = answerService.createNewAnswer("B - Assignment operator", javaBq13, false);
		Answer javaBq13aC = answerService.createNewAnswer("C - Inequality", javaBq13, false);
		Answer javaBq13aD = answerService.createNewAnswer("D - Logical AND", javaBq13, false);
		Question javaBq14 = questionService.createNewQuestion(javaBeginner, "Q 14 - Which of the below is valid way to instantiate an array in java?", Question.QuestionType.MUTIPLE_CHOICE, 5);
		Answer javaBq14aA = answerService.createNewAnswer("A - int myArray [] = {1, 3, 5};", javaBq14, true);
		Answer javaBq14aB = answerService.createNewAnswer("B - int myArray [] [] = {1,2,3,4};", javaBq14, false);
		Answer javaBq14aC = answerService.createNewAnswer("C - int [] myArray = (5, 4, 3);", javaBq14, false);
		Answer javaBq14aD = answerService.createNewAnswer("D - int [] myArray = {“1”, “2”, “3”};", javaBq14, false);
		Question javaBq15 = questionService.createNewQuestion(javaBeginner, "Q 15 - What are the valid statements for static keyword in Java?", Question.QuestionType.MULTI_SELECT, 5);
		Answer javaBq15aA = answerService.createNewAnswer("A - We can have static block in a class.", javaBq15, true);
		Answer javaBq15aB = answerService.createNewAnswer("B - The static block in a class is executed every time an object of class is created.", javaBq15, false);
		Answer javaBq15aC = answerService.createNewAnswer("C - We can have static method implementations in interface.", javaBq15, true);
		Answer javaBq15aD = answerService.createNewAnswer("D - We can define static block inside a method.", javaBq15, false);
		Question javaBq16 = questionService.createNewQuestion(javaBeginner, "Q 16 - Which of the below are unchecked exceptions in java?", Question.QuestionType.MULTI_SELECT, 5);
		Answer javaBq16aA = answerService.createNewAnswer("A - RuntimeException", javaBq16, true);
		Answer javaBq16aB = answerService.createNewAnswer("B - ClassCastException", javaBq16, true);
		Answer javaBq16aC = answerService.createNewAnswer("C - NullPointerException", javaBq16, true);
		Answer javaBq16aD = answerService.createNewAnswer("D - IOException", javaBq16, false);
		Question javaBq17 = questionService.createNewQuestion(javaBeginner, "Q 17 - Which of the following statement(s) are true for java?", Question.QuestionType.MULTI_SELECT, 5);
		Answer javaBq17aA = answerService.createNewAnswer("A - JVM is responsible for converting Byte code to the machine-specific code.", javaBq17, true);
		Answer javaBq17aB = answerService.createNewAnswer("B - We only need JRE to run java programs.", javaBq17, true);
		Answer javaBq17aC = answerService.createNewAnswer("C - JDK is required to compile java programs.", javaBq17, true);
		Answer javaBq17aD = answerService.createNewAnswer("D - JRE doesn’t contain JVM", javaBq17, false);
		Question javaBq18 = questionService.createNewQuestion(javaBeginner, "Q 18 - Which of the following statements are true about Enum in java?", Question.QuestionType.MULTI_SELECT, 5);
		Answer javaBq18aA = answerService.createNewAnswer("A - All java enum implicitly extends java.lang.Enum class.", javaBq18, true);
		Answer javaBq18aB = answerService.createNewAnswer("B - Java enum can implement interfaces.", javaBq18, true);
		Answer javaBq18aC = answerService.createNewAnswer("C - We can create instance of enum using new operator.", javaBq18, false);
		Answer javaBq18aD = answerService.createNewAnswer("D - Enums can’t be used in switch statements.", javaBq18, false);
		Question javaBq19 = questionService.createNewQuestion(javaBeginner, "Q 19 - Which of the below are built-in class loaders in java?", Question.QuestionType.MULTI_SELECT, 5);
		Answer javaBq19aA = answerService.createNewAnswer("A - Bootstrap Class Loader", javaBq19, true);
		Answer javaBq19aB = answerService.createNewAnswer("B - Extensions Class Loader", javaBq19, true);
		Answer javaBq19aC = answerService.createNewAnswer("C - Runtime Class Loader", javaBq19, false);
		Answer javaBq19aD = answerService.createNewAnswer("D - System Class Loader", javaBq19, true);
		Question javaBq20 = questionService.createNewQuestion(javaBeginner, "Q 20 - Which of the following statements are true about annotations in java?", Question.QuestionType.MULTI_SELECT, 5);
		Answer javaBq20aA = answerService.createNewAnswer("A - @interface keyword is used to create custom annotation", javaBq20, true);
		Answer javaBq20aB = answerService.createNewAnswer("B - @Override is a built-in annotation in java", javaBq20, true);
		Answer javaBq20aC = answerService.createNewAnswer("C - Java annotation information gets lost when class is compiled.", javaBq20, false);
		Answer javaBq20aD = answerService.createNewAnswer("D - @Retention is one of the meta annotation in java.", javaBq20, true);
		log.info("quiz init finished");

//		log.debug("Find by Java");
//		for (Placement p : placementService.findBySkillName("Java")) {
//			log.debug(p);
//		}
//		log.debug("Find by name " + placementService.findByName("Placement1"));
//		log.debug("Display all ");
//		for (Placement p : placementService.findAll()) {
//			log.debug(p);
//		}
//		log.debug("Find by client name");
//		for (Placement p : placementService.findByClientName("ANZ")) {
//			log.debug(p);
//		}
//		log.debug("Find by Location Sydney");
//		for (Placement p : placementService.findByLocation("Sydney")) {
//			log.debug(p);
//		}
//
//		log.debug("find trainee by name");
//		for (Trainee t : traineeService.findTraineeByName("Mancy")) {
//			log.debug(t);
//		}
//
//		log.debug("find trainee by skill");
//		for (Trainee t : traineeService.findTraineeBySkills(skillLevel1)) {
//			log.debug(t);
//		}
		log.info("Finished Data Setup");
	}

	private void createResult() {
		log.debug("Creating Result");
		try {
			Quiz quiz = new Quiz("Test Quiz For Results", "For testing trainer marking", 1000, 5, 3.0);
					
			Question q1 = new Question(quiz, "Test Quiz", QuestionType.MUTIPLE_CHOICE, 4, new ArrayList<Answer>());
			Answer q1a = new Answer("Answer 1", q1, false);
			Answer q1a1 = new Answer("Answer 2", q1, true);
			Answer q1a2 = new Answer("Answer 3", q1, false);
			q1.addAnswers(q1a);
			q1.addAnswers(q1a1);
			q1.addAnswers(q1a2);
			quizService.saveAnswer(q1a);
			quizService.saveAnswer(q1a1);
			quizService.saveAnswer(q1a2);
			quizService.saveQuestion(q1);

			Question q2 = new Question(quiz, "MultiSelect", QuestionType.MULTI_SELECT, 4, new ArrayList<Answer>());
			Answer q2a = new Answer("Answer 1", q2, true);
			Answer q2a1 = new Answer("Answer 2", q2, false);
			Answer q2a2 = new Answer("Answer 3", q2, true);
			q2.addAnswers(q2a);
			q2.addAnswers(q2a1);
			q2.addAnswers(q2a2);

			Question q3 = new Question(quiz, "Short Answer", QuestionType.SHORT_ANSWER, 10, new ArrayList<Answer>());
			Answer q3a1 = new Answer("Short Answer", q3, true);
			q3.addAnswers(q3a1);

			quiz.addQuestion(q1);
			quiz.addQuestion(q2);
			quiz.addQuestion(q3);

			Question q5 = new Question(quiz, "MultiSelectadifsjklfj;lasdkjf;laskdjf;alsdkjf", QuestionType.MULTI_SELECT,
					4, new ArrayList<Answer>());
			Answer q5a = new Answer("Answer 1", q5, true);
			Answer q5a1 = new Answer("Answer 2", q5, false);
			Answer q5a2 = new Answer("Answer 3", q5, true);
			q5.addAnswers(q5a);
			q5.addAnswers(q5a1);
			q5.addAnswers(q5a2);

			quizService.saveAnswer(q3a1);
			quizService.saveQuestion(q3);

			quizService.saveAnswer(q2a);
			quizService.saveAnswer(q2a1);
			quizService.saveAnswer(q2a2);
			quizService.saveQuestion(q2);
			quizService.saveAnswer(q5a2);
			quizService.saveAnswer(q5a1);
			quizService.saveAnswer(q5a);
			quizService.saveQuestion(q5);

			quiz = quizService.saveQuiz(quiz);

			// Quiz quiz = quizService.findQuizById(31).get();
			log.debug(quiz.getQuestions());
			Trainee trainee = traineeService.getTraineeByID(14);
			SubmittedAnswer sa1 = new SubmittedAnswer(quiz.getQuestions().get(0), null,
					quiz.getQuestions().get(0).getAnswers().get(0), null);
			SubmittedAnswer sa2 = new SubmittedAnswer(quiz.getQuestions().get(1), null,
					quiz.getQuestions().get(1).getAnswers().get(0), null);
			SubmittedAnswer sa3 = new SubmittedAnswer(quiz.getQuestions().get(2), null,
					quiz.getQuestions().get(2).getAnswers().get(0), "answer to short answer type question");
			SubmittedAnswer sa4 = new SubmittedAnswer(quiz.getQuestions().get(1), null,
					quiz.getQuestions().get(1).getAnswers().get(1), null);


			sa1 = submittedAnswerService.save(sa1);
			sa2 = submittedAnswerService.save(sa2);
			sa3 = submittedAnswerService.save(sa3);
			sa4 = submittedAnswerService.save(sa4);
			
			Skill skill = skillService.save(new Skill("resultTest"));
			log.debug(skill);
			SkillLevel skillLevel = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, skill, quiz);
			log.debug(skillLevel);
			skillLevelService.save(skillLevel);
			quiz.setSkillLevel(skillLevel);
			quiz = quizService.saveQuiz(quiz);
			
			Result result = new Result(0, false, false, trainee, quiz,
					new ArrayList<SubmittedAnswer>(Arrays.asList(sa1, sa2, sa3, sa4)));
			
			sa1.setResult(result);
			sa2.setResult(result);
			sa3.setResult(result);
			sa4.setResult(result);

			result = quizService.saveResult(result);
			

//			sa1 = submittedAnswerService.save(sa1);
//			sa2 = submittedAnswerService.save(sa2);
//			sa3 = submittedAnswerService.save(sa3);
			
			List<Result> results = trainee.getResults();
			results.add(result);
			trainee.setResults(results);
			traineeService.save(trainee);
			
			log.debug("Created Result: " + result);
		} catch (NoSuchElementException e) {
			log.warn("Couldn't create result");
		}
	}

	public void createQuiz() {

//		Quiz quiz = new Quiz("Java Quiz", "For Java Students", 1000, 5, 50.0,new ArrayList<Question>());
//		
//		Question q1 = new Question(quiz,"Test Quiz", QuestionType.MUTIPLE_CHOICE, 4, new ArrayList<Answer>());
//		Answer q1a = new Answer("Answer 1", q1, false);
//		Answer q1a1 = new Answer("Answer 2", q1, true);
//		Answer q1a2 = new Answer("Answer 3", q1, false);
//		q1.addAnswers(q1a);
//		q1.addAnswers(q1a1);
//		q1.addAnswers(q1a2);
//		quizService.saveAnswer(q1a);
//		quizService.saveAnswer(q1a1);
//		quizService.saveAnswer(q1a2);
//		quizService.saveQuestion(q1);
//		
//		Question q2 = new Question(quiz,"MultiSelect", QuestionType.MULTI_SELECT, 4, new ArrayList<Answer>());
//		Answer q2a = new Answer("Answer 1", q2, true);
//		Answer q2a1 = new Answer("Answer 2", q2, false);
//		Answer q2a2 = new Answer("Answer 3", q2, true);	
//		q2.addAnswers(q2a);
//		q2.addAnswers(q2a1);
//		q2.addAnswers(q2a2);
//
//		
//		
//		Question q3 = new Question(quiz,"Short Answer", QuestionType.SHORT_ANSWER, 10, new ArrayList<Answer>());
//		Answer q3a1 = new Answer("Short Answer", q3, true);
//		q3.addAnswers(q3a1);
//		
//		quiz.addQuestion(q1);
//		
//		quiz.addQuestion(q3);
//		quiz.addQuestion(q2);
//		
//		Question q5 = new Question(quiz,"MultiSelectadifsjklfj;lasdkjf;laskdjf;alsdkjf", QuestionType.MULTI_SELECT, 4, new ArrayList<Answer>());
//		Answer q5a = new Answer("Answer 1", q5, true);
//		Answer q5a1 = new Answer("Answer 2", q5, false);
//		Answer q5a2 = new Answer("Answer 3", q5, true);	
//		q5.addAnswers(q5a);
//		q5.addAnswers(q5a1);
//		q5.addAnswers(q5a2);
//		
//		
//		quizService.saveAnswer(q3a1);
//		quizService.saveQuestion(q3);
//		
//		quizService.saveAnswer(q2a);
//		quizService.saveAnswer(q2a1);
//		quizService.saveAnswer(q2a2);
//		quizService.saveQuestion(q2);
//		quizService.saveAnswer(q5a2);
//		quizService.saveAnswer(q5a1);
//		quizService.saveAnswer(q5a);
//		quizService.saveQuestion(q5);
//		Quiz cpp = new Quiz("C++", "For C++ Students", 10, 5, 50.0,new ArrayList<Question>());
//		Quiz linux = new Quiz("Linux Quiz", "Debian and linux for the bois", 10, 5, 50.0,new ArrayList<Question>());
//		
//		
//
//		
//		Quiz savedQuiz = quizService.saveQuiz(quiz);
//		quizService.saveQuiz(linux);
//		quizService.saveQuiz(cpp);
//		log.info("SAVED QUIZ ID: " + savedQuiz.getQuizId());
//		log.info("Questions: " + savedQuiz.getQuestions());

	}

	public void createTrainee() {
		// Create Suggested Skills
		SuggestedSkill suggestedSkill = new SuggestedSkill("java");
		suggestedSkillService.save(suggestedSkill);

		// Create Skills
		Skill java = new Skill("java");
		Skill cpp = new Skill("cpp");
		Skill react = new Skill("react");

		log.info("Saving Skills");
		skillService.save(Arrays.asList(java, cpp, react));

		// Create Skill levels
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

		// Create Trainee with skills
		Trainee trainee = new Trainee("trainee1", "123");
		trainee.setFirstName("Zero");
		trainee.setLastName("Tea");
		log.info("Saving Trainee");
		trainee = traineeService.save(trainee);
		log.debug(trainee);
		trainee.setSkills(new ArrayList<>(Arrays.asList(javaBeginner, cppIntermediate, reactBeginner)));

		List<SkillLevel> skills = traineeService.getSkills(trainee.getUserId());
		log.debug(skills);

		List<SkillLevel> pinnedSkills = traineeService.getPinnedSkills(trainee.getUserId());
		log.debug(pinnedSkills);

		List<SkillLevel> allSkills = traineeService.getAllSkills(trainee.getUserId());
		log.debug(allSkills);

		// Change pinned skills
		traineeService.pinSkill(trainee.getUserId(), javaBeginner.getSkillLevelId());
		log.debug("After pinning: " + trainee);

		traineeService.unpinSkill(trainee.getUserId(), javaBeginner.getSkillLevelId());
		log.debug("After unpinning: " + trainee);

	}
}
