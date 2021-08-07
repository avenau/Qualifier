package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.isA;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.repository.SkillRepository;
import com.fdm.qualifier.repository.SuggestedSkillRepository;

public class SuggestedSkillServiceTest {
	private SuggestedSkillService suggestedSkillService;

	@Mock
	SuggestedSkillRepository suggestedSkillRepoMock;

	@Mock
	SkillRepository skillRepoMock;

	@Mock
	SuggestedSkill suggestedSkillMock;

	@Mock
	Skill skillMock;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		suggestedSkillService = new SuggestedSkillService(suggestedSkillRepoMock, skillRepoMock);
	}

	@Test
	public void test_save_returns_empty_string_when_good() {
		// Arrange
		String name = "java";
		when(suggestedSkillMock.getName()).thenReturn(name);
		when(suggestedSkillRepoMock.findByNameIgnoreCase(name)).thenReturn(new ArrayList<SuggestedSkill>());

		// Act
		String result = suggestedSkillService.save(suggestedSkillMock);

		// Assert
		verify(suggestedSkillRepoMock, times(1)).findByNameIgnoreCase(name);
		verify(suggestedSkillRepoMock, times(1)).save(suggestedSkillMock);
		assertEquals("", result);
	}

	@Test
	public void test_save_returns_skill_suggested_message_string_when_already_in_database() {
		// Arrange
		String name = "java";
		when(suggestedSkillMock.getName()).thenReturn(name);
		List<SuggestedSkill> suggestedSkillsFound = new ArrayList<SuggestedSkill>();
		suggestedSkillsFound.add(suggestedSkillMock);
		when(suggestedSkillRepoMock.findByNameIgnoreCase(name)).thenReturn(suggestedSkillsFound);

		// Act
		String result = suggestedSkillService.save(suggestedSkillMock);

		// Assert
		verify(suggestedSkillRepoMock, times(1)).findByNameIgnoreCase(name);
		verify(suggestedSkillRepoMock, times(0)).save(suggestedSkillMock);
		assertEquals(SuggestedSkillService.SKILL_ALREADY_SUGGESTED_MESSAGE, result);
	}

	@Test
	public void test_getAll_calls_repo_returns_list() {
		// Arrange
		List<SuggestedSkill> expected = new ArrayList<>();
		when(suggestedSkillRepoMock.findAll()).thenReturn(expected);

		// Act
		List<SuggestedSkill> actual = suggestedSkillService.getAll();

		// Assert
		verify(suggestedSkillRepoMock, times(1)).findAll();
		assertEquals(expected, actual);
	}

	@Test
	public void test_findByName_returns_list() {
		// Arrange
		String name = "test"; 
		List<SuggestedSkill> expected = new ArrayList<>();
		when(suggestedSkillRepoMock.findByNameIgnoreCase(name)).thenReturn(expected);

		// Act
		List<SuggestedSkill> actual = suggestedSkillService.findByName(name);

		// Assert
		verify(suggestedSkillRepoMock, times(1)).findByNameIgnoreCase(name);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_declineSuggestedSkill_calls_repo_delete() {
		//Arrange
		
		//Act
		suggestedSkillService.declineSuggestedSkill(suggestedSkillMock);
		
		//Assert
		verify(suggestedSkillRepoMock, times(1)).delete(suggestedSkillMock);
	}
	
	@Test
	public void test_acceptSuggestedSkill_calls_repo_save_and_delete_returns_skill() {
		//Arrange
		when(skillRepoMock.save(isA(Skill.class))).thenReturn(skillMock);
		
		//Act
		Skill actual = suggestedSkillService.acceptSuggestedSkill(suggestedSkillMock);
		
		//Assert
		verify(suggestedSkillRepoMock, times(1)).delete(suggestedSkillMock);
		verify(skillRepoMock, times(1)).save(isA(Skill.class));
		assertEquals(skillMock, actual);
	}
}
