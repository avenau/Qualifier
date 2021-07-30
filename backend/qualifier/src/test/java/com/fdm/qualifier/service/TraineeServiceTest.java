package com.fdm.qualifier.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.repository.TraineeRepository;

public class TraineeServiceTest {
	private TraineeService traineeService;
	
	@Mock
	TraineeRepository traineeRepoMock;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		traineeService = new TraineeService(traineeRepoMock);
	}
}
