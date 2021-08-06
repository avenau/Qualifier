package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.repository.SkillLevelRepository;
import com.fdm.qualifier.repository.TraineeRepository;

public class TraineeServiceTest {
	private TraineeService traineeService;
	
	@Mock
	TraineeRepository traineeRepoMock;

	@Mock
	SkillLevelRepository skillLevelRepoMock;
	
	@Mock
	SkillLevelService skillLevelServiceMock;
	
	@Mock
	SkillService skillServiceMock;
	
	@Mock
	Trainee traineeMock;

	@Mock
	List<SkillLevel> skillsMock;

	@Mock
	List<SkillLevel> pinnedSkillsMock;

	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		traineeService = new TraineeService(traineeRepoMock, skillLevelRepoMock, skillLevelServiceMock, skillServiceMock);
	}
	
	@Test
	public void test_getAllTrainees_returns_list() {
		//Arrange
		List<Trainee> expected = new ArrayList<>();
		when(traineeRepoMock.findAll()).thenReturn(expected);
		
		//Act
		List<Trainee> actual = traineeService.getAllTrainees();

		//Assert
		verify(traineeRepoMock, times(1)).findAll();
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_save_calls_repo_save() {
		//Arrange
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		
		//Act
		Trainee actual = traineeService.save(traineeMock);
		
		//Assert
		verify(traineeRepoMock, times(1)).save(traineeMock);
		assertEquals(traineeMock, actual);
	}
	
	@Test
	public void test_getPinnedSkills_returns_list() {
		//Arrange
		int id = 0;
		List<SkillLevel> expected = new ArrayList<>();
		when(traineeMock.getUserId()).thenReturn(id);
		when(traineeRepoMock.getPinnedSkillsByUid(id)).thenReturn(expected);
		
		//Act
		List<SkillLevel> actual = traineeService.getPinnedSkills(traineeMock.getUserId());
		
		//Assert
		verify(traineeRepoMock, times(1)).getPinnedSkillsByUid(id);
		assertEquals(expected, actual);
	}
	

	@Test
	public void test_getSkills_returns_list() {
		//Arrange
		int id = 0;
		List<SkillLevel> expected = new ArrayList<>();
		when(traineeMock.getUserId()).thenReturn(id);
		when(traineeRepoMock.getSkillsByUid(id)).thenReturn(expected);
		
		//Act
		List<SkillLevel> actual = traineeService.getSkills(traineeMock.getUserId());
		
		//Assert
		verify(traineeRepoMock, times(1)).getSkillsByUid(id);
		assertEquals(expected, actual);
	}
	

	@Test
	public void test_getAllSkills_returns_list() {
		//Arrange
		int id = 0;
		List<SkillLevel> expected = new ArrayList<>();
		when(traineeMock.getUserId()).thenReturn(id);
		when(traineeRepoMock.getSkillsByUid(id)).thenReturn(skillsMock);
		when(traineeRepoMock.getPinnedSkillsByUid(id)).thenReturn(pinnedSkillsMock);
		when(skillsMock.addAll(pinnedSkillsMock)).thenReturn(true);
		
		//Act
		List<SkillLevel> actual = traineeService.getAllSkills(traineeMock.getUserId());
		
		//Assert
		verify(traineeRepoMock, times(1)).getPinnedSkillsByUid(id);
		verify(traineeRepoMock, times(1)).getSkillsByUid(id);
		
		assertEquals(skillsMock, actual);
	}
	
	@Test
	public void test_pinSkill_pins_skill_when_valid_returns_empty_string() {
		//Arrange
		SkillLevel skill = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, new Skill("java"), null);
		when(traineeRepoMock.findById(traineeMock.getUserId())).thenReturn(Optional.of(traineeMock));
		when(skillLevelRepoMock.findById(skill.getSkillLevelId())).thenReturn(Optional.of(skill));
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.size()).thenReturn(10);
		when(pinnedSkillsMock.size()).thenReturn(0);
		when(skillsMock.contains(skill)).thenReturn(true);
		
		//Act
		String actual = traineeService.pinSkill(traineeMock.getUserId(), skill.getSkillLevelId());
		
		//Assert
		verify(traineeRepoMock, times(1)).findById(traineeMock.getUserId());
		verify(skillLevelRepoMock, times(1)).findById(skill.getSkillLevelId());
		verify(traineeMock, times(1)).getSkills();
		verify(traineeMock, times(1)).getPinnedSkills();
		verify(skillsMock, times(1)).size();
		verify(pinnedSkillsMock, times(1)).size();
		verify(skillsMock, times(1)).contains(skill);
		verify(skillsMock, times(1)).remove(skill);
		verify(pinnedSkillsMock, times(1)).add(skill);
		verify(traineeMock, times(1)).setSkills(skillsMock);
		verify(traineeMock, times(1)).setPinnedSkills(pinnedSkillsMock);
		verify(traineeRepoMock, times(1)).save(traineeMock);
		assertEquals("", actual);
	}
	
	@Test
	public void test_pinSkill_does_not_pin_skill_when_pinnedSkills_full_returns_message_string() {
		//Arrange
		SkillLevel skill = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, new Skill("java"), null);
		when(traineeRepoMock.findById(traineeMock.getUserId())).thenReturn(Optional.of(traineeMock));
		when(skillLevelRepoMock.findById(skill.getSkillLevelId())).thenReturn(Optional.of(skill));
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.size()).thenReturn(10);
		when(pinnedSkillsMock.size()).thenReturn(TraineeService.MAX_PINNED_SKILLS);
		when(skillsMock.contains(skill)).thenReturn(true);
		
		//Act
		String actual = traineeService.pinSkill(traineeMock.getUserId(), skill.getSkillLevelId());
		
		//Assert
		verify(traineeRepoMock, times(1)).findById(traineeMock.getUserId());
		verify(skillLevelRepoMock, times(1)).findById(skill.getSkillLevelId());
		verify(traineeMock, times(1)).getSkills();
		verify(traineeMock, times(1)).getPinnedSkills();
		verify(skillsMock, times(1)).size();
		verify(pinnedSkillsMock, times(1)).size();
		verify(skillsMock, times(0)).contains(skill);
		verify(skillsMock, times(0)).remove(skill);
		verify(pinnedSkillsMock, times(0)).add(skill);
		verify(traineeMock, times(0)).setSkills(skillsMock);
		verify(traineeMock, times(0)).setPinnedSkills(pinnedSkillsMock);
		verify(traineeRepoMock, times(0)).save(traineeMock);
		assertEquals(TraineeService.TOO_MANY_PINNED_SKILLS_MESSAGE, actual);
	}

	@Test
	public void test_pinSkill_does_not_pin_skill_when_skills_empty_returns_message_string() {
		//Arrange
		SkillLevel skill = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, new Skill("java"), null);
		when(traineeRepoMock.findById(traineeMock.getUserId())).thenReturn(Optional.of(traineeMock));
		when(skillLevelRepoMock.findById(skill.getSkillLevelId())).thenReturn(Optional.of(skill));
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.size()).thenReturn(0);
		when(pinnedSkillsMock.size()).thenReturn(0);
		when(skillsMock.contains(skill)).thenReturn(true);
		
		//Act
		String actual = traineeService.pinSkill(traineeMock.getUserId(), skill.getSkillLevelId());
		
		//Assert
		verify(traineeRepoMock, times(1)).findById(traineeMock.getUserId());
		verify(skillLevelRepoMock, times(1)).findById(skill.getSkillLevelId());
		verify(traineeMock, times(1)).getSkills();
		verify(traineeMock, times(1)).getPinnedSkills();
		verify(skillsMock, times(1)).size();
		verify(pinnedSkillsMock, times(0)).size();
		verify(skillsMock, times(0)).contains(skill);
		verify(skillsMock, times(0)).remove(skill);
		verify(pinnedSkillsMock, times(0)).add(skill);
		verify(traineeMock, times(0)).setSkills(skillsMock);
		verify(traineeMock, times(0)).setPinnedSkills(pinnedSkillsMock);
		verify(traineeRepoMock, times(0)).save(traineeMock);
		assertEquals(TraineeService.NO_SKILLS_MESSAGE, actual);
	}
	
	@Test
	public void test_pinSkill_does_not_pin_skill_when_skill_to_pin_not_in_skills_returns_message_string() {
		//Arrange
		SkillLevel skill = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, new Skill("java"), null);
		when(traineeRepoMock.findById(traineeMock.getUserId())).thenReturn(Optional.of(traineeMock));
		when(skillLevelRepoMock.findById(skill.getSkillLevelId())).thenReturn(Optional.of(skill));
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.size()).thenReturn(10);
		when(pinnedSkillsMock.size()).thenReturn(0);
		when(skillsMock.contains(skill)).thenReturn(false);
		
		//Act
		String actual = traineeService.pinSkill(traineeMock.getUserId(), skill.getSkillLevelId());
		
		//Assert
		verify(traineeRepoMock, times(1)).findById(traineeMock.getUserId());
		verify(skillLevelRepoMock, times(1)).findById(skill.getSkillLevelId());
		verify(traineeMock, times(1)).getSkills();
		verify(traineeMock, times(1)).getPinnedSkills();
		verify(skillsMock, times(1)).size();
		verify(pinnedSkillsMock, times(1)).size();
		verify(skillsMock, times(1)).contains(skill);
		verify(skillsMock, times(0)).remove(skill);
		verify(pinnedSkillsMock, times(0)).add(skill);
		verify(traineeMock, times(0)).setSkills(skillsMock);
		verify(traineeMock, times(0)).setPinnedSkills(pinnedSkillsMock);
		verify(traineeRepoMock, times(0)).save(traineeMock);
		assertEquals(traineeService.createMessageWithSkillNameAndLevel(TraineeService.NO_SKILL_IN_SKILLS_PATTERN, skill) , actual);
	}

	@Test
	public void test_pinSkill_returns_message_when_trainee_not_found() {
		//Arrange
		SkillLevel skill = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, new Skill("java"), null);
		when(traineeRepoMock.findById(traineeMock.getUserId())).thenReturn(Optional.of(traineeMock).empty());
		when(skillLevelRepoMock.findById(skill.getSkillLevelId())).thenReturn(Optional.of(skill));
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.size()).thenReturn(10);
		when(pinnedSkillsMock.size()).thenReturn(0);
		when(skillsMock.contains(skill)).thenReturn(false);
		
		//Act
		String actual = traineeService.pinSkill(traineeMock.getUserId(), skill.getSkillLevelId());
		
		//Assert
		verify(traineeRepoMock, times(1)).findById(traineeMock.getUserId());
		verify(skillLevelRepoMock, times(1)).findById(skill.getSkillLevelId());
		verify(traineeMock, times(0)).getSkills();
		verify(traineeMock, times(0)).getPinnedSkills();
		verify(skillsMock, times(0)).size();
		verify(pinnedSkillsMock, times(0)).size();
		verify(skillsMock, times(0)).contains(skill);
		verify(skillsMock, times(0)).remove(skill);
		verify(pinnedSkillsMock, times(0)).add(skill);
		verify(traineeMock, times(0)).setSkills(skillsMock);
		verify(traineeMock, times(0)).setPinnedSkills(pinnedSkillsMock);
		verify(traineeRepoMock, times(0)).save(traineeMock);
		assertEquals(TraineeService.TRAINEE_DOESN_T_EXIST_IN_DATABASE_MESSAGE , actual);		
	}
	
	@Test
	public void test_pinSkill_returns_message_when_skill_not_found() {
		//Arrange
		SkillLevel skill = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, new Skill("java"), null);
		when(traineeRepoMock.findById(traineeMock.getUserId())).thenReturn(Optional.of(traineeMock));
		when(skillLevelRepoMock.findById(skill.getSkillLevelId())).thenReturn(Optional.of(skill).empty());
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.size()).thenReturn(10);
		when(pinnedSkillsMock.size()).thenReturn(0);
		when(skillsMock.contains(skill)).thenReturn(false);
		
		//Act
		String actual = traineeService.pinSkill(traineeMock.getUserId(), skill.getSkillLevelId());
		
		//Assert
		verify(traineeRepoMock, times(1)).findById(traineeMock.getUserId());
		verify(skillLevelRepoMock, times(1)).findById(skill.getSkillLevelId());
		verify(traineeMock, times(0)).getSkills();
		verify(traineeMock, times(0)).getPinnedSkills();
		verify(skillsMock, times(0)).size();
		verify(pinnedSkillsMock, times(0)).size();
		verify(skillsMock, times(0)).contains(skill);
		verify(skillsMock, times(0)).remove(skill);
		verify(pinnedSkillsMock, times(0)).add(skill);
		verify(traineeMock, times(0)).setSkills(skillsMock);
		verify(traineeMock, times(0)).setPinnedSkills(pinnedSkillsMock);
		verify(traineeRepoMock, times(0)).save(traineeMock);
		assertEquals(TraineeService.SKILL_DOESN_T_EXIST_IN_DATABASE_MESSAGE, actual);		
	}
	
	@Test
	public void test_unpinSkill_unpins_skill_when_valid_returns_empty_string() {
		//Arrange
		SkillLevel skill = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, new Skill("java"), null);
		when(traineeRepoMock.findById(traineeMock.getUserId())).thenReturn(Optional.of(traineeMock));
		when(skillLevelRepoMock.findById(skill.getSkillLevelId())).thenReturn(Optional.of(skill));
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(pinnedSkillsMock.size()).thenReturn(1);
		when(pinnedSkillsMock.contains(skill)).thenReturn(true);
		
		//Act
		String actual = traineeService.unpinSkill(traineeMock.getUserId(), skill.getSkillLevelId());
		
		//Assert
		verify(traineeRepoMock, times(1)).findById(traineeMock.getUserId());
		verify(skillLevelRepoMock, times(1)).findById(skill.getSkillLevelId());
		verify(traineeMock, times(1)).getSkills();
		verify(traineeMock, times(1)).getPinnedSkills();
		verify(pinnedSkillsMock, times(1)).size();
		verify(pinnedSkillsMock, times(1)).contains(skill);
		verify(pinnedSkillsMock, times(1)).remove(skill);
		verify(skillsMock, times(1)).add(skill);
		verify(traineeMock, times(1)).setSkills(skillsMock);
		verify(traineeMock, times(1)).setPinnedSkills(pinnedSkillsMock);
		verify(traineeRepoMock, times(1)).save(traineeMock);
		assertEquals("", actual);
	}
	
	@Test
	public void test_unpinSkill_does_not_unpin_skill_when_pinnedSkills_empty_returns_message_string() {
		//Arrange
		SkillLevel skill = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, new Skill("java"), null);
		when(traineeRepoMock.findById(traineeMock.getUserId())).thenReturn(Optional.of(traineeMock));
		when(skillLevelRepoMock.findById(skill.getSkillLevelId())).thenReturn(Optional.of(skill));
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(pinnedSkillsMock.size()).thenReturn(0);
		when(pinnedSkillsMock.contains(skill)).thenReturn(true);
		
		//Act
		String actual = traineeService.unpinSkill(traineeMock.getUserId(), skill.getSkillLevelId());
		
		//Assert
		verify(traineeRepoMock, times(1)).findById(traineeMock.getUserId());
		verify(skillLevelRepoMock, times(1)).findById(skill.getSkillLevelId());
		verify(traineeMock, times(1)).getSkills();
		verify(traineeMock, times(1)).getPinnedSkills();
		verify(pinnedSkillsMock, times(1)).size();
		verify(pinnedSkillsMock, times(0)).contains(skill);
		verify(pinnedSkillsMock, times(0)).remove(skill);
		verify(skillsMock, times(0)).add(skill);
		verify(traineeMock, times(0)).setSkills(skillsMock);
		verify(traineeMock, times(0)).setPinnedSkills(pinnedSkillsMock);
		verify(traineeRepoMock, times(0)).save(traineeMock);
		assertEquals(TraineeService.NO_PINNED_SKILLS_MESSAGE, actual);
	}
	
	@Test
	public void test_unpinSkill_does_not_unpin_skill_when_skill_to_unpin_not_in_pinnedSkills_returns_message_string() {
		//Arrange
		SkillLevel skill = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, new Skill("java"), null);
		when(traineeRepoMock.findById(traineeMock.getUserId())).thenReturn(Optional.of(traineeMock));
		when(skillLevelRepoMock.findById(skill.getSkillLevelId())).thenReturn(Optional.of(skill));
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(pinnedSkillsMock.size()).thenReturn(1);
		when(pinnedSkillsMock.contains(skill)).thenReturn(false);
		
		//Act
		String actual = traineeService.unpinSkill(traineeMock.getUserId(), skill.getSkillLevelId());
		
		//Assert
		verify(traineeRepoMock, times(1)).findById(traineeMock.getUserId());
		verify(skillLevelRepoMock, times(1)).findById(skill.getSkillLevelId());
		verify(traineeMock, times(1)).getSkills();
		verify(traineeMock, times(1)).getPinnedSkills();
		verify(pinnedSkillsMock, times(1)).size();
		verify(pinnedSkillsMock, times(1)).contains(skill);
		verify(pinnedSkillsMock, times(0)).remove(skill);
		verify(skillsMock, times(0)).add(skill);
		verify(traineeMock, times(0)).setSkills(skillsMock);
		verify(traineeMock, times(0)).setPinnedSkills(pinnedSkillsMock);
		verify(traineeRepoMock, times(0)).save(traineeMock);
		assertEquals(traineeService.createMessageWithSkillNameAndLevel(TraineeService.NO_SKILL_IN_PINNED_SKILLS_PATTERN, skill) , actual);
	}

	@Test
	public void test_unpinSkill_returns_message_when_trainee_not_found() {
		//Arrange
		SkillLevel skill = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, new Skill("java"), null);
		when(traineeRepoMock.findById(traineeMock.getUserId())).thenReturn(Optional.of(traineeMock).empty());
		when(skillLevelRepoMock.findById(skill.getSkillLevelId())).thenReturn(Optional.of(skill));
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.size()).thenReturn(10);
		when(pinnedSkillsMock.size()).thenReturn(0);
		when(skillsMock.contains(skill)).thenReturn(false);
		
		//Act
		String actual = traineeService.unpinSkill(traineeMock.getUserId(), skill.getSkillLevelId());
		
		//Assert
		verify(traineeRepoMock, times(1)).findById(traineeMock.getUserId());
		verify(skillLevelRepoMock, times(1)).findById(skill.getSkillLevelId());
		verify(traineeMock, times(0)).getSkills();
		verify(traineeMock, times(0)).getPinnedSkills();
		verify(skillsMock, times(0)).size();
		verify(pinnedSkillsMock, times(0)).size();
		verify(skillsMock, times(0)).contains(skill);
		verify(skillsMock, times(0)).remove(skill);
		verify(pinnedSkillsMock, times(0)).add(skill);
		verify(traineeMock, times(0)).setSkills(skillsMock);
		verify(traineeMock, times(0)).setPinnedSkills(pinnedSkillsMock);
		verify(traineeRepoMock, times(0)).save(traineeMock);
		assertEquals(TraineeService.TRAINEE_DOESN_T_EXIST_IN_DATABASE_MESSAGE , actual);		
	}
	
	@Test
	public void test_unpinSkill_returns_message_when_skill_not_found() {
		//Arrange
		SkillLevel skill = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, new Skill("java"), null);
		when(traineeRepoMock.findById(traineeMock.getUserId())).thenReturn(Optional.of(traineeMock));
		when(skillLevelRepoMock.findById(skill.getSkillLevelId())).thenReturn(Optional.of(skill).empty());
		when(traineeRepoMock.save(traineeMock)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.size()).thenReturn(10);
		when(pinnedSkillsMock.size()).thenReturn(0);
		when(skillsMock.contains(skill)).thenReturn(false);
		
		//Act
		String actual = traineeService.unpinSkill(traineeMock.getUserId(), skill.getSkillLevelId());
		
		//Assert
		verify(traineeRepoMock, times(1)).findById(traineeMock.getUserId());
		verify(skillLevelRepoMock, times(1)).findById(skill.getSkillLevelId());
		verify(traineeMock, times(0)).getSkills();
		verify(traineeMock, times(0)).getPinnedSkills();
		verify(skillsMock, times(0)).size();
		verify(pinnedSkillsMock, times(0)).size();
		verify(skillsMock, times(0)).contains(skill);
		verify(skillsMock, times(0)).remove(skill);
		verify(pinnedSkillsMock, times(0)).add(skill);
		verify(traineeMock, times(0)).setSkills(skillsMock);
		verify(traineeMock, times(0)).setPinnedSkills(pinnedSkillsMock);
		verify(traineeRepoMock, times(0)).save(traineeMock);
		assertEquals(TraineeService.SKILL_DOESN_T_EXIST_IN_DATABASE_MESSAGE, actual);		
	}

	@Test
	public void findTraineeByName_returnListOfTrainees() {
		//Arrange
		List<Trainee> testList = new ArrayList<Trainee>();
		when(traineeRepoMock.findByFirstNameOrLastName("test")).thenReturn(testList);
		
		//Act
		List<Trainee> result = traineeService.findTraineeByName("test");
		
		//Assert
		assertEquals(testList, result);
	}
	
	@Test
	public void findTraineeBySkills_returnListOfTrainees() {
		//Arrange
		List<Trainee> testList = new ArrayList<Trainee>();
		List<SkillLevel> test = new ArrayList<>();
		when(traineeRepoMock.findTraineeBySkillsIn(test)).thenReturn(testList);
		
		//Act
		List<Trainee> result = traineeService.findTraineeBySkills(test);
		
		//Assert
		assertEquals(testList, result);
	}
	
	@Test
	public void getTraineeByID_getTrainee() {
		//Arrange
		when(traineeRepoMock.getTraineeByUid(1)).thenReturn(traineeMock);
		
		//Act
		Trainee result = traineeService.getTraineeByID(1);
		
		//Assert
		assertEquals(traineeMock, result);
	}
}
