package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.isA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.dto.SkillDTO;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.repository.SkillRepository;

public class SkillServiceTest {

	SkillService skillService;

	@Mock
	SkillRepository skillRepoMock;

	@Mock
	List<Skill> skillsMock;

	@Mock
	Iterator<Skill> skillsIteratorMock;

	@Mock
	Skill mockSkill;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		skillService = new SkillService(skillRepoMock);
	}

	@Test
	public void test_save_multiple() {
		// Arrange
		List<Skill> skills = new ArrayList<>();

		// Act
		skillService.save(skills);

		// Assert
		verify(skillRepoMock, times(1)).saveAll(skills);
		verify(skillRepoMock, times(1)).flush();
	}

	@Test
	public void test_save_multiple_all_unique() {
		// Arrange
		Skill skill1 = new Skill("unique");
		Skill skill2 = new Skill("unique2");
		List<Skill> skills = new ArrayList<>(Arrays.asList(skill1, skill2));

		// Act
		skillService.save(skills);

		// Assert
		verify(skillRepoMock, times(1)).saveAll(skills);
		verify(skillRepoMock, times(1)).flush();
	}

	@Test
	public void test_save_multiple_non_unique() {
		// Arrange
		Skill skill1 = new Skill("unique");
		Skill skill2 = new Skill("not-unique");
		when(skillsMock.iterator()).thenReturn(skillsIteratorMock);
		when(skillsIteratorMock.hasNext()).thenReturn(true, true, false);
		when(skillsIteratorMock.next()).thenReturn(skill1, skill2);
		when(skillRepoMock.findByName(skill2.getName())).thenReturn(skill2);

		// Act
		skillService.save(skillsMock);

		// Assert
		verify(skillRepoMock, times(1)).saveAll(skillsMock);
		verify(skillRepoMock, times(1)).flush();
	}

	@Test
	public void test_deleteById_calls_repo_deleteById() {
		// Arrange
		int id = 1;

		// Act
		skillService.deleteById(id);

		// Assert
		verify(skillRepoMock, times(1)).deleteById(id);
	}

	@Test
	public void test_findByName_calls_repo_findByName_returns_skill() {
		// Arrange
		String name = "test";
		when(skillRepoMock.findByName(name)).thenReturn(mockSkill);

		// Act
		Skill actual = skillService.findByName(name);

		// Assert
		verify(skillRepoMock, times(1)).findByName(name);
		assertEquals(mockSkill, actual);
	}

	@Test
	public void test_findAll_calls_repo_returns_list() {
		// Arrange
		List<Skill> expected = new ArrayList<>();
		when(skillRepoMock.findAll()).thenReturn(expected);

		// Act
		List<Skill> actual = skillService.findAll();

		// Assert
		verify(skillRepoMock, times(1)).findAll();
		assertEquals(expected, actual);
	}

	@Test
	public void test_save_calls_repo_returns_skill() {
		// Arrange
		when(skillRepoMock.save(mockSkill)).thenReturn(mockSkill);

		// Act
		Skill actual = skillService.save(mockSkill);

		// Assert
		verify(skillRepoMock, times(1)).save(mockSkill);
		assertEquals(mockSkill, actual);
	}

	@Test
	public void test_saveByName_calls_repo_save_when_new_skill_returns_skill() {
		// Arrange
		String name = "test";
		when(skillRepoMock.findByName(name)).thenReturn(null);
		when(skillRepoMock.save(isA(Skill.class))).thenReturn(mockSkill);
		
		// Act
		Skill actual = skillService.saveByName(name);

		// Assert
		verify(skillRepoMock, times(1)).findByName(name);
		verify(skillRepoMock, times(1)).save(isA(Skill.class));
		assertEquals(mockSkill, actual);
	}
	
	@Test
	public void test_saveByName_does_not_call_repo_save_when_new_skill_returns_skill() {
		// Arrange
		String name = "test";
		when(skillRepoMock.findByName(name)).thenReturn(mockSkill);
		when(skillRepoMock.save(isA(Skill.class))).thenReturn(mockSkill);
		
		// Act
		Skill actual = skillService.saveByName(name);

		// Assert
		verify(skillRepoMock, times(1)).findByName(name);
		verify(skillRepoMock, times(0)).save(isA(Skill.class));
		assertEquals(mockSkill, actual);
	}
	
	@Test
	public void test_skillExist_returns_true_when_skill_with_name_in_repo() {
		//Arrange
		String name = "test";
		when(skillRepoMock.findByName(name)).thenReturn(mockSkill);
		
		//Act
		boolean result = skillService.skillExist(name);
		
		//Assert
		assertTrue(result);
	}

	@Test
	public void test_skillExist_returns_false_when_skill_with_name_not_in_repo() {
		//Arrange
		String name = "test";
		when(skillRepoMock.findByName(name)).thenReturn(null);
		
		//Act
		boolean result = skillService.skillExist(name);
		
		//Assert
		assertFalse(result);
	}

	@Test
	public void test_findAllSkillDTOs_returns_SkillDTO_list() {
		//Arrange
		String name = "test";
		Skill skill = new Skill(name);
		SkillDTO skillDTO = new SkillDTO(skill);
		List<Skill> skills = new ArrayList<>(Arrays.asList(skill));
		List<SkillDTO> expected = new ArrayList<>(Arrays.asList(skillDTO)); 
		when(skillRepoMock.findAll()).thenReturn(skills);
		
		//Act
		List<SkillDTO> actual = skillService.findAllSkillDTOs();
		
		//Assert
		verify(skillRepoMock, times(1)).findAll();
		assertEquals(expected.get(0).getSkillId(), actual.get(0).getSkillId());
		assertEquals(expected.get(0).getName(), actual.get(0).getName());
	}
	
	@Test
	public void test_findById_calls_repo_returns_skill() {
		//Arrange
		int id = 1;
		when(skillRepoMock.findBySkillId(id)).thenReturn(mockSkill);
		
		//Act
		Skill actual = skillService.findById(id);

		//Assert
		verify(skillRepoMock, times(1)).findBySkillId(id);
		assertEquals(mockSkill, actual);
	}
}
