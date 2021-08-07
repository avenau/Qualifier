package com.fdm.qualifier.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.dto.PlacementRecieverDTO;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.ClientService;
import com.fdm.qualifier.service.PlacementService;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.TraineeService;

class PlacementControllerTest {
	PlacementController placementController;
	
	@Mock
	private PlacementService mockPlacementService;

	@Mock
	private ClientService mockClientService;

	@Mock
	private TraineeService mockTraineeService;

	@Mock
	private SkillLevelService mockSkillLevelService;

	@Mock
	private Placement mockPlacement;

	@Mock
	private Trainee mockTrainee;

	@Mock
	private SkillLevel mockskillLevel;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		placementController = new PlacementController(mockPlacementService, 
				mockClientService, mockTraineeService, mockSkillLevelService);
	}
//	
//	@Test
//	void save_verify() {
//		
//		placementController.save(mockPlacement);
//		
//		verify(mockPlacementService, times(1)).save(mockPlacement);
//	}

	@Test
	void searchPlacements_returnList_onCall() {
		List<Placement> test = new ArrayList<Placement>();
		test.add(mockPlacement);
		List<Placement> expected = new ArrayList<Placement>();
		expected.add(mockPlacement);
		expected.add(mockPlacement);
		expected.add(mockPlacement);
		expected.add(mockPlacement);
		when(mockPlacementService.findByName("test")).thenReturn(test);
		when(mockPlacementService.findByClientName("test")).thenReturn(test);
		when(mockPlacementService.findBySkillName("test")).thenReturn(test);
		when(mockPlacementService.findByLocation("test")).thenReturn(test);
		
		List<Placement> result = placementController.searchPlacements("test");
		
		assertEquals(expected, result);
	}
	
	@Test
	void getAllPlacements_returnList() {
		List<Placement> expected = new ArrayList<Placement>();
		when(mockPlacementService.findAll()).thenReturn(expected);
		
		List<Placement> result = placementController.getAllPlacements();
		
		assertEquals(expected, result);
	}
	
	@Test
	void applyForPlacement_verify_eligibleTrainee() {
		Integer[] id = {1, 2};
		List<SkillLevel> skillList = new ArrayList<SkillLevel>();
		when(mockTraineeService.getTraineeByID(1)).thenReturn(mockTrainee);
		when(mockPlacementService.findById(2)).thenReturn(mockPlacement);
		when(mockPlacement.getSkillsNeeded()).thenReturn(skillList);
		when(mockTraineeService.getAllSkills(1)).thenReturn(skillList);
		when(mockSkillLevelService.isSufficientSkills(skillList, skillList)).thenReturn(true);
		
		placementController.applyForPlacement(id);
		
		verify(mockPlacementService, times(1)).save(mockPlacement);
	}
	
	@Test
	void applyForPlacement_verify_inEligibleTrainee() {
		Integer[] id = {1, 2};
		List<SkillLevel> skillList = new ArrayList<SkillLevel>();
		when(mockTraineeService.getTraineeByID(1)).thenReturn(mockTrainee);
		when(mockPlacementService.findById(2)).thenReturn(mockPlacement);
		when(mockPlacement.getSkillsNeeded()).thenReturn(skillList);
		when(mockTraineeService.getAllSkills(1)).thenReturn(skillList);
		when(mockSkillLevelService.isSufficientSkills(skillList, skillList)).thenReturn(false);
		
		placementController.applyForPlacement(id);
		
		verify(mockPlacementService, times(0)).save(mockPlacement);
	}
	
	@Test
	void test_save() {
		PlacementRecieverDTO placementDTO = new PlacementRecieverDTO();
		placementController.save(placementDTO);
		verify(mockPlacementService).saveDTO(placementDTO);
	}
	
	@Test
	void test_approveREquest() {
		PlacementRecieverDTO placementDTO = new PlacementRecieverDTO();
		Integer[] test = {1, 2, 3};
		when(mockTraineeService.getTraineeByID(1)).thenReturn(mockTrainee);
		when(mockPlacementService.findById(2)).thenReturn(mockPlacement);
		placementController.approveREquest(test);
		
	}
}
