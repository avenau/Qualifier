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
import com.fdm.qualifier.model.SubmittedAnswer;
import com.fdm.qualifier.repository.SubmittedAnswerRepository;

class SubmittedAnswerServiceTest {
	private SubmittedAnswerService submitAnswerServ;
	
	@Mock
	private SubmittedAnswerRepository mockSubmitAnswerRepo;

	@Mock
	private SubmittedAnswer mockSubmittedAnswer;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		submitAnswerServ = new SubmittedAnswerService(mockSubmitAnswerRepo);
	}

	@Test
	void save_returnMockSubmittedAnswer() {
		when(mockSubmitAnswerRepo.save(mockSubmittedAnswer)).thenReturn(mockSubmittedAnswer);
		
		SubmittedAnswer result = submitAnswerServ.save(mockSubmittedAnswer);
		
		assertEquals(mockSubmittedAnswer, result);
	}

	@Test
	void delete_verify() {
		
		submitAnswerServ.delete(mockSubmittedAnswer);
		
		verify(mockSubmitAnswerRepo, times(1)).delete(mockSubmittedAnswer);
	}
	
	@Test
	void findById_returnMockSubmittedAnswer() {
		Optional<SubmittedAnswer> mockOptionalSubmitAnswer = Optional.of(mockSubmittedAnswer);
		when(mockSubmitAnswerRepo.findById(1)).thenReturn(mockOptionalSubmitAnswer);
		
		Optional<SubmittedAnswer> result = submitAnswerServ.findById(1);
		
		assertEquals(mockSubmittedAnswer, result.get());
	}
	
	@Test
	void findByQuestion_returnList() {
		Question test = new Question();
		List<SubmittedAnswer> testList = new ArrayList<SubmittedAnswer>();
		when(mockSubmitAnswerRepo.findSubmittedAnswerByQuestion(test)).thenReturn(testList);
		
		List<SubmittedAnswer> result = submitAnswerServ.findByQuestion(test);
		
		assertEquals(testList, result);
	}
}
