package com.booking.app.businesserror;

public class BadRequestDataException extends RuntimeException {
	public BadRequestDataException(String errorMsg) {
		super(errorMsg);
	}
}
