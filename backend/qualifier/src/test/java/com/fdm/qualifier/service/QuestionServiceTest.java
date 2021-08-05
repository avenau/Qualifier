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
}
