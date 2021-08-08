package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Trainer;
import com.fdm.qualifier.repository.TrainerRepository;

public class TrainerServiceTest {

	private TrainerService trainerService;
	
	@Mock
	private TrainerRepository trainerRepoMock;

	@Mock
	private Trainer mockTrainer;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		trainerService = new TrainerService(trainerRepoMock);
	}
	
	@Test
	public void test_save_calls_repo_returns_trainer() {
		//Arrange
		when(trainerRepoMock.save(mockTrainer)).thenReturn(mockTrainer);
		
		//Act
		Trainer actual = trainerService.saveTrainer(mockTrainer);
		
		//Assert
		verify(trainerRepoMock, times(1)).save(mockTrainer);
		assertEquals(mockTrainer, actual);
	}
}
