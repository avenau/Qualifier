package com.fdm.qualifier.service;

import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Stream;
import com.fdm.qualifier.repository.StreamRepository;

class StreamServiceTest {
	StreamService streamService;

	@Mock
	private StreamRepository mockStreamRepo;
	@Mock
	private Stream mockStream;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		streamService = new StreamService(mockStreamRepo);
	}
	
	@Test
	void test_findById() {
		streamService.findById(1);
		verify(mockStreamRepo).findById(1);
	}
	
	@Test
	void test_findByName() {
		streamService.findByName("hello");
		verify(mockStreamRepo).findByName("hello");
	}
	
	@Test
	void test_deleteById() {
		streamService.deleteById(1);
		verify(mockStreamRepo).deleteById(1);
	}
	
	@Test
	void test_deleteAll() {
		streamService.deleteAll();
		verify(mockStreamRepo).deleteAll();
	}
	
	@Test
	void test_save() {
		streamService.save(mockStream);
		verify(mockStreamRepo).save(mockStream);
	}
	
	@Test
	void test_findAll() {
		streamService.findAll();
		verify(mockStreamRepo).findAll();
	}
	

}
