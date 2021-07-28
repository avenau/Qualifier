package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Stream;
import com.fdm.qualifier.repository.StreamRepository;

@Service
public class StreamService {
	private StreamRepository streamRepo;
	
	@Autowired
	public StreamService(StreamRepository streamRepo) {
		this.streamRepo = streamRepo;
	}
	
	public Optional<Stream> findById(int id){
		return streamRepo.findById(id);
	}
	
	public List<Stream> findByName(String name){
		return streamRepo.findByName(name);
	}
	
	public List<Stream> findAll(){
		return streamRepo.findAll();
	}
	
	public void deleteById(int id) {
		streamRepo.deleteById(id);
	}
	
	public void deleteAll() {
		streamRepo.deleteAll();
	}
	
	public Stream save(Stream stream) {
		return streamRepo.save(stream);
	}
	
	
}
