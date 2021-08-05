package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.repository.AnswerRepository;

class AnswerServiceTest {
	AnswerService answerService;
	
	@Mock
	private AnswerRepository mockAnswerRepo;

	@Mock
	private Question mockQuestion;

	@Mock
	private Answer mockAnswer;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		answerService = new AnswerService(mockAnswerRepo);
	}

	@Test
	void findByQuestion_returnListOfAnswers() {
		List<Answer> answers = new ArrayList<Answer>();
		when(mockAnswerRepo.findByQuestion(mockQuestion)).thenReturn(answers);
		
		List<Answer> result = answerService.findByQuestion(mockQuestion);
		
		assertEquals(answers, result);
	}

	@Test
	void delete_verify() {
		
		answerService.delete(mockAnswer);
		
		verify(mockAnswerRepo, times(1)).delete(mockAnswer);
	}
}
