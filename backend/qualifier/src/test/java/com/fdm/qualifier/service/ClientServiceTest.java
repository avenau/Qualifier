package com.fdm.qualifier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.repository.ClientRepository;

public class ClientServiceTest {
	private ClientService clientService;
	
	@Mock
	private ClientRepository clientRepoMock;

	@Mock
	private Client clientMock;

	@Mock
	private List<Client> clientsMock;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		clientService = new ClientService(clientRepoMock);
	}
	
	@Test
	public void test_save_calls_repo_returns_client() {
		//Arrange
		when(clientRepoMock.save(clientMock)).thenReturn(clientMock);
		
		//Act
		Client actual = clientService.save(clientMock);

		//Assert
		verify(clientRepoMock, times(1)).save(clientMock);
		assertEquals(clientMock, actual);
	}
	
	@Test
	public void test_findByName_calls_repo_returns_client() {
		//Arrange
		String name = "test";
		when(clientRepoMock.findByName(name)).thenReturn(clientMock);
		
		//Act
		Client actual = clientService.findByName(name);

		//Assert
		verify(clientRepoMock, times(1)).findByName(name);
		assertEquals(clientMock, actual);
	}
	
	@Test
	public void test_getAllClients_calls_repo_returns_list() {
		//Arrange
		when(clientRepoMock.findAll()).thenReturn(clientsMock);
		
		//Act
		List<Client> actual = clientService.getAllClients();
		
		//Assert
		verify(clientRepoMock, times(1)).findAll();
		assertEquals(clientsMock, actual);
	}
}
