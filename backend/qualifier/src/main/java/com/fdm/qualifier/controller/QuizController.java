package com.fdm.qualifier.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.dto.AnswerDTO2;
import com.fdm.qualifier.dto.QuestionDTO2;
import com.fdm.qualifier.dto.QuizDTO;
import com.fdm.qualifier.dto.ResultDTO;
import com.fdm.qualifier.httpRequest.QuizRequest;
import com.fdm.qualifier.httpRequest.QuizRequest2;
import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Question.QuestionType;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.SubmittedAnswer;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.AnswerService;
import com.fdm.qualifier.service.QuestionService;
import com.fdm.qualifier.service.QuizService;
import com.fdm.qualifier.service.ResultService;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.SubmittedAnswerService;
import com.fdm.qualifier.service.TraineeService;

/**
 * Quiz Controller
 * 
 * @author William
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
	Logger logger = LogManager.getLogger();
	Log log = LogFactory.getLog(QuizController.class);

	private QuizService quizService;
	private SkillLevelService skillLevelService;
	private QuestionService questionService;
	private AnswerService answerService;
	private SubmittedAnswerService submittedAnswerService;
	private ResultService resultService;
	private TraineeService traineeService;

	@Autowired
	public QuizController(QuizService quizService, SkillLevelService skillLevelService, QuestionService questionService,
			AnswerService answerService, SubmittedAnswerService submittedAnswerService, ResultService resultService,
			TraineeService traineeService) {
		super();
		this.quizService = quizService;
		this.skillLevelService = skillLevelService;
		this.questionService = questionService;
		this.answerService = answerService;
		this.submittedAnswerService = submittedAnswerService;
		this.resultService = resultService;
		this.traineeService = traineeService;
	}

	/**
	 * Gets quiz by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/quiz/get/{id}")
	public QuizDTO getQuizDetails(@PathVariable("id") String id) {
		int quizId = Integer.parseInt(id);
		QuizDTO quizDTO = quizService.findQuizDTOById(quizId);

		return quizDTO;
	}

	/**
	 * Submit quiz from payload of mapped String Object
	 * 
	 * @param payload
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/quiz/submit")
	public Result submitQuiz(@RequestBody Map<String, Object> payload) throws Exception {
		System.out.println("DSFLSDJF");
		double totalMark = 0.0;
		for (Map<String, Object> content : (ArrayList<Map<String, Object>>) payload.get("payload")) {
			int id = Integer.parseInt((String) content.get("quizId"));
			if (quizService.findQuizById(id).isPresent()) {
			} else {
				Result badResult = new Result();
				badResult.setResultId(-5);
				return badResult;
			}

			break;
		}

		double passingMark = 75;
		int quizId = -1;
		Integer userId = null;
		List<SubmittedAnswer> submittedAnswers = new ArrayList<SubmittedAnswer>();
		boolean marked = true;
		boolean passed = false;

		for (Map<String, Object> content : (ArrayList<Map<String, Object>>) payload.get("payload")) {
			userId = Integer.parseInt((String) content.get("userId"));
			quizId = Integer.parseInt((String) content.get("quizId"));

			int questionId = Integer.parseInt((String) content.get("questionId"));
			Question question = questionService.findById(questionId);
			Question.QuestionType questionType = question.getType();
			String answerContent = (String) content.get("answerContent");

			double unitMark = question.getPoints();
			if (questionType.equals(QuestionType.SHORT_ANSWER)) {

				marked = false;
				totalMark -= unitMark;
				SubmittedAnswer submittedAnswer = submittedAnswerService.createNewShortAnswer(question, answerContent);
				submittedAnswers.add(submittedAnswer);
			} else {

				for (Object id : (ArrayList<Object>) content.get("answerId")) {
					int answerId = Integer.parseInt((String) id);
					Answer answer = answerService.finById(answerId).get();

					if (!answer.isCorrect()) {
						totalMark -= unitMark;
						logger.info("Wrong answer : " + answer.getContent() + "lost points: " + unitMark);
						break;
					}

					SubmittedAnswer submittedAnswer = submittedAnswerService.createNewSelectedAnswer(question, answer,
							answerContent);
					submittedAnswers.add(submittedAnswer);
				}
			}

			unitMark = question.getPoints();
			totalMark += unitMark;
		}

		Trainee trainee = traineeService.getTraineeByID(userId);

		if (quizId == -1) {
			throw new Exception("Quiz id not found");
		}
		Quiz quiz = quizService.findQuizById(quizId).get();
		passingMark = quiz.getPassingMark();

		SkillLevel skillLevel = skillLevelService.findByQuizId(quiz);

		if (marked && totalMark >= passingMark) {
			passed = true;
			List<SkillLevel> skillList = trainee.getSkills();
			skillList.add(skillLevel);
			traineeService.save(trainee);
		}

		Result result = resultService.createNewResult(totalMark, passed, marked, trainee, quiz, submittedAnswers);
		log.debug("ABOUT TO SAVE TRAINEE");
		traineeService.save(trainee);

		System.out.println("Submit Result Return " + result);
		return result;

	}

	/**
	 * Create QuizDTO from quiz based on id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/quiz/create/{id}")
	public QuizDTO createQuizDetails(@PathVariable("id") String id) {
		int skillLevelId = Integer.parseInt(id);
		SkillLevel skillLevel = skillLevelService.findById(skillLevelId).get();
		QuizDTO quizDTO = quizService.createNewQuizDTO(null, null, 0, 0, 0);
		Quiz quiz = quizService.findQuizById(quizDTO.getQuizId()).get();
		skillLevel.setQuiz(quiz);
		skillLevelService.save(skillLevel);
		return quizDTO;
	}

	/**
	 * Create quiz from QuizRequest2 object
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/createNewQuiz")
	public Map<String, String> createNewQuizByTrainer(@RequestBody QuizRequest2 request) {
		Map<String, String> status = new HashMap<String, String>();

		int quizId = request.getQuizId();
		System.out.println("QUIZ ID:  " + quizId);
		Quiz quiz = quizService.findQuizById(quizId).get();

		quiz.setName(request.getName());
		quiz.setDescription(request.getDescription());
		quiz.setDuration(request.getDuration());
		quiz.setPassingMark(request.getPassingMark());
		List<QuestionDTO2> questionDTOs = request.getQuestions();
		for (QuestionDTO2 questionDTO : questionDTOs) {
			Question question = questionService.createNewQuestion(quiz, questionDTO.getQuestionContent(),
					QuestionType.valueOf(questionDTO.getQuestionType()), questionDTO.getQuestionPoints());
			for (AnswerDTO2 answer : questionDTO.getAnswers()) {
				answerService.createNewAnswer(answer.getContent(), question, answer.isCorrect());
			}
		}
		status.put("status", "success");
		return status;
	}

	/**
	 * Update a quiz and return a QuizDTO
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/quiz/update")
	public QuizDTO updateQuizDetails(@RequestBody QuizRequest request) {
		return quizService.updateQuiz(request);
	}

	/**
	 * Removes Quizs based on Id
	 * 
	 * @param qId
	 */
	@GetMapping("/quiz/remove/{id}")
	public void updateQuizDetails(@PathVariable("id") String qId) {
		Quiz requestedQuiz = quizService.findQuizById(Integer.parseInt(qId)).get();
		quizService.deleteQuiz(requestedQuiz);
	}

	/**
	 * Get all quizzes
	 * 
	 * @return
	 */
	@GetMapping("/getAllQuizzes")
	public List<Quiz> getAllQuizzes() {
		return quizService.findAllQuiz();
	}

	/**
	 * Get a result as a DTO based on id
	 * 
	 * @param resultId
	 * @return
	 */
	@PostMapping("/getResult")
	public ResultDTO getResult(@RequestBody int[] resultId) {
		log.debug(resultId);
		Result result = quizService.findResultById(resultId[0]);
		if (result != null)
			log.debug(result.getSubmittedAnswers());
		return new ResultDTO(result);
	}

	/**
	 * Update a result based on result id and mark
	 * 
	 * @param result
	 */
	@PostMapping("/submitMarkedResult")
	public void submitMarkedResult(@RequestBody double[] result) {
		log.debug("Result: " + result);
		Result oldResult = quizService.findResultById((int) result[0]);
		Trainee trainee = oldResult.getTrainee();
		SkillLevel skillLevel = oldResult.getQuiz().getSkillLevel();
		oldResult.setMark(result[1]);
		oldResult.setMarked(true);
		oldResult.setPassed(oldResult.getMark() >= oldResult.getQuiz().getPassingMark());
		if (oldResult.isPassed() && skillLevel != null && trainee != null) {
			trainee.removeSkill(skillLevel.getSkill());
			trainee.removePinnedSkill(skillLevel.getSkill());
			trainee.addSkill(skillLevel);
			trainee = traineeService.save(trainee);
			log.debug(trainee);
		}
		quizService.saveResult(oldResult);
	}

}
