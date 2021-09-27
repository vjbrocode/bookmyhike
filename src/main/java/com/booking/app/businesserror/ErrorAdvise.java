package com.booking.app.businesserror;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorAdvise {

	private static final Logger log = LoggerFactory.getLogger(ErrorAdvise.class);
	private static final Marker dataError = MarkerFactory.getMarker("DATA_ERROR");
	private static final Marker clientError = MarkerFactory.getMarker("CLIENT_ERROR");
	private static final Marker serverError = MarkerFactory.getMarker("SERVER_ERROR");
	
	@ResponseBody
	@ExceptionHandler({ TrailNotFoundException.class, BookingNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String recordNotFoundHandler(Exception ex) {
		log.error(dataError, "Record Not Found Error", ex);
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler({ BadRequestDataException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String badDataRequestHandler(Exception ex) {
		log.error(clientError, "Bad Request Error", ex);
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String unHandledError(Exception ex) {
		log.error(serverError, "Unhandled Error", ex);
		return ex.getMessage();
	}

}
