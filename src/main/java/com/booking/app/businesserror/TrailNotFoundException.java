package com.booking.app.businesserror;

public class TrailNotFoundException extends RuntimeException {
	public TrailNotFoundException(Long id) {
		super("Could not find trail " + id);
	}
}
