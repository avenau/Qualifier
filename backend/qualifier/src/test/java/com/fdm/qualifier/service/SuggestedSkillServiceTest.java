package com.fdm.qualifier.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.repository.SuggestedSkillRepository;

public class SuggestedSkillServiceTest {
	private SuggestedSkillService suggestedSkillService;
	
	@Mock
	SuggestedSkillRepository suggestedSkillRepoMock;

	@Mock
	SuggestedSkill suggestedSkillMock;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		suggestedSkillService = new SuggestedSkillService(suggestedSkillRepoMock);
	}
	
	@Test
	public void test_save_calls_repo_save() {
		//Arrange
		
		//Act
		suggestedSkillService.save(suggestedSkillMock);
		
		//Assert
		verify(suggestedSkillRepoMock, times(1)).save(suggestedSkillMock);
	}
}
