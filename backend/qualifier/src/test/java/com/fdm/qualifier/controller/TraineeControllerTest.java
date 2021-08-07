package com.fdm.qualifier.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
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

	@Mock
	Trainee traineeMock;

	@Mock
	Skill skillMock;

	@Mock
	SkillLevel skillLevelMock;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		traineeController = new TraineeController(traineeServiceMock, skillLevelServiceMock, skillServiceMock);
	}
//	
//	@Test
//	void test_changeOinnedSkills_returns_trainee() {
//		Trainee trainee = new Trainee();
//		Trainee result = traineeController.changePinnedSkills(trainee);
//		assertEquals(trainee, result);
//	}
	
	@Test
	void test_getAllTrainees() {
		traineeController.getAllTrainees();
		verify(traineeServiceMock).getAllTrainees();
	}
	
	@Test
	void test_addUnverifiedSkill() {
		//Assign
		Integer[] ids = {5, 9};
		List<SkillLevel> list = new ArrayList<SkillLevel>();
		list.add(skillLevelMock);
		
		//Act
		when(traineeServiceMock.getTraineeByID(ids[0])).thenReturn(traineeMock);
		when(skillServiceMock.findById(ids[1])).thenReturn(skillMock);
		when(skillLevelServiceMock.findBySkill(skillMock)).thenReturn(list);
		when(skillLevelMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.UNVERIFIED);
		when(traineeServiceMock.addSkillToTrainee(skillLevelMock, ids[0])).thenReturn(true);
		
		//Assert
		SkillLevel result = traineeController.addUnverifiedSkill(ids);
		assertEquals(result, skillLevelMock);
	}

}
