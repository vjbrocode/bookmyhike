package com.booking.app.businesserror;

public class BookingNotFoundException extends RuntimeException {
	public BookingNotFoundException(Long id) {
		super("Could not find booking " + id);
	}
}
