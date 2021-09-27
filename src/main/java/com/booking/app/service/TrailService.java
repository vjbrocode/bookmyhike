package com.booking.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.app.businesserror.TrailNotFoundException;
import com.booking.app.entity.Trail;
import com.booking.app.repository.TrailRepository;

/**
 * @author vijaysingh
 */
@Service
public class TrailService {
	private final TrailRepository trailRepository;
	
	TrailService(TrailRepository trailRepository) {
		this.trailRepository = trailRepository;
	}
	
	public List<Trail> getAllTrails() {
		return trailRepository.findAll();
	}

	public Trail getOneTrail(Long id) {
		return trailRepository.findById(id).orElseThrow(() -> new TrailNotFoundException(id));
	}
}
