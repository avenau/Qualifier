package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.SubmittedAnswer;
import com.fdm.qualifier.repository.ResultRepository;

class ResultServiceTest {
	ResultService resultService;

	@Mock
	private ResultRepository mockResultRepo;

	@Mock
	private Result mockResult;

	@Mock
	private Quiz mockQuiz;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		resultService = new ResultService(mockResultRepo);
	}

	@Test
	void delete_verify() {
		
		resultService.delete(mockResult);
		
		verify(mockResultRepo, times(1)).delete(mockResult);
	}

	@Test
	void findByQuiz_returnListOfAnswers() {
		List<Result> answers = new ArrayList<Result>();
		when(mockResultRepo.findByQuiz(mockQuiz)).thenReturn(answers);
		
		List<Result> result = resultService.findByQuiz(mockQuiz);
		
		assertEquals(answers, result);
	}
	
	@Test
	void test_createNewResult() {
		 List<SubmittedAnswer> list = new ArrayList<SubmittedAnswer>();
		
		resultService.createNewResult(5, false, false, null, mockQuiz, list);
		Result result = new Result(5, false, false, null, mockQuiz, list);
		
		
	}
}
