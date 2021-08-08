package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fdm.qualifier.model.User;
import com.fdm.qualifier.repository.UserRepository;

public class AccountDetailsServiceTest {
	AccountDetailsService accountDetailsService;

	@Mock
	private UserRepository userRepoMock;

	@Mock
	private User userMock;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		accountDetailsService = new AccountDetailsService(userRepoMock);
	}

	@Test
	public void test_loadUserByUsername_returns_account_map() {
		// Arrange
		String username = "test";
		when(userRepoMock.findByUsername(username)).thenReturn(Optional.of(userMock));
		when(userMock.getAccountType()).thenReturn("trainee");
		when(userMock.getUsername()).thenReturn(username);

		// Act
		UserDetails actual = accountDetailsService.loadUserByUsername(username);

		// Assert
		verify(userRepoMock, times(1)).findByUsername(username);
		assertEquals(username, actual.getUsername());
	}

	@Test
	public void test_loadUserByUsername_throws_UsernameNotFoundException() {
		// Arrange
		String username = "test";
		when(userRepoMock.findByUsername(username)).thenReturn(Optional.of(userMock).empty());
		when(userMock.getAccountType()).thenReturn("trainee");

		// Act
		assertThrows(UsernameNotFoundException.class, () -> {
			UserDetails actual = accountDetailsService.loadUserByUsername(username);
		});
		
		// Assert
		verify(userRepoMock, times(1)).findByUsername(username);
	}

}
