package com.fdm.qualifier.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.service.SkillLevelService;

public class SkillLevelControllerTest {
	private SkillLevelController skillLevelController;
	
	@Mock
	private SkillLevelService skillLevelServiceMock;

	@Mock
	private List<SkillLevel> skillLevelsListMock;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		skillLevelController = new SkillLevelController(skillLevelServiceMock);
	}

	@Test
	public void test_getAllSkillLevels_returns_list() {
		//Arrange
		when(skillLevelServiceMock.findAll()).thenReturn(skillLevelsListMock);
		
		//Act
		List<SkillLevel> actual = skillLevelController.getAllSkillLevels();
		
		//Assert
		verify(skillLevelServiceMock, times(1)).findAll();
		assertEquals(skillLevelsListMock, actual);
	}
}
