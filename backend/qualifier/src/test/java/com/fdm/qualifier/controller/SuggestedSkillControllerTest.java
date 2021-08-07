package com.fdm.qualifier.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.dto.SkillDTO;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.SkillService;
import com.fdm.qualifier.service.SuggestedSkillService;

class SuggestedSkillControllerTest {
	
	SuggestedSkillController suggestedSkillController;
	
	@Mock
	private Skill mockSkill;
	
	@Mock
	private SkillDTO mockSkillDTO;
	
	@Mock
	private SkillService mockSkillService;
	
	@Mock
	private SkillLevelService mockSkillLevelService;

	@Mock
	private SuggestedSkillService suggestedSkillService;
	
	@Mock
	private SuggestedSkill suggestedSkill;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		suggestedSkillController = new SuggestedSkillController(suggestedSkillService);
	}
	
	@Test
	void test_saveSuggestedSkill() {	
		suggestedSkillController.save(suggestedSkill);
		verify(suggestedSkillService, times(1)).save(suggestedSkill);
	}
	
	@Test
	void test_getAll() {	
		suggestedSkillController.getAll();
		verify(suggestedSkillService, times(1)).getAll();
	}
	
	@Test
	void test_removeSuggestedSkill_fail() {	
		Map<String, String> result = suggestedSkillController.removeSuggestedSkill("DoesntExist");
		assertEquals(result.get("status"), "failed");
		
	}
	
	@Test
	void test_removeSuggestedSkill_success() {
		String existSkillName = "exist";
		List<SuggestedSkill> list = new ArrayList<SuggestedSkill> ();
		list.add(suggestedSkill);
		when(suggestedSkillService.findByName(existSkillName)).thenReturn(list);
		Map<String, String>  newResult = suggestedSkillController.removeSuggestedSkill(existSkillName);
		assertEquals(newResult.get("status"), "success");
	}
}