package com.fdm.qualifier.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class MainControllerTest {
	MainController mainController;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		mainController = new MainController();
	}
	
	@Test
	public void test_mainpage_GET_returns_reponseEntity() {
		//Arrange
		
		//Act
		ResponseEntity<?> actual = mainController.mainpage_GET();
		
		//Assert
		assertEquals(MainController.RETURNING_MAIN_PAGE_MESSAGE, actual.getBody());
	}
	
}
