package com.fdm.qualifier.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.SkillService;
import com.fdm.qualifier.service.TraineeService;

class TraineeControllerTest {
	
	TraineeController traineeController;
	
	@Mock
	TraineeService traineeServiceMock;
	
	@Mock
	SkillService skillServiceMock;
	
	@Mock
	SkillLevelService skillLevelServiceMock;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		traineeController = new TraineeController(traineeServiceMock, skillLevelServiceMock, skillServiceMock);
	}
	
	@Test
	void test_changeOinnedSkills_returns_trainee() {
		Trainee trainee = new Trainee();
		Trainee result = traineeController.changePinnedSkills(trainee);
		assertEquals(trainee, result);
	}
	
	@Test
	void test_getAllTrainees() {
		traineeController.getAllTrainees();
		verify(traineeServiceMock).getAllTrainees();
	}

}
