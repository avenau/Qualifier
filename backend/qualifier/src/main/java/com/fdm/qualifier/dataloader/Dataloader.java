package com.fdm.qualifier.dataloader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Question.QuestionType;
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

		Quiz quiz = new Quiz("Java Quiz", "For Java Students", 1000, 5, 50.0,new ArrayList<Question>());
		
		Question q1 = new Question(quiz,"Test Quiz", QuestionType.MUTIPLE_CHOICE, 4, new ArrayList<Answer>());
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
		
		Question q2 = new Question(quiz,"MultiSelect", QuestionType.MULTI_SELECT, 4, new ArrayList<Answer>());
		Answer q2a = new Answer("Answer 1", q2, true);
		Answer q2a1 = new Answer("Answer 2", q2, false);
		Answer q2a2 = new Answer("Answer 3", q2, true);	
		q2.addAnswers(q2a);
		q2.addAnswers(q2a1);
		q2.addAnswers(q2a2);

		
		
		Question q3 = new Question(quiz,"Short Answer", QuestionType.SHORT_ANSWER, 10, new ArrayList<Answer>());
		Answer q3a1 = new Answer("Short Answer", q3, true);
		q3.addAnswers(q3a1);
		
		quiz.addQuestion(q1);
		
		quiz.addQuestion(q3);
		quiz.addQuestion(q2);
		
		Question q5 = new Question(quiz,"MultiSelectadifsjklfj;lasdkjf;laskdjf;alsdkjf", QuestionType.MULTI_SELECT, 4, new ArrayList<Answer>());
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
		Quiz cpp = new Quiz("C++", "For C++ Students", 10, 5, 50.0,new ArrayList<Question>());
		Quiz linux = new Quiz("Linux Quiz", "Debian and linux for the bois", 10, 5, 50.0,new ArrayList<Question>());
		
		

		
		Quiz savedQuiz = quizService.saveQuiz(quiz);
		quizService.saveQuiz(linux);
		quizService.saveQuiz(cpp);
		log.info("SAVED QUIZ ID: " + savedQuiz.getQuizId());
		log.info("Questions: " + savedQuiz.getQuestions());
		
		
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
