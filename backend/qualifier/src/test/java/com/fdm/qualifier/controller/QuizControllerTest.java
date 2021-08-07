package com.fdm.qualifier.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.dto.AnswerDTO2;
import com.fdm.qualifier.dto.QuestionDTO2;
import com.fdm.qualifier.dto.QuizDTO;
import com.fdm.qualifier.dto.ResultDTO;
import com.fdm.qualifier.httpRequest.QuizRequest;
import com.fdm.qualifier.httpRequest.QuizRequest2;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Question.QuestionType;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.Skill;
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

public class QuizControllerTest {
	QuizController quizController;

	@Mock
	QuizService quizServiceMock;

	@Mock
	SkillLevelService skillLevelServiceMock;

	@Mock
	QuestionService questionServiceMock;

	@Mock
	AnswerService answerServiceMock;

	@Mock
	ResultService resultServiceMock;

	@Mock
	SubmittedAnswerService submittedAnswerServiceMock;

	@Mock
	TraineeService traineeServiceMock;

	@Mock
	Result resultMock;

	@Mock
	Trainee traineeMock;

	@Mock
	SkillLevel skillLevelMock;

	@Mock
	Quiz quizMock;

	@Mock
	ResultDTO resultDTOMock;

	@Mock
	Skill skillMock;

	@Mock
	QuizRequest2 quizRequest2Mock;
	
	@Mock
	QuestionDTO2 questionDTO2Mock;

	@Mock
	AnswerDTO2 answerDTO2Mock;

	@Mock
	Question questionMock;

	@Mock
	QuizDTO quizDTOMock;

	@Mock
	private QuizRequest quizRequestDTO;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		quizController = new QuizController(quizServiceMock, skillLevelServiceMock, questionServiceMock,
				answerServiceMock, submittedAnswerServiceMock, resultServiceMock, traineeServiceMock);
	}

	@Test

	public void getAllQuizzes_returnList() {
		List<Quiz> expected = new ArrayList<Quiz>();
		when(quizServiceMock.findAllQuiz()).thenReturn(expected);

		List<Quiz> result = quizController.getAllQuizzes();

		assertEquals(expected, result);
	}

	@Test
	public void submitMarkedResult_verifyPasssingResult() {
		double[] resultTest = { 1, 80.23 };
		when(quizServiceMock.findResultById(1)).thenReturn(resultMock);
		when(resultMock.getTrainee()).thenReturn(traineeMock);
		when(resultMock.getQuiz()).thenReturn(quizMock);
		when(quizMock.getSkillLevel()).thenReturn(skillLevelMock);
		when(quizMock.getPassingMark()).thenReturn(75.00);
		when(traineeServiceMock.save(traineeMock)).thenReturn(traineeMock);

		quizController.submitMarkedResult(resultTest);

	}

	@Test
	public void test_getResult_returns_result_when_found() {
		// Arrange
		int[] id = { 1 };
		int traineeId = 1;
		when(resultMock.getResultId()).thenReturn(id[0]);
		when(quizServiceMock.findResultById(id[0])).thenReturn(resultMock);
		when(traineeMock.getUserId()).thenReturn(traineeId);
		when(quizMock.getSkillLevel()).thenReturn(skillLevelMock);
		when(skillLevelMock.getSkill()).thenReturn(skillMock);
		when(resultMock.getSubmittedAnswers()).thenReturn(new ArrayList<>());
		when(resultMock.getTrainee()).thenReturn(traineeMock);
		when(resultMock.getQuiz()).thenReturn(quizMock);

		ResultDTO expected = new ResultDTO(resultMock);

		// Act
		ResultDTO actual = quizController.getResult(id);

		// Assert
		verify(quizServiceMock, times(1)).findResultById(id[0]);
		verify(resultMock, times(2)).setMark(resultMock.getMark());
		verify(resultMock, times(2)).setMarked(resultMock.isMarked());
		verify(resultMock, times(2)).getMark();
		verify(quizMock, times(2)).getPassingMark();
		assertEquals(expected.getMark(), actual.getMark());
		assertEquals(expected.getQuiz().getQuizId(), actual.getQuiz().getQuizId());
		assertEquals(expected.getResultId(), actual.getResultId());
		assertEquals(expected.getSubmittedAnswers(), actual.getSubmittedAnswers());
		assertEquals(expected.getTraineeId(), actual.getTraineeId());
	}

	@Test
	public void test_getResult_returns_null_when_not_found() {
		//Arrange
		int[] id = { 1 };
		int traineeId = 1;
		when(resultMock.getResultId()).thenReturn(id[0]);
		when(quizServiceMock.findResultById(id[0])).thenReturn(null);
		when(traineeMock.getUserId()).thenReturn(traineeId);
		when(quizMock.getSkillLevel()).thenReturn(skillLevelMock);
		when(skillLevelMock.getSkill()).thenReturn(skillMock);
		when(resultMock.getSubmittedAnswers()).thenReturn(new ArrayList<>());
		when(resultMock.getTrainee()).thenReturn(traineeMock);
		when(resultMock.getQuiz()).thenReturn(quizMock);

		ResultDTO expected = new ResultDTO(resultMock);

		// Act
		ResultDTO actual = quizController.getResult(id);

		// Assert
		verify(resultMock, times(1)).getResultId();
		verify(quizServiceMock, times(1)).findResultById(id[0]);
		verify(resultMock, times(1)).setMark(resultMock.getMark());
		verify(resultMock, times(1)).setMarked(resultMock.isMarked());
		verify(resultMock, times(1)).getMark();
		verify(quizMock, times(1)).getPassingMark();
		assertEquals(null, actual);
	}

	@Test
	public void test_submitMarkedResult_adds_skill_to_trainee_when_passed_and_non_nulls() {
		//Arrange
		double[] result = {5,25};
		when(quizServiceMock.findResultById((int)result[0])).thenReturn(resultMock);
		when(resultMock.getTrainee()).thenReturn(traineeMock);
		when(resultMock.getQuiz()).thenReturn(quizMock);
		when(resultMock.getQuiz().getSkillLevel()).thenReturn(skillLevelMock);
		when(quizServiceMock.saveResult(resultMock)).thenReturn(resultMock);
		when(traineeServiceMock.save(traineeMock)).thenReturn(traineeMock);
		when(resultMock.isPassed()).thenReturn(true);
		
		//Act
		quizController.submitMarkedResult(result);
		
		//Assert
		verify(quizServiceMock, times(1)).findResultById((int)result[0]);
		verify(resultMock, times(1)).getTrainee();
		verify(resultMock, times(3)).getQuiz();
		verify(quizMock, times(1)).getSkillLevel();
		verify(quizServiceMock, times(1)).saveResult(resultMock);
		verify(traineeMock, times(1)).addSkill(skillLevelMock);
		verify(traineeServiceMock, times(1)).save(traineeMock);
	}
	
	@Test
	public void test_createNewQuizByTrainer() {
		int quizId = 0;
		String questionType= "MULTIPLE_CHOICE";
		String questionContent= "Question Content";
		String answerContent = "Answer Content";
		int questionPoint = 5;
		List<QuestionDTO2> list = new ArrayList<QuestionDTO2> ();
		list.add(questionDTO2Mock);
		List<AnswerDTO2> answerList = new ArrayList<AnswerDTO2> ();
		answerList.add(answerDTO2Mock);
		
		when(questionDTO2Mock.getQuestionType()).thenReturn(questionType);
		when(questionDTO2Mock.getQuestionPoints()).thenReturn(questionPoint);
		when(questionDTO2Mock.getQuestionContent()).thenReturn(questionContent);
		when(quizRequest2Mock.getQuizId()).thenReturn(quizId);
		when(quizServiceMock.findQuizById(quizId)).thenReturn(Optional.of(quizMock));
		when (quizRequest2Mock.getQuestions()).thenReturn(list);
		when(questionDTO2Mock.getAnswers()).thenReturn(answerList);
		when(answerDTO2Mock.isCorrect()).thenReturn(true);
		when(answerDTO2Mock.getContent()).thenReturn(answerContent);
		when(questionServiceMock.createNewQuestion(quizMock, questionContent, QuestionType.MULTIPLE_CHOICE, questionPoint)).thenReturn(questionMock);
		quizController.createNewQuizByTrainer(quizRequest2Mock);
		
		verify(questionServiceMock, times(1)).createNewQuestion(quizMock, questionContent, QuestionType.MULTIPLE_CHOICE, questionPoint);
		verify(answerServiceMock, times(1)).createNewAnswer(answerContent, questionMock, true);
	}
	
	@Test
	public void test_createQuizDetails() {
		//ASSIGN
		String idString = "5";
		int id = 5;
		int quizId = 10;
		
		//ACT
		when(skillLevelServiceMock.findById(id)).thenReturn(Optional.of(skillLevelMock));
		when(quizServiceMock.createNewQuizDTO(null, null, 0, 0, 0)).thenReturn(quizDTOMock);
		when(quizServiceMock.findQuizById(quizId)).thenReturn(Optional.of(quizMock));
		when(quizDTOMock.getQuizId()).thenReturn(quizId);
		QuizDTO result = quizController.createQuizDetails(idString);
		
		//ASSERT
		
		verify(skillLevelMock, times(1)).setQuiz(quizMock);
		verify(skillLevelServiceMock, times(1)).save(skillLevelMock);
		assertEquals(result, quizDTOMock);	
	}
	
	@Test
	public void test_getQuizDetails() {
		//Assign
		String idStr = "5";
		int id = 5;
		
		//Act
		when(quizServiceMock.findQuizDTOById(id)).thenReturn(quizDTOMock);
		QuizDTO result = quizController.getQuizDetails(idStr);
		
		//Assert
		assertEquals(result, quizDTOMock);
	}
	
	@Test
	public void test_updateQuizDetails() {
		//Assign
		
		//Act
		when(quizServiceMock.updateQuiz(quizRequestDTO)).thenReturn(quizDTOMock);
		QuizDTO result = quizController.updateQuizDetails(quizRequestDTO);
		//Assert
		assertEquals(quizDTOMock, result);
	}
	
	@Test
	public void test_removeQuiz() {
		//Assign
		String idStr = "10";
		int id = 10;
		
		//Act
		when(quizServiceMock.findQuizById(id)).thenReturn(Optional.of(quizMock));	
		quizController.updateQuizDetails(idStr);
		//Assert
		verify(quizServiceMock, times(1)).deleteQuiz(quizMock);
		
		
	}
	
	
//	
//	@Test
//	public void test_getResult_returns_result_when_found() {
//		//Arrange
//		int id = 1;
//		when(resultMock.getResultId()).thenReturn(id);
//		when(quizServiceMock.findResultById(id)).thenReturn(resultMock);
//		
//		//Act
//		Result actual = quizController.getResult(resultMock);
//		
//		//Assert
//		verify(resultMock, times(1)).getResultId();
//		verify(quizServiceMock, times(1)).findResultById(id);
//		assertEquals(resultMock, actual);
//	}

//	@Test
//	public void test_getResult_returns_null_when_not_found() {
//		//Arrange
//				int id = 1;
//				when(resultMock.getResultId()).thenReturn(id);
//				when(quizServiceMock.findResultById(id)).thenReturn(null);
//				
//				//Act
//				Result actual = quizController.getResult(resultMock);
//				
//				//Assert
//				verify(resultMock, times(1)).getResultId();
//				verify(quizServiceMock, times(1)).findResultById(id);
//				assertEquals(null, actual);
//	}

//	@Test
//	public void test_submitMarkedResult_adds_skill_to_trainee_when_passed_and_non_nulls() {
//		//Arrange
//		int id = 1;
//		when(resultMock.getResultId()).thenReturn(id);
//		when(quizServiceMock.findResultById(id)).thenReturn(resultMock);
//		when(resultMock.getTrainee()).thenReturn(traineeMock);
//		when(resultMock.getQuiz()).thenReturn(quizMock);
//		when(resultMock.getQuiz().getSkillLevel()).thenReturn(skillLevelMock);
//		when(quizServiceMock.saveResult(resultMock)).thenReturn(resultMock);
//		when(traineeServiceMock.save(traineeMock)).thenReturn(traineeMock);
//		when(resultMock.isPassed()).thenReturn(true);
//		
//		//Act
//		quizController.submitMarkedResult(resultMock);
//		
//		//Assert
//		verify(quizServiceMock, times(1)).findResultById(id);
//		verify(resultMock, times(1)).getTrainee();
//		verify(resultMock, times(2)).getQuiz();
//		verify(quizMock, times(1)).getSkillLevel();
//		verify(quizServiceMock, times(1)).saveResult(resultMock);
//		verify(traineeMock, times(1)).addSkill(skillLevelMock);
//		verify(traineeServiceMock, times(1)).save(traineeMock);
//	}

//	@Test
//	public void test_submitMarkedResult_does_not_adds_skill_to_trainee_when_not_passed_and_non_nulls() {
//		//Arrange
//		int id = 1;
//		when(resultMock.getResultId()).thenReturn(id);
//		when(quizServiceMock.findResultById(id)).thenReturn(resultMock);
//		when(resultMock.getTrainee()).thenReturn(traineeMock);
//		when(resultMock.getQuiz()).thenReturn(quizMock);
//		when(resultMock.getQuiz().getSkillLevel()).thenReturn(skillLevelMock);
//		when(quizServiceMock.saveResult(resultMock)).thenReturn(resultMock);
//		when(traineeServiceMock.save(traineeMock)).thenReturn(traineeMock);
//		when(resultMock.isPassed()).thenReturn(false);
//		
//		//Act
//		quizController.submitMarkedResult(resultMock);
//		
//		//Assert
//		verify(quizServiceMock, times(1)).findResultById(id);
//		verify(resultMock, times(1)).getTrainee();
//		verify(resultMock, times(2)).getQuiz();
//		verify(quizMock, times(1)).getSkillLevel();
//		verify(quizServiceMock, times(1)).saveResult(resultMock);
//		verify(traineeMock, times(0)).addSkill(skillLevelMock);
//		verify(traineeServiceMock, times(0)).save(traineeMock);
//	}

}
