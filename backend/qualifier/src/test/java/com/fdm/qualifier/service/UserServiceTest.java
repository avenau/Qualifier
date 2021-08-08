package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdm.qualifier.model.AuthRequest;
import com.fdm.qualifier.model.User;
import com.fdm.qualifier.repository.UserRepository;

public class UserServiceTest {

	private UserService userService;
	
	@Mock
	private UserRepository userRepoMock;
	
	@Mock
	private PasswordEncoder passwordEncoderMock;

	@Mock
	private User mockUser;

	@Mock
	private AuthRequest mockAuthRequest;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		userService = new UserService(userRepoMock, passwordEncoderMock);
	}
	
	@Test
	public void test_getUserByUsername_calls_repo_returns_user_when_present() {
		//Arrange
		String username = "username";
		when(userRepoMock.findByUsername(username)).thenReturn(Optional.of(mockUser));
		
		//Act
		User actual = userService.getUserByUsername(username);
		
		//Assert
		verify(userRepoMock, times(1)).findByUsername(username);
		assertEquals(mockUser, actual);
	}

	@Test
	public void test_getUserByUsername_calls_repo_returns_null_when_not_present() {
		//Arrange
		String username = "username";
		when(userRepoMock.findByUsername(username)).thenReturn(Optional.of(mockUser).empty());
		
		//Act
		User actual = userService.getUserByUsername(username);
		
		//Assert
		verify(userRepoMock, times(1)).findByUsername(username);
		assertEquals(null, actual);
	}
	
	@Test
	public void test_userExists_returns_true_when_not_in_repo() {
		//Arrange
		String username = "username";
		when(mockAuthRequest.getUsername()).thenReturn(username);
		when(userRepoMock.findByUsername(username)).thenReturn(Optional.of(mockUser).empty());
		
		//Act
		boolean result = userService.userExists(mockAuthRequest);
		
		//Assert
		verify(userRepoMock, times(1)).findByUsername(username);
		assertTrue(result);
	}

	@Test
	public void test_userExists_returns_false_when_in_repo() {
		//Arrange
		String username = "username";
		when(mockAuthRequest.getUsername()).thenReturn(username);
		when(userRepoMock.findByUsername(username)).thenReturn(Optional.of(mockUser));
		
		//Act
		boolean result = userService.userExists(mockAuthRequest);
		
		//Assert
		verify(userRepoMock, times(1)).findByUsername(username);
		assertFalse(result);
	}

	@Test
	public void test_findById_returns_user_when_present() {
		//Arrange
		int id = 1;
		when(userRepoMock.findById(id)).thenReturn(Optional.of(mockUser));
		
		//Act
		User actual = userService.findById(id);
		
		//Assert
		verify(userRepoMock, times(1)).findById(id);
		assertEquals(mockUser, actual);
	}

	@Test
	public void test_findById_returns_null_when_not_present() {
		//Arrange
		int id = 1;
		when(userRepoMock.findById(id)).thenReturn(Optional.of(mockUser).empty());
		
		//Act
		User actual = userService.findById(id);
		
		//Assert
		verify(userRepoMock, times(1)).findById(id);
		assertEquals(null, actual);
	}
	
	@Test
	public void test_saveUser_calls_repo_save_returns_user() {
		//Arrange
		when(userRepoMock.save(mockUser)).thenReturn(mockUser);
		
		//Act
		User actual = userService.saveUser(mockUser);
		
		//Assert
		verify(userRepoMock, times(1)).save(mockUser);
		assertEquals(mockUser, actual);
	}
}
