package com.fdm.qualifier.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.dto.SkillDTO;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.SkillService;

class SkillControllerTest {
	
	SkillController skillController;
	
	@Mock
	private Skill mockSkill;
	
	@Mock
	private SkillDTO mockSkillDTO;
	
	@Mock
	private SkillService mockSkillService;
	
	@Mock
	private SkillLevelService mockSkillLevelService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		skillController = new SkillController(mockSkillService, mockSkillLevelService);
	}
	
	@Test
	void test_getAllSkills_returns_a_Skill_List() {
		List<Skill> skills = new ArrayList<Skill>();
		skills.add(mockSkill);
		skills.add(mockSkill);
		skills.add(mockSkill);
		skills.add(mockSkill);
		when(mockSkillService.findAll()).thenReturn(skills);
		List<Skill> result = skillController.getAllSkills();
		verify(mockSkillService, times(1)).findAll();
		assertEquals(result, skills);
	}
	
	@Test
	void test_getAllSkillsDTO_returns_a_SkillDTO_List() {
		List<SkillDTO> skillDTOs = new ArrayList<SkillDTO>();
		skillDTOs.add(mockSkillDTO);
		skillDTOs.add(mockSkillDTO);
		skillDTOs.add(mockSkillDTO);
		skillDTOs.add(mockSkillDTO);
		when(mockSkillService.findAllSkillDTOs()).thenReturn(skillDTOs);
		List<SkillDTO> result = skillController.getAllSkillsDTO();
		verify(mockSkillService, times(1)).findAllSkillDTOs();
		assertEquals(result, skillDTOs);
	}

//	@Test - issue with stubbing a Map<String, Object>
//	void test_updateSkillName_returns_Map_with_status_fail_when_newSkillName_already_existed() {
//		Map<String, Object> input = new HashMap<String, Object>();
//		Object skillId = 9;
//		Object skillName = "C++";
//		input.put("skillId", skillId);
//		input.put("skillName", skillName);
//		Map<String, String> status = new HashMap<String, String>();
//		status.put("status", "failed");
//		when(mockSkillService.skillExist("C++")).thenReturn(true);
//		Map<String, String> result = skillController.updateSkillName(input);
//		verify(mockSkillService, times(1)).skillExist("C++");
//		assertEquals(result, status);
//	}
	
	@Test
	void test_deleteSkill() {
		String id = "3";
		skillController.deleteSkill(id);
		verify(mockSkillService, times(1)).deleteById(Integer.parseInt(id));
	}
	
	@Test
	void test_addSkill_returns_fail_when_skillName_already_existed() {
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", "already exist");
		String skillname = "java";
		when(mockSkillService.skillExist(skillname)).thenReturn(true);
		Map<String, String> result = skillController.addSkill(skillname);
		verify(mockSkillService, times(1)).skillExist(skillname);
		assertEquals(result, status);
	}
	
	@Test
	void test_addSkill_returns_success_when_skillName_does_not_existed () {
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", "success");
		String skillname = "java";
		when(mockSkillService.skillExist(skillname)).thenReturn(false);
		Map<String, String> result = skillController.addSkill(skillname);
		verify(mockSkillService, times(1)).skillExist(skillname);
		assertEquals(result, status);
	}
}
