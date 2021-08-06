package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.repository.ClientRepository;
import com.fdm.qualifier.repository.PlacementRepository;
import com.fdm.qualifier.repository.SkillLevelRepository;

class PlacementServiceTest {

	private PlacementService placementService;
	
	@Mock
	Placement placementMock;
	
	@Mock
	PlacementRepository placementRepoMock;
	
	@Mock
	SkillLevelService skillLevelServiceMock;
	
	@Mock
	SkillService skillServiceMock;
	
	@Mock
	ClientService clientServiceMock;
	
	@Mock
	TraineeService traineeServiceMock;

	@Mock
	ClientRepository clientRepoMock;

	@Mock
	SkillLevelRepository skillLevelRepoMock;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		placementService = new PlacementService(placementRepoMock, skillLevelServiceMock, skillServiceMock, clientServiceMock, traineeServiceMock, clientRepoMock, skillLevelRepoMock);
	}
	
	@Test
	void test_save_calls_repo_save() {
		placementService.save(placementMock);
		verify(placementRepoMock, times(1)).save(placementMock);
	}
	
	@Test
	void test_deleteById_calls_repo_deletById() {
		placementService.deleteById(1);
		verify(placementRepoMock, times(1)).deleteById(1);
	}
	
	@Test
	void test_deleteAll_calls_repo_deleteAll() {
		placementService.deleteAll();
		verify(placementRepoMock).deleteAll();
	}
	
	@Test
	void test_findAll_calls_repo_findAll() {
		placementService.findAll();
		verify(placementRepoMock).findAll();
	}
	
	@Test
	void test_findById_returns_null() {
		when(placementRepoMock.findById(1)).thenReturn(Optional.of(placementMock).empty());
		
		Placement result = placementService.findById(1);

		verify(placementRepoMock).findById(1);		
		assertEquals(null, result);
	}
	
	@Test
	void test_findById_returns_placement() {
		int id = 1;
		Optional<Placement> placement = Optional.of(new Placement());
		when(placementRepoMock.findById(id)).thenReturn(placement);
		Placement result = placementService.findById(id);
		verify(placementRepoMock).findById(1);
		assertEquals(placement.get(), result);
	}
	
	@Test
	void test_findByLocation_calls_repo_findByLocation() {
		String location = "Sydney";
		placementService.findByLocation(location);
		verify(placementRepoMock).findByLocation(location);
	}
	
	@Test
	void test_findByName_calls_repo_findByName() {
		String name = "test";
		placementService.findByName(name);
		verify(placementRepoMock).findByName(name);
	}
	
	@Test
	void test_findBySkillLevelIn_calls_repo_findBySkillLevelIn() {
		SkillLevel skillLevel1 = new SkillLevel();
		SkillLevel skillLevel2 = new SkillLevel(); 
		List<SkillLevel> list = new ArrayList<>();
		list.add(skillLevel1);
		list.add(skillLevel2);
		placementService.findBySkillLevelIn(list);
		verify(placementRepoMock).findBySkillsNeededIn(list);		
	}
	
	@Test
	void test_findBySkillName_calls_skillRepos() {
		String skillName = "Java";
		Skill skill = new Skill();
		when(skillServiceMock.findByName(skillName)).thenReturn(skill);
		placementService.findBySkillName(skillName);
		verify(skillServiceMock).findByName(skillName);
		verify(skillLevelServiceMock).findBySkill(skill);
	}
	
	@Test
	void test_findByClientName_calls_clientServiceMock_and_placementRepoMock() {
		String clientName = "ANZ";
		Client client = new Client(clientName);
		when(clientServiceMock.findByName(clientName)).thenReturn(client);
		placementService.findByClientName(clientName);
		verify(clientServiceMock).findByName(clientName);
		verify(placementRepoMock).findByClient(client);
	}

}
