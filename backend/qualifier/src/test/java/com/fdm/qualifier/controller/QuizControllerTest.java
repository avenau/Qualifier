package com.fdm.qualifier.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.dto.ResultDTO;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
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
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		quizController = new QuizController(quizServiceMock, skillLevelServiceMock, questionServiceMock, answerServiceMock, submittedAnswerServiceMock, resultServiceMock, traineeServiceMock);
	}
	
	@Test
	public void test_getResult_returns_result_when_found() {
		//Arrange			
		int[] id = {1};
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
		
		//Act
		ResultDTO actual = quizController.getResult(id);
		
		//Assert
		verify(quizServiceMock, times(1)).findResultById(id[0]);
		verify(resultMock, times(1)).setMark(resultMock.getMark());
		verify(resultMock, times(1)).setMarked(resultMock.isMarked());
		verify(resultMock, times(2)).getMark();
		verify(quizMock, times(1)).getPassingMark();
		assertEquals(expected, actual);
	}
	
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
//	
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
