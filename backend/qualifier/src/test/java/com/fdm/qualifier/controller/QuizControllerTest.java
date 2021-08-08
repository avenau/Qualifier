package com.fdm.qualifier.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.Iterator;
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
import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Question.QuestionType;
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

	@Mock
	private Map<String, Object> payloadMock;

	@Mock
	private ArrayList<Map<String, Object>> quizArrayListMock;

	@Mock
	private Iterator<Map<String, Object>> quizArrayListIteratorMock;

	@Mock
	private Map<String, Object> quizMapMock;

	@Mock
	private ArrayList<Object> answerArrayListMock;

	@Mock
	private Iterator<Object> answerArrayListIteratorMock;

	@Mock
	private Answer answerMock;

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
		// Arrange
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
		// Arrange
		double[] result = { 5, 25 };
		when(quizServiceMock.findResultById((int) result[0])).thenReturn(resultMock);
		when(resultMock.getTrainee()).thenReturn(traineeMock);
		when(resultMock.getQuiz()).thenReturn(quizMock);
		when(resultMock.getQuiz().getSkillLevel()).thenReturn(skillLevelMock);
		when(quizServiceMock.saveResult(resultMock)).thenReturn(resultMock);
		when(traineeServiceMock.save(traineeMock)).thenReturn(traineeMock);
		when(resultMock.isPassed()).thenReturn(true);

		// Act
		quizController.submitMarkedResult(result);

		// Assert
		verify(quizServiceMock, times(1)).findResultById((int) result[0]);
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
		String questionType = "MULTIPLE_CHOICE";
		String questionContent = "Question Content";
		String answerContent = "Answer Content";
		int questionPoint = 5;
		List<QuestionDTO2> list = new ArrayList<QuestionDTO2>();
		list.add(questionDTO2Mock);
		List<AnswerDTO2> answerList = new ArrayList<AnswerDTO2>();
		answerList.add(answerDTO2Mock);

		when(questionDTO2Mock.getQuestionType()).thenReturn(questionType);
		when(questionDTO2Mock.getQuestionPoints()).thenReturn(questionPoint);
		when(questionDTO2Mock.getQuestionContent()).thenReturn(questionContent);
		when(quizRequest2Mock.getQuizId()).thenReturn(quizId);
		when(quizServiceMock.findQuizById(quizId)).thenReturn(Optional.of(quizMock));
		when(quizRequest2Mock.getQuestions()).thenReturn(list);
		when(questionDTO2Mock.getAnswers()).thenReturn(answerList);
		when(answerDTO2Mock.isCorrect()).thenReturn(true);
		when(answerDTO2Mock.getContent()).thenReturn(answerContent);
		when(questionServiceMock.createNewQuestion(quizMock, questionContent, QuestionType.MULTIPLE_CHOICE,
				questionPoint)).thenReturn(questionMock);
		quizController.createNewQuizByTrainer(quizRequest2Mock);

		verify(questionServiceMock, times(1)).createNewQuestion(quizMock, questionContent, QuestionType.MULTIPLE_CHOICE,
				questionPoint);
		verify(answerServiceMock, times(1)).createNewAnswer(answerContent, questionMock, true);
	}

	@Test
	public void test_createQuizDetails() {
		// ASSIGN
		String idString = "5";
		int id = 5;
		int quizId = 10;

		// ACT
		when(skillLevelServiceMock.findById(id)).thenReturn(Optional.of(skillLevelMock));
		when(quizServiceMock.createNewQuizDTO(null, null, 0, 0, 0)).thenReturn(quizDTOMock);
		when(quizServiceMock.findQuizById(quizId)).thenReturn(Optional.of(quizMock));
		when(quizDTOMock.getQuizId()).thenReturn(quizId);
		QuizDTO result = quizController.createQuizDetails(idString);

		// ASSERT

		verify(skillLevelMock, times(1)).setQuiz(quizMock);
		verify(skillLevelServiceMock, times(1)).save(skillLevelMock);
		assertEquals(result, quizDTOMock);
	}

	@Test
	public void test_getQuizDetails() {
		// Assign
		String idStr = "5";
		int id = 5;

		// Act
		when(quizServiceMock.findQuizDTOById(id)).thenReturn(quizDTOMock);
		QuizDTO result = quizController.getQuizDetails(idStr);

		// Assert
		assertEquals(result, quizDTOMock);
	}

	@Test
	public void test_updateQuizDetails() {
		// Assign

		// Act
		when(quizServiceMock.updateQuiz(quizRequestDTO)).thenReturn(quizDTOMock);
		QuizDTO result = quizController.updateQuizDetails(quizRequestDTO);
		// Assert
		assertEquals(quizDTOMock, result);
	}

	@Test
	public void test_removeQuiz() {
		// Assign
		String idStr = "10";
		int id = 10;

		// Act
		when(quizServiceMock.findQuizById(id)).thenReturn(Optional.of(quizMock));
		quizController.updateQuizDetails(idStr);
		// Assert
		verify(quizServiceMock, times(1)).deleteQuiz(quizMock);

	}

	@Test
	public void test_submitQuiz_returns_result() throws Exception {
		// Arrange
		String idAsString = "1";
		int id = Integer.parseInt(idAsString);
		String answerContent = "Test";
		Integer points = 5;
		when(payloadMock.get(QuizController.PAYLOAD_KEY)).thenReturn(quizArrayListMock);
		when(quizArrayListMock.iterator()).thenReturn(quizArrayListIteratorMock);
		when(quizArrayListIteratorMock.hasNext()).thenReturn(true, true, false);
		when(quizArrayListIteratorMock.next()).thenReturn(quizMapMock);
		when(quizMapMock.get(QuizController.QUIZ_ID_KEY)).thenReturn(idAsString);
		when(quizMapMock.get(QuizController.USER_ID_KEY)).thenReturn(idAsString);
		when(quizMapMock.get(QuizController.QUESTION_ID_KEY)).thenReturn(idAsString);
		when(quizServiceMock.findQuizById(id)).thenReturn(Optional.of(quizMock));
		when(questionServiceMock.findById(id)).thenReturn(questionMock);
		when(questionMock.getType()).thenReturn(QuestionType.SHORT_ANSWER);
		when(quizMapMock.get(QuizController.ANSWER_CONTENT_KEY)).thenReturn(answerContent);
		when(questionMock.getPoints()).thenReturn(points);
		when(traineeServiceMock.getTraineeByID(id)).thenReturn(traineeMock);

		// Act
		Result actual = quizController.submitQuiz(payloadMock);

		// Assert
		verify(payloadMock, times(2)).get(QuizController.PAYLOAD_KEY);
		verify(quizMapMock, times(2)).get(QuizController.QUIZ_ID_KEY);
		verify(quizServiceMock, times(2)).findQuizById(id);
		verify(quizMapMock, times(1)).get(QuizController.USER_ID_KEY);
		verify(quizMapMock, times(1)).get(QuizController.QUESTION_ID_KEY);
		verify(questionServiceMock, times(1)).findById(id);
		verify(questionMock, times(1)).getType();
		verify(submittedAnswerServiceMock, times(1)).createNewShortAnswer(questionMock, answerContent);
		verify(questionMock, times(2)).getPoints();
		verify(traineeServiceMock, times(1)).getTraineeByID(id);
		verify(traineeServiceMock, times(1)).save(traineeMock);
	}

	@Test
	public void test_submitQuiz_returns_bad_result() throws Exception {
		// Arrange
		String idAsString = "1";
		int id = Integer.parseInt(idAsString);
		String answerContent = "Test";
		Integer points = 5;
		when(payloadMock.get(QuizController.PAYLOAD_KEY)).thenReturn(quizArrayListMock);
		when(quizArrayListMock.iterator()).thenReturn(quizArrayListIteratorMock);
		when(quizArrayListIteratorMock.hasNext()).thenReturn(true, true, false);
		when(quizArrayListIteratorMock.next()).thenReturn(quizMapMock);
		when(quizMapMock.get(QuizController.QUIZ_ID_KEY)).thenReturn(idAsString);
		when(quizMapMock.get(QuizController.USER_ID_KEY)).thenReturn(idAsString);
		when(quizMapMock.get(QuizController.QUESTION_ID_KEY)).thenReturn(idAsString);
		when(quizServiceMock.findQuizById(id)).thenReturn(Optional.of(quizMock).empty());
		when(questionServiceMock.findById(id)).thenReturn(questionMock);
		when(questionMock.getType()).thenReturn(QuestionType.SHORT_ANSWER);
		when(quizMapMock.get(QuizController.ANSWER_CONTENT_KEY)).thenReturn(answerContent);
		when(questionMock.getPoints()).thenReturn(points);
		when(traineeServiceMock.getTraineeByID(id)).thenReturn(traineeMock);

		// Act
		Result actual = quizController.submitQuiz(payloadMock);

		// Assert
		verify(payloadMock, times(1)).get(QuizController.PAYLOAD_KEY);
		verify(quizMapMock, times(1)).get(QuizController.QUIZ_ID_KEY);
		verify(quizServiceMock, times(1)).findQuizById(id);
		verify(quizMapMock, times(0)).get(QuizController.USER_ID_KEY);
		verify(quizMapMock, times(0)).get(QuizController.QUESTION_ID_KEY);
		verify(questionServiceMock, times(0)).findById(id);
		verify(questionMock, times(0)).getType();
		verify(submittedAnswerServiceMock, times(0)).createNewShortAnswer(questionMock, answerContent);
		verify(questionMock, times(0)).getPoints();
		verify(traineeServiceMock, times(0)).getTraineeByID(id);
		verify(traineeServiceMock, times(0)).save(traineeMock);
		assertEquals(-5, actual.getResultId());
	}

	@Test
	public void test_submitQuiz_returns_result_uses_select_answer() throws Exception {
		// Arrange
		String idAsString = "1";
		int id = Integer.parseInt(idAsString);
		String answerContent = "Test";
		Integer points = 5;
		when(payloadMock.get(QuizController.PAYLOAD_KEY)).thenReturn(quizArrayListMock);
		when(quizArrayListMock.iterator()).thenReturn(quizArrayListIteratorMock);
		when(quizArrayListIteratorMock.hasNext()).thenReturn(true, true, false);
		when(quizArrayListIteratorMock.next()).thenReturn(quizMapMock);
		when(quizMapMock.get(QuizController.QUIZ_ID_KEY)).thenReturn(idAsString);
		when(quizMapMock.get(QuizController.USER_ID_KEY)).thenReturn(idAsString);
		when(quizMapMock.get(QuizController.QUESTION_ID_KEY)).thenReturn(idAsString);
		when(quizServiceMock.findQuizById(id)).thenReturn(Optional.of(quizMock));
		when(questionServiceMock.findById(id)).thenReturn(questionMock);
		when(questionMock.getType()).thenReturn(QuestionType.MULTI_SELECT);
		when(quizMapMock.get(QuizController.ANSWER_CONTENT_KEY)).thenReturn(answerContent);
		when(questionMock.getPoints()).thenReturn(points);
		when(traineeServiceMock.getTraineeByID(id)).thenReturn(traineeMock);
		when(quizMapMock.get(QuizController.ANSWER_ID_KEY)).thenReturn(answerArrayListMock);
		when(answerArrayListMock.iterator()).thenReturn(answerArrayListIteratorMock);
		when(answerArrayListIteratorMock.hasNext()).thenReturn(true, false);
		when(answerArrayListIteratorMock.next()).thenReturn(idAsString);
		when(answerServiceMock.finById(id)).thenReturn(Optional.of(answerMock));
		when(answerMock.isCorrect()).thenReturn(true);

		// Act
		Result actual = quizController.submitQuiz(payloadMock);

		// Assert
		verify(payloadMock, times(2)).get(QuizController.PAYLOAD_KEY);
		verify(quizMapMock, times(2)).get(QuizController.QUIZ_ID_KEY);
		verify(quizServiceMock, times(2)).findQuizById(id);
		verify(quizMapMock, times(1)).get(QuizController.USER_ID_KEY);
		verify(quizMapMock, times(1)).get(QuizController.QUESTION_ID_KEY);
		verify(questionServiceMock, times(1)).findById(id);
		verify(questionMock, times(1)).getType();
		verify(submittedAnswerServiceMock, times(0)).createNewShortAnswer(questionMock, answerContent);
		verify(submittedAnswerServiceMock, times(1)).createNewSelectedAnswer(questionMock, answerMock, answerContent);
		verify(questionMock, times(2)).getPoints();
		verify(traineeServiceMock, times(1)).getTraineeByID(id);
		verify(traineeServiceMock, times(2)).save(traineeMock);
	}

	@Test
	public void test_submitQuiz_returns_result_uses_select_answer_is_not_correct() throws Exception {
		// Arrange
		String idAsString = "1";
		int id = Integer.parseInt(idAsString);
		String answerContent = "Test";
		Integer points = 5;
		when(payloadMock.get(QuizController.PAYLOAD_KEY)).thenReturn(quizArrayListMock);
		when(quizArrayListMock.iterator()).thenReturn(quizArrayListIteratorMock);
		when(quizArrayListIteratorMock.hasNext()).thenReturn(true, true, false);
		when(quizArrayListIteratorMock.next()).thenReturn(quizMapMock);
		when(quizMapMock.get(QuizController.QUIZ_ID_KEY)).thenReturn(idAsString);
		when(quizMapMock.get(QuizController.USER_ID_KEY)).thenReturn(idAsString);
		when(quizMapMock.get(QuizController.QUESTION_ID_KEY)).thenReturn(idAsString);
		when(quizServiceMock.findQuizById(id)).thenReturn(Optional.of(quizMock));
		when(questionServiceMock.findById(id)).thenReturn(questionMock);
		when(questionMock.getType()).thenReturn(QuestionType.MULTI_SELECT);
		when(quizMapMock.get(QuizController.ANSWER_CONTENT_KEY)).thenReturn(answerContent);
		when(questionMock.getPoints()).thenReturn(points);
		when(traineeServiceMock.getTraineeByID(id)).thenReturn(traineeMock);
		when(quizMapMock.get(QuizController.ANSWER_ID_KEY)).thenReturn(answerArrayListMock);
		when(answerArrayListMock.iterator()).thenReturn(answerArrayListIteratorMock);
		when(answerArrayListIteratorMock.hasNext()).thenReturn(true, false);
		when(answerArrayListIteratorMock.next()).thenReturn(idAsString);
		when(answerServiceMock.finById(id)).thenReturn(Optional.of(answerMock));
		when(answerMock.isCorrect()).thenReturn(false);

		// Act
		Result actual = quizController.submitQuiz(payloadMock);

		// Assert
		verify(payloadMock, times(2)).get(QuizController.PAYLOAD_KEY);
		verify(quizMapMock, times(2)).get(QuizController.QUIZ_ID_KEY);
		verify(quizServiceMock, times(2)).findQuizById(id);
		verify(quizMapMock, times(1)).get(QuizController.USER_ID_KEY);
		verify(quizMapMock, times(1)).get(QuizController.QUESTION_ID_KEY);
		verify(questionServiceMock, times(1)).findById(id);
		verify(questionMock, times(1)).getType();
		verify(submittedAnswerServiceMock, times(0)).createNewShortAnswer(questionMock, answerContent);
		verify(submittedAnswerServiceMock, times(0)).createNewSelectedAnswer(questionMock, answerMock, answerContent);
		verify(questionMock, times(2)).getPoints();
		verify(traineeServiceMock, times(1)).getTraineeByID(id);
		verify(answerMock, times(1)).getContent();
		verify(traineeServiceMock, times(2)).save(traineeMock);
	}

	@Test
	public void test_submitQuiz_throws_exception() throws Exception {
		// Arrange
		int badQuizId = -1;
		String idAsString = "1";
		int id = Integer.parseInt(idAsString);
		String answerContent = "Test";
		Integer points = 5;
		when(payloadMock.get(QuizController.PAYLOAD_KEY)).thenReturn(quizArrayListMock);
		when(quizArrayListMock.iterator()).thenReturn(quizArrayListIteratorMock);
		when(quizArrayListIteratorMock.hasNext()).thenReturn(true, false);
		when(quizArrayListIteratorMock.next()).thenReturn(quizMapMock);
		when(quizMapMock.get(QuizController.QUIZ_ID_KEY)).thenReturn(badQuizId);
		when(quizMapMock.get(QuizController.USER_ID_KEY)).thenReturn(idAsString);
		when(quizMapMock.get(QuizController.QUESTION_ID_KEY)).thenReturn(idAsString);
		when(quizServiceMock.findQuizById(id)).thenReturn(Optional.of(quizMock));
		when(questionServiceMock.findById(id)).thenReturn(questionMock);
		when(questionMock.getType()).thenReturn(QuestionType.MULTI_SELECT);
		when(quizMapMock.get(QuizController.ANSWER_CONTENT_KEY)).thenReturn(answerContent);
		when(questionMock.getPoints()).thenReturn(points);
		when(traineeServiceMock.getTraineeByID(id)).thenReturn(traineeMock);
		when(quizMapMock.get(QuizController.ANSWER_ID_KEY)).thenReturn(answerArrayListMock);
		when(answerArrayListMock.iterator()).thenReturn(answerArrayListIteratorMock);
		when(answerArrayListIteratorMock.hasNext()).thenReturn(true, false);
		when(answerArrayListIteratorMock.next()).thenReturn(idAsString);
		when(answerServiceMock.finById(id)).thenReturn(Optional.of(answerMock));
		when(answerMock.isCorrect()).thenReturn(false);

		// Act
		assertThrows(Exception.class, () -> {
			Result actual = quizController.submitQuiz(payloadMock);
		});

		// Assert
		verify(payloadMock, times(1)).get(QuizController.PAYLOAD_KEY);
		verify(quizMapMock, times(1)).get(QuizController.QUIZ_ID_KEY);
		verify(quizServiceMock, times(0)).findQuizById(badQuizId);
		verify(quizMapMock, times(0)).get(QuizController.USER_ID_KEY);
		verify(quizMapMock, times(0)).get(QuizController.QUESTION_ID_KEY);
		verify(questionServiceMock, times(0)).findById(id);
		verify(questionMock, times(0)).getType();
		verify(submittedAnswerServiceMock, times(0)).createNewShortAnswer(questionMock, answerContent);
		verify(submittedAnswerServiceMock, times(0)).createNewSelectedAnswer(questionMock, answerMock, answerContent);
		verify(questionMock, times(0)).getPoints();
		verify(traineeServiceMock, times(0)).getTraineeByID(id);
		verify(answerMock, times(0)).getContent();
		verify(traineeServiceMock, times(0)).save(traineeMock);
	}

}
