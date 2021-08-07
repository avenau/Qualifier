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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import com.fdm.qualifier.dto.SkillDTO;
import com.fdm.qualifier.model.AuthRequest;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.model.User;
import com.fdm.qualifier.service.AccountDetailsService;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.SkillService;
import com.fdm.qualifier.service.SuggestedSkillService;
import com.fdm.qualifier.service.UserService;
import com.fdm.qualifier.util.JwtUtil;

public class UserControllerTest {
	UserController userController;
	
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

	@Mock
	private AuthenticationManager authenticationManagerMock;

	@Mock
	private AccountDetailsService accountDetailsServiceMock;

	@Mock
	private UserService userServiceMock;
	
	@Mock
	private JwtUtil jwtUtilMock;
	@Mock
	private AuthRequest authRequestMock;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		userController = new UserController(authenticationManagerMock, accountDetailsServiceMock, userServiceMock, jwtUtilMock);
	}
	
	@Test
	void test_saveLogin() {	
		try {
			User user = new User("John", "Smith", "trainer");
			when(userServiceMock.getUserByUsername(authRequestMock.getUsername())).thenReturn(user);
			ResponseEntity<?> response = userController.generateToken(authRequestMock);
			assertEquals(response.getStatusCodeValue(), 200);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void test_getLoggedInUser() {	
		String username = "dolan";
		userController.getLoggedInUser(username);
		verify(userServiceMock, times(1)).getUserByUsername(username);
		
	}

}
