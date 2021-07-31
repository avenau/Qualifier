package com.fdm.qualifier.dataloader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
import com.fdm.qualifier.repository.QuestionRepository;
import com.fdm.qualifier.repository.QuizRepository;
import com.fdm.qualifier.service.SuggestedSkillService;

@Component
public class Dataloader implements ApplicationRunner {
//	Logger logger = LogManager.getLogManager();
	private SuggestedSkillService suggestedSkillService;
	
	@Autowired
	AnswerRepository answerRepo;
	
	@Autowired
	QuizRepository quizRepo;
	
	@Autowired
	QuestionRepository questionRepo;

	@Autowired
	public Dataloader(SuggestedSkillService suggestedSkillService) {
		super();
		this.suggestedSkillService = suggestedSkillService;
	}

	@Override
	@Transactional
	@Modifying
	public void run(ApplicationArguments args) throws Exception {
		SuggestedSkill suggestedSkill = new SuggestedSkill("java");
		
		suggestedSkillService.save(suggestedSkill);
		
		System.out.println("init");
		
		byte[] imageBytes = Files.readAllBytes(Paths.get("C:\\Users\\shirl\\Desktop\\against.jpg"));
		
		Question question1 = new Question(null, "text", Question.QuestionType.MUTIPLE_CHOICE, 5, imageBytes, null);
		Answer answer1 = new Answer("answerText", question1, true);
		Quiz quiz1 = new Quiz("Java", "Java entry", 20,  10, 75, List.of(question1));
		
		question1.setQuiz(quiz1);
		question1.setAnswers(List.of(answer1));
		
		
		questionRepo.save(question1);
		answerRepo.save(answer1);
		quizRepo.save(quiz1);
		
		System.out.println(question1);
		System.out.println(answer1);
		System.out.println(quiz1);
		System.out.println("init finished");
	}

}
