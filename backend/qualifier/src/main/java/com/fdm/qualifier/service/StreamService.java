package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Stream;
import com.fdm.qualifier.repository.StreamRepository;

/**
 * Stream Service
 * 
 * @author William
 *
 */
@Service
public class StreamService {
	private StreamRepository streamRepo;

	@Autowired
	public StreamService(StreamRepository streamRepo) {
		this.streamRepo = streamRepo;
	}

	/**
	 * Get Stream by id from repo
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Stream> findById(int id) {
		return streamRepo.findById(id);
	}

	/**
	 * Get streams by name from repo
	 * 
	 * @param name
	 * @return
	 */
	public List<Stream> findByName(String name) {
		return streamRepo.findByName(name);
	}

	/**
	 * Get all streams from repo
	 * 
	 * @return
	 */
	public List<Stream> findAll() {
		return streamRepo.findAll();
	}

	/**
	 * Delete stream by Id from repo
	 * 
	 * @param id
	 */
	public void deleteById(int id) {
		streamRepo.deleteById(id);
	}

	/**
	 * Delete all streams from repo
	 */
	public void deleteAll() {
		streamRepo.deleteAll();
	}

	/**
	 * Save stream to repo
	 * 
	 * @param stream
	 * @return
	 */
	public Stream save(Stream stream) {
		return streamRepo.save(stream);
	}

}
