package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.dto.TraineeSkillLevelDTO;
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

	@Mock
	SkillLevel skillLevelMock;

	@Mock
	Skill skillMock;

	@Mock
	Iterator<SkillLevel> skillsIteratorMock;

	@Mock
	Iterator<SkillLevel> pinnedSkillsIteratorMock;

	
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
		verify(traineeMock, times(2)).getSkills();
		verify(traineeMock, times(2)).getPinnedSkills();
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
	
	@Test
	void test_findTraineeBySkillName_returns_trainee() {
		String skillName = "Ruby";
		Skill skill = new Skill();
		when(skillServiceMock.findByName(skillName)).thenReturn(skill);
		traineeService.findBySkillName(skillName);
		verify(skillServiceMock).findByName(skillName);
		verify(skillLevelServiceMock).findBySkill(skill);		
	}
	
	@Test
	void test_getAllResults() {
		int userId = 1;
		traineeService.getAllResults(userId);
		verify(traineeRepoMock).getResultsByUid(userId);
	}
	
	@Test
	void test_findTraineeByFirstAndLastName() {
		String fName = "test1";
		String lName = "test2";
		traineeService.findByFirstAndLastName(fName, lName);
		verify(traineeRepoMock).findByFirstNameAndLastName(fName, lName);
	}
	
	
	@Test
	void test_addSkillToTrainee_returns_true_when_trainee_doesn_not_have_skill() {
		//Arrange
		int id = 1;
		when(traineeRepoMock.getTraineeByUid(id)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.contains(skillLevelMock)).thenReturn(false);
		when(pinnedSkillsMock.contains(skillLevelMock)).thenReturn(false);
		when(skillLevelMock.getSkill()).thenReturn(skillMock);
		when(skillsMock.iterator()).thenReturn(skillsIteratorMock);
		when(skillsIteratorMock.hasNext()).thenReturn(false);
		when(pinnedSkillsMock.iterator()).thenReturn(pinnedSkillsIteratorMock);
		when(pinnedSkillsIteratorMock.hasNext()).thenReturn(false);
		
		//Act
		boolean result = traineeService.addSkillToTrainee(skillLevelMock, id);
		
		//Assert
		verify(traineeRepoMock, times(1)).getTraineeByUid(id);
		verify(traineeMock, times(2)).getSkills();
		verify(traineeMock, times(2)).getPinnedSkills();
		verify(skillsMock, times(1)).contains(skillLevelMock);
		verify(pinnedSkillsMock, times(1)).contains(skillLevelMock);
		verify(traineeMock, times(1)).addSkill(skillLevelMock);
		assertTrue(result);
	}
	
	@Test
	void test_addSkillToTrainee_returns_false_when_trainee_null() {
		//Arrange
		int id = 1;
		when(traineeRepoMock.getTraineeByUid(id)).thenReturn(null);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.contains(skillLevelMock)).thenReturn(false);
		when(pinnedSkillsMock.contains(skillLevelMock)).thenReturn(false);
		when(skillLevelMock.getSkill()).thenReturn(skillMock);
		when(skillsMock.iterator()).thenReturn(skillsIteratorMock);
		when(skillsIteratorMock.hasNext()).thenReturn(false);
		when(pinnedSkillsMock.iterator()).thenReturn(pinnedSkillsIteratorMock);
		when(pinnedSkillsIteratorMock.hasNext()).thenReturn(false);
		
		//Act
		boolean result = traineeService.addSkillToTrainee(skillLevelMock, id);
		
		//Assert
		verify(traineeRepoMock, times(1)).getTraineeByUid(id);
		verify(traineeMock, times(0)).getSkills();
		verify(traineeMock, times(0)).getPinnedSkills();
		verify(skillsMock, times(0)).contains(skillLevelMock);
		verify(pinnedSkillsMock, times(0)).contains(skillLevelMock);
		verify(traineeMock, times(0)).addSkill(skillLevelMock);
		assertFalse(result);
	}

	@Test
	void test_addSkillToTrainee_returns_false_when_trainee_skills_contains_skill() {
		//Arrange
		int id = 1;
		when(traineeRepoMock.getTraineeByUid(id)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.contains(skillLevelMock)).thenReturn(true);
		when(pinnedSkillsMock.contains(skillLevelMock)).thenReturn(false);
		when(skillLevelMock.getSkill()).thenReturn(skillMock);
		when(skillsMock.iterator()).thenReturn(skillsIteratorMock);
		when(skillsIteratorMock.hasNext()).thenReturn(false);
		when(pinnedSkillsMock.iterator()).thenReturn(pinnedSkillsIteratorMock);
		when(pinnedSkillsIteratorMock.hasNext()).thenReturn(false);
		
		//Act
		boolean result = traineeService.addSkillToTrainee(skillLevelMock, id);
		
		//Assert
		verify(traineeRepoMock, times(1)).getTraineeByUid(id);
		verify(traineeMock, times(1)).getSkills();
		verify(traineeMock, times(0)).getPinnedSkills();
		verify(skillsMock, times(1)).contains(skillLevelMock);
		verify(pinnedSkillsMock, times(0)).contains(skillLevelMock);
		verify(traineeMock, times(0)).addSkill(skillLevelMock);
		assertFalse(result);
	}

	@Test
	void test_addSkillToTrainee_returns_false_when_trainee_pinnedSkills_contains_skill() {
		//Arrange
		int id = 1;
		when(traineeRepoMock.getTraineeByUid(id)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.contains(skillLevelMock)).thenReturn(false);
		when(pinnedSkillsMock.contains(skillLevelMock)).thenReturn(true);
		when(skillLevelMock.getSkill()).thenReturn(skillMock);
		when(skillsMock.iterator()).thenReturn(skillsIteratorMock);
		when(skillsIteratorMock.hasNext()).thenReturn(false);
		when(pinnedSkillsMock.iterator()).thenReturn(pinnedSkillsIteratorMock);
		when(pinnedSkillsIteratorMock.hasNext()).thenReturn(false);
		
		//Act
		boolean result = traineeService.addSkillToTrainee(skillLevelMock, id);
		
		//Assert
		verify(traineeRepoMock, times(1)).getTraineeByUid(id);
		verify(traineeMock, times(1)).getSkills();
		verify(traineeMock, times(1)).getPinnedSkills();
		verify(skillsMock, times(1)).contains(skillLevelMock);
		verify(pinnedSkillsMock, times(1)).contains(skillLevelMock);
		verify(traineeMock, times(0)).addSkill(skillLevelMock);
		assertFalse(result);
	}

	@Test
	void test_addSkillToTrainee_returns_false_when_trainee_skills_has_skill_at_different_level() {
		//Arrange
		int id = 1;
		when(traineeRepoMock.getTraineeByUid(id)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.contains(skillLevelMock)).thenReturn(false);
		when(pinnedSkillsMock.contains(skillLevelMock)).thenReturn(false);
		when(skillLevelMock.getSkill()).thenReturn(skillMock);
		when(skillsMock.iterator()).thenReturn(skillsIteratorMock);
		when(skillsIteratorMock.hasNext()).thenReturn(true, false);
		when(pinnedSkillsMock.iterator()).thenReturn(pinnedSkillsIteratorMock);
		when(pinnedSkillsIteratorMock.hasNext()).thenReturn(false);
		when(skillsIteratorMock.next()).thenReturn(skillLevelMock);
		
		//Act
		boolean result = traineeService.addSkillToTrainee(skillLevelMock, id);
		
		//Assert
		verify(traineeRepoMock, times(1)).getTraineeByUid(id);
		verify(traineeMock, times(2)).getSkills();
		verify(traineeMock, times(2)).getPinnedSkills();
		verify(skillsMock, times(1)).contains(skillLevelMock);
		verify(pinnedSkillsMock, times(1)).contains(skillLevelMock);
		verify(traineeMock, times(0)).addSkill(skillLevelMock);
		assertFalse(result);
	}
	
	@Test
	void test_addSkillToTrainee_returns_false_when_trainee_pinnedSkills_has_skill_at_different_level() {
		//Arrange
		int id = 1;
		when(traineeRepoMock.getTraineeByUid(id)).thenReturn(traineeMock);
		when(traineeMock.getSkills()).thenReturn(skillsMock);
		when(traineeMock.getPinnedSkills()).thenReturn(pinnedSkillsMock);
		when(skillsMock.contains(skillLevelMock)).thenReturn(false);
		when(pinnedSkillsMock.contains(skillLevelMock)).thenReturn(false);
		when(skillLevelMock.getSkill()).thenReturn(skillMock);
		when(skillsMock.iterator()).thenReturn(skillsIteratorMock);
		when(skillsIteratorMock.hasNext()).thenReturn(false);
		when(pinnedSkillsMock.iterator()).thenReturn(pinnedSkillsIteratorMock);
		when(pinnedSkillsIteratorMock.hasNext()).thenReturn(true, false);
		when(skillsIteratorMock.next()).thenReturn(skillLevelMock);
		when(pinnedSkillsIteratorMock.next()).thenReturn(skillLevelMock);
		
		//Act
		boolean result = traineeService.addSkillToTrainee(skillLevelMock, id);
		
		//Assert
		verify(traineeRepoMock, times(1)).getTraineeByUid(id);
		verify(traineeMock, times(2)).getSkills();
		verify(traineeMock, times(2)).getPinnedSkills();
		verify(skillsMock, times(1)).contains(skillLevelMock);
		verify(pinnedSkillsMock, times(1)).contains(skillLevelMock);
		verify(traineeMock, times(0)).addSkill(skillLevelMock);
		assertFalse(result);
	}
	
	@Test
	public void test_removeSkillFromTrainee_calls_repo_removes_skill() {
		//Arrange
		int id = 1;
		when(traineeRepoMock.getTraineeByUid(id)).thenReturn(traineeMock);

		//Act
		traineeService.removeSkillFromTrainee(skillMock, id);
		
		//Assert
		verify(traineeRepoMock, times(1)).getTraineeByUid(id);
		verify(traineeMock, times(1)).removeSkill(skillMock);
	}
	
	@Test
	public void test_getPinnedSkillsAsDTO_returns_DTO_list() {
		//Arrange
		int id = 1;
		when(traineeRepoMock.getPinnedSkillsByUid(id)).thenReturn(pinnedSkillsMock);
		when(pinnedSkillsMock.iterator()).thenReturn(pinnedSkillsIteratorMock);
		when(pinnedSkillsIteratorMock.hasNext()).thenReturn(true, false);
		when(pinnedSkillsIteratorMock.next()).thenReturn(skillLevelMock);
		when(skillLevelMock.getSkill()).thenReturn(skillMock);

		TraineeSkillLevelDTO skillLevelAsDTO = new TraineeSkillLevelDTO(skillLevelMock);
		
		//Act
		List<TraineeSkillLevelDTO> actual = traineeService.getPinnedSkillsAsDTO(id);
		
		//Assert
		verify(traineeRepoMock, times(1)).getPinnedSkillsByUid(id);
		assertEquals(skillLevelAsDTO.getSkillLevelId(), actual.get(0).getSkillLevelId());
		assertEquals(skillLevelAsDTO.getLevel(), actual.get(0).getLevel());
		assertEquals(skillLevelAsDTO.getSkill().getSkillId(), actual.get(0).getSkill().getSkillId());
		assertEquals(skillLevelAsDTO.getSkill().getName(), actual.get(0).getSkill().getName());
	}
	
	@Test
	public void test_getSkillsAsDTO_returns_DTO_list() {
		//Arrange
		int id = 1;
		when(traineeRepoMock.getSkillsByUid(id)).thenReturn(skillsMock);
		when(skillsMock.iterator()).thenReturn(skillsIteratorMock);
		when(skillsIteratorMock.hasNext()).thenReturn(true, false);
		when(skillsIteratorMock.next()).thenReturn(skillLevelMock);
		when(skillLevelMock.getSkill()).thenReturn(skillMock);

		TraineeSkillLevelDTO skillLevelAsDTO = new TraineeSkillLevelDTO(skillLevelMock);
		
		//Act
		List<TraineeSkillLevelDTO> actual = traineeService.getSkillsAsDTO(id);
		
		//Assert
		verify(traineeRepoMock, times(1)).getSkillsByUid(id);
		assertEquals(skillLevelAsDTO.getSkillLevelId(), actual.get(0).getSkillLevelId());
		assertEquals(skillLevelAsDTO.getLevel(), actual.get(0).getLevel());
		assertEquals(skillLevelAsDTO.getSkill().getSkillId(), actual.get(0).getSkill().getSkillId());
		assertEquals(skillLevelAsDTO.getSkill().getName(), actual.get(0).getSkill().getName());
	}

}
