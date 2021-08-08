package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Question.QuestionType;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.repository.QuestionRepository;

class QuestionServiceTest {
	QuestionService questionService;
	
	@Mock
	private QuestionRepository mockQuestionRepo;

	@Mock
	private Question mockQuestion;

	@Mock
	private Quiz mockQuiz;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		questionService = new QuestionService(mockQuestionRepo);
	}

	@Test
	public void saveQuestion_returnsSavedQuestion() {
		when(mockQuestionRepo.save(mockQuestion)).thenReturn(mockQuestion);
		
		Question result = questionService.save(mockQuestion);
		
		assertEquals(mockQuestion, result);
	}
	
	@Test
	public void findAllQuestion_returns_list() {
		List<Question> expected = new ArrayList<>();
		when(mockQuestionRepo.findQuestionByQuiz(mockQuiz)).thenReturn(expected);
		
		List<Question> result = questionService.findAllQuestionsOfQuizID(mockQuiz);

		assertEquals(expected, result);
	}
	
	@Test
	public void delete_verifyDeletion() {
		
		questionService.delete(mockQuestion);
		
		verify(mockQuestionRepo, times(1)).delete(mockQuestion);
	}
	
	@Test
	public void getfindQuestionById_getQuestion_OnNotNullResult() {
		Optional<Question> mockOptQuestion = Optional.of(mockQuestion);
		when(mockQuestionRepo.findById(1)).thenReturn(mockOptQuestion);
		
		Question result = questionService.findById(1);
		
		assertEquals(mockQuestion, result);
	}
	
	@Test
	public void test_findById_returns_null_when_not_found() {
		//Arrange
		int id = 1;
		when(mockQuestionRepo.findById(id)).thenReturn(Optional.of(mockQuestion).empty());
		
		//Act
		Question actual = questionService.findById(id);
		
		//Assert
		verify(mockQuestionRepo, times(1)).findById(id);
		assertEquals(null, actual);
	}
	
	@Test
	public void test_createNewQuestion_calls_save_returns_question() {
		//Arrange
		String content = "content";
		QuestionType questionType = QuestionType.MULTI_SELECT;
		int marks = 5;
		when(mockQuiz.getQuestionCount()).thenReturn(1);
		
		//Act
		Question actual = questionService.createNewQuestion(mockQuiz, content, questionType, marks);
		
		//Assert
		verify(mockQuestionRepo, times(1)).save(isA(Question.class));
		verify(mockQuiz, times(1)).getQuestionCount();
		verify(mockQuiz, times(1)).setQuestionCount(2);
		assertEquals(mockQuiz, actual.getQuiz());
		assertEquals(content, actual.getContent());
		assertEquals(questionType, actual.getType());
		assertEquals(marks, actual.getPoints());
	}
}
