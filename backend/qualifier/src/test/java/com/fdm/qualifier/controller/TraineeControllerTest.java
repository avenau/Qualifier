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

import com.fdm.qualifier.dto.TraineeSkillLevelDTO;
import com.fdm.qualifier.model.Result;
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

	@Mock
	private TraineeSkillLevelDTO traineeSkillLevelDTOMock;

	@Mock
	private Trainee traineeMockname;

	@Mock
	private Trainee traineeMockskill;

	@Mock
	private Result resultMock;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		traineeController = new TraineeController(traineeServiceMock, skillLevelServiceMock, skillServiceMock);
	}
	
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
		verify(traineeServiceMock, times(1)).save(traineeMock);
		assertEquals(result, skillLevelMock);
	}
	
	@Test
	void test_removeTraineeSkill() {
		//Assign
		Integer ids[] = {5,10};
		
		//Act
		when(traineeServiceMock.getTraineeByID(ids[0])).thenReturn(traineeMock);
		when(skillLevelServiceMock.getById(ids[1])).thenReturn(skillLevelMock);
		when(skillLevelMock.getSkill()).thenReturn(skillMock);
		traineeController.removeTraineeSkill(ids);
		
		//Assert
		verify(traineeServiceMock, times(1)).removeSkillFromTrainee(skillMock, ids[0]);
		verify(traineeServiceMock, times(1)).save(traineeMock);	
	}
	
	@Test
	void test_getPinnedSkills() {
		//Assign
		int[] ids = {5};	
		List<TraineeSkillLevelDTO> list = new ArrayList<TraineeSkillLevelDTO> ();
		when(traineeServiceMock.getPinnedSkillsAsDTO(ids[0])).thenReturn(list);
		list.add(traineeSkillLevelDTOMock);
		
		//Act
		List<TraineeSkillLevelDTO> result = traineeController.getPinnedSkills(ids);
		
		//Assert
		assertEquals(result, list);	
	}
	
	@Test
	void test_getSkills() {
		//Assign
		int[] id = {5};
		List<TraineeSkillLevelDTO> list = new ArrayList<TraineeSkillLevelDTO> ();
		list.add(traineeSkillLevelDTOMock);
		
		//Act
		when(traineeServiceMock.getSkillsAsDTO(id[0])).thenReturn(list);
		List<TraineeSkillLevelDTO> result = traineeController.getSkills(id);
		//Assert
		assertEquals(result, list);
	}
	
	@Test
	void test_pinSkill() {
		//Assign
		Integer ids[] = {5, 3};
		String expectedAnswer = "HELLO";
		
		//Act
		when(traineeServiceMock.pinSkill(ids[0], ids[1])).thenReturn(expectedAnswer);
		String result = traineeController.pinSkill(ids);
		
		//Assert
		assertEquals(result, expectedAnswer);
	}
	
	@Test
	void test_unPinSkill() {
		//Assign
		Integer ids[] = {5, 3};
		String expectedAnswer = "HELLO";
		
		//Act
		when(traineeServiceMock.unpinSkill(ids[0], ids[1])).thenReturn(expectedAnswer);
		String result = traineeController.unpinSkill(ids);
		
		//Assert
		assertEquals(result, expectedAnswer);
	}
	
	@Test
	void test_searchTrainees() {
		//Assign
		String searchTerm = "dolla king";
		List<Trainee> traineeNameList = new ArrayList<Trainee>();
		List<Trainee> skillNameList = new ArrayList<Trainee>();
		List<Trainee> firstlastList = new ArrayList<Trainee>();
		List<Trainee> expected = new ArrayList<Trainee> ();
		
		traineeNameList.add(traineeMockname);
		skillNameList.add(traineeMockskill);
		firstlastList.add(traineeMock);
		expected.addAll(traineeNameList);
		expected.addAll(skillNameList);
		expected.addAll(firstlastList);
		
		//Act
		when(traineeServiceMock.findTraineeByName(searchTerm)).thenReturn(traineeNameList);
		when(traineeServiceMock.findBySkillName(searchTerm)).thenReturn(skillNameList);
		when(traineeServiceMock.findByFirstAndLastName("dolla", "king")).thenReturn(firstlastList);
		List<Trainee> result = traineeController.searchTrainees(searchTerm);
		
		//Assert
		assertEquals(result, expected);
	}
	
	@Test
	void test_getTraineeResults() {
		//Assert
		int id[] = {5};
		List<Result> expected = new ArrayList<Result>();
		expected.add(resultMock);
		
		//Act
		when(traineeServiceMock.getAllResults(id[0])).thenReturn(expected);
		List<Result> result = traineeController.getTraineeResults(id);
		//Assign
		assertEquals(expected, result);
	}

}
