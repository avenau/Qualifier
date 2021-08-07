package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.repository.SkillLevelRepository;

public class SkillLevelServiceTest {
	private SkillLevelService skillLevelService;
	
	@Mock
	SkillLevelRepository skillLevelRepoMock;

	@Mock
	SkillLevel traineeSkillMock;

	@Mock
	SkillLevel requiredSkillMock;

	@Mock
	SkillLevel skillLevelMock;

	@Mock
	Skill skillMock;

	@Mock
	List<SkillLevel> skillLevelsMock;

	@Mock
	Quiz quizMock;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.skillLevelService = new SkillLevelService(skillLevelRepoMock);
	}
	
	@Test
	public void test_isSufficentSkills_returns_true_when_skills_at_level() {
		//Arrange
		Skill skill1 = new Skill("skill1");
		SkillLevel skillLevel1 = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, skill1, null);
		Skill skill2 = new Skill("skill2");
		SkillLevel skillLevel2 = new SkillLevel(SkillLevel.KnowledgeLevel.INTERMEDIATE, skill2, null);
		List<SkillLevel> traineeSkills = new ArrayList<>(Arrays.asList(skillLevel1, skillLevel2));
		List<SkillLevel> requiredSkills = traineeSkills;		
		
		//Act
		boolean result = skillLevelService.isSufficientSkills(traineeSkills, requiredSkills);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	public void test_isSufficentSkills_returns_false_when_skills_not_at_level() {
		//Arrange
		Skill skill1 = new Skill("skill1");
		SkillLevel skillLevel1 = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, skill1, null);
		SkillLevel skillLevel1Higher = new SkillLevel(SkillLevel.KnowledgeLevel.EXPERT, skill1, null);
		Skill skill2 = new Skill("skill2");
		SkillLevel skillLevel2 = new SkillLevel(SkillLevel.KnowledgeLevel.INTERMEDIATE, skill2, null);
		List<SkillLevel> traineeSkills = new ArrayList<>(Arrays.asList(skillLevel1, skillLevel2));
		List<SkillLevel> requiredSkills = new ArrayList<>(Arrays.asList(skillLevel1Higher, skillLevel2));		
		
		//Act
		boolean result = skillLevelService.isSufficientSkills(traineeSkills, requiredSkills);
		
		//Assert
		assertFalse(result);
	}

	@Test
	public void test_isSufficentSkills_returns_false_when_missing_skills() {
		//Arrange
		Skill skill1 = new Skill("skill1");
		SkillLevel skillLevel1 = new SkillLevel(SkillLevel.KnowledgeLevel.BEGINNER, skill1, null);
		Skill skill2 = new Skill("skill2");
		SkillLevel skillLevel2 = new SkillLevel(SkillLevel.KnowledgeLevel.INTERMEDIATE, skill2, null);
		List<SkillLevel> traineeSkills = new ArrayList<>(Arrays.asList(skillLevel1 ));
		List<SkillLevel> requiredSkills = new ArrayList<>(Arrays.asList(skillLevel1, skillLevel2));		
		
		//Act
		boolean result = skillLevelService.isSufficientSkills(traineeSkills, requiredSkills);
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	public void test_isSufficentLevel_returns_true_when_at_level() {
		//Arrange
		when(traineeSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.BEGINNER);
		when(requiredSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.BEGINNER);
		
		//Act
		boolean result = skillLevelService.isSufficientLevel(traineeSkillMock, requiredSkillMock);
		
		//Assert
		verify(traineeSkillMock, times(3)).getLevel();
		verify(requiredSkillMock, times(2)).getLevel();
		assertTrue(result);
	}
	
	@Test
	public void test_isSufficentLevel_returns_false_when_level_unverified() {
		//Arrange
		when(traineeSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.UNVERIFIED);
		when(requiredSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.BEGINNER);
		
		//Act
		boolean result = skillLevelService.isSufficientLevel(traineeSkillMock, requiredSkillMock);
		
		//Assert
		verify(traineeSkillMock, times(1)).getLevel();
		verify(requiredSkillMock, times(0)).getLevel();
		assertFalse(result);
	}

	@Test
	public void test_isSufficentLevel_returns_false_when_trainee_level_beginner_and_required_intermediate() {
		//Arrange
		when(traineeSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.BEGINNER);
		when(requiredSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.INTERMEDIATE);
		
		//Act
		boolean result = skillLevelService.isSufficientLevel(traineeSkillMock, requiredSkillMock);
		
		//Assert
		verify(traineeSkillMock, times(2)).getLevel();
		verify(requiredSkillMock, times(1)).getLevel();
		assertFalse(result);
	}

	@Test
	public void test_isSufficentLevel_returns_false_when_trainee_level_beginner_and_required_expert() {
		//Arrange
		when(traineeSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.BEGINNER);
		when(requiredSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.EXPERT);
		
		//Act
		boolean result = skillLevelService.isSufficientLevel(traineeSkillMock, requiredSkillMock);
		
		//Assert
		verify(traineeSkillMock, times(2)).getLevel();
		verify(requiredSkillMock, times(2)).getLevel();
		assertFalse(result);
	}

	@Test
	public void test_isSufficentLevel_returns_false_when_trainee_level_intermediate_and_required_expert() {
		//Arrange
		when(traineeSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.INTERMEDIATE);
		when(requiredSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.EXPERT);
		
		//Act
		boolean result = skillLevelService.isSufficientLevel(traineeSkillMock, requiredSkillMock);
		
		//Assert
		verify(traineeSkillMock, times(3)).getLevel();
		verify(requiredSkillMock, times(1)).getLevel();
		assertFalse(result);
	}

	@Test
	public void test_isSufficentLevel_returns_true_when_trainee_level_intermediate_and_required_beginner() {
		//Arrange
		when(traineeSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.INTERMEDIATE);
		when(requiredSkillMock.getLevel()).thenReturn(SkillLevel.KnowledgeLevel.BEGINNER);
		
		//Act
		boolean result = skillLevelService.isSufficientLevel(traineeSkillMock, requiredSkillMock);
		
		//Assert
		verify(traineeSkillMock, times(3)).getLevel();
		verify(requiredSkillMock, times(1)).getLevel();
		assertTrue(result);
	}

	@Test
	public void test_findById_calls_repo_returns_optional() {
		//Arrange
		int id = 1;
		Optional<SkillLevel> expected = Optional.of(skillLevelMock);
		when(skillLevelRepoMock.findById(id)).thenReturn(expected);
		
		//Act
		Optional<SkillLevel> actual = skillLevelService.findById(id);
		
		//Assert
		verify(skillLevelRepoMock, times(1)).findById(id);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_getById_calls_repo_returns_skillLevel() {
		//Arrange
		int id = 1;
		when(skillLevelRepoMock.findByskillLevelId(id)).thenReturn(skillLevelMock);
		
		//Act
		SkillLevel actual = skillLevelService.getById(id);
		
		//Assert
		verify(skillLevelRepoMock, times(1)).findByskillLevelId(id);
		assertEquals(skillLevelMock, actual);
	}

	@Test
	public void test_findAll_calls_repo_returns_list() {
		//Arrange
		List<SkillLevel> expected = new ArrayList<>();
		when(skillLevelRepoMock.findAll()).thenReturn(expected);
		
		//Act
		List<SkillLevel> actual = skillLevelService.findAll();
		
		//Assert
		verify(skillLevelRepoMock, times(1)).findAll();
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_findBySkill_calls_repo_returns_list() {
		//Arrange
		List<SkillLevel> expected = new ArrayList<>();
		when(skillLevelRepoMock.findBySkill(skillMock)).thenReturn(expected);
		
		//Act
		List<SkillLevel> actual = skillLevelService.findBySkill(skillMock);
		
		//Assert
		verify(skillLevelRepoMock, times(1)).findBySkill(skillMock);
		assertEquals(expected, actual);
	}

	@Test
	public void test_delete_calls_repo() {
		//Arrange
		
		//Act
		skillLevelService.delete(skillLevelMock);
		
		//Assert
		verify(skillLevelRepoMock, times(1)).delete(skillLevelMock);
	}
	
	@Test
	public void test_save_calls_repo_returns_skillLevel() {
		//Arrange
		when(skillLevelRepoMock.save(skillLevelMock)).thenReturn(skillLevelMock);
		
		//Act
		SkillLevel actual = skillLevelService.save(skillLevelMock);
		
		//Assert
		verify(skillLevelRepoMock, times(1)).save(skillLevelMock);
		assertEquals(skillLevelMock, actual);
	}
	
	@Test
	public void test_save_list_calls_repo() {
		//Arrange
		
		//Act
		skillLevelService.save(skillLevelsMock);
		
		//Assert
		verify(skillLevelRepoMock, times(1)).saveAll(skillLevelsMock);
		verify(skillLevelRepoMock, times(1)).flush();
	}
	
	@Test
	public void test_findByQuizId_calls_repo_returns_skillLevel() {
		//Arrange
		when(skillLevelRepoMock.findByQuiz(quizMock)).thenReturn(skillLevelMock);
		
		//Act
		SkillLevel actual = skillLevelService.findByQuizId(quizMock);
		
		//Assert
		verify(skillLevelRepoMock, times(1)).findByQuiz(quizMock);
		assertEquals(skillLevelMock, actual);
	}
}
