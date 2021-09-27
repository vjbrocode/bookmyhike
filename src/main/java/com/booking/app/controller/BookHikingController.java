package com.booking.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.businesserror.BadRequestDataException;
import com.booking.app.businesserror.TrailNotFoundException;
import com.booking.app.entity.Booking;
import com.booking.app.entity.Booking.BookingStatus;
import com.booking.app.entity.Hiker;
import com.booking.app.entity.Trail;
import com.booking.app.service.BookingService;
import com.booking.app.service.TrailService;
import com.sun.istack.NotNull;

/**
 * @author vijaysingh
 */
@RestController
@RequestMapping("/api/hike/v1/")
public class BookHikingController {

	private static final Logger log = LoggerFactory.getLogger(BookHikingController.class);

	@Autowired
	private TrailService trailService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private BookingModelAssembler bookingAssembler;
	@Autowired
	private TrailModelAssembler trailAssembler;

	@GetMapping("/trails")
	CollectionModel<EntityModel<Trail>> allTrails() {
		List<EntityModel<Trail>> allTrails = trailService.getAllTrails().stream().map(trailAssembler::toModel)
				.collect(Collectors.toList());

		return trailAssembler.toModel(allTrails);
	}

	@GetMapping("/trails/{id}")
	EntityModel<Trail> oneTrail(@PathVariable Long id) {
		Trail oneTrail = trailService.getOneTrail(id);

		return trailAssembler.toModel(oneTrail);
	}

	@GetMapping("/bookings")
	CollectionModel<EntityModel<Booking>> allBookings() {
		List<EntityModel<Booking>> allBookings = bookingService.getAllBookings().stream().map(bookingAssembler::toModel)
				.collect(Collectors.toList());

		return bookingAssembler.toModel(allBookings);
	}

	@GetMapping("/bookings/{id}")
	EntityModel<Booking> oneBooking(@PathVariable Long id) {

		Booking booking = bookingService.getOneBooking(id);

		return bookingAssembler.toModel(booking);
	}

	@PutMapping("/cancelBooking/{id}")
	EntityModel<Booking> cancelBooking(@PathVariable Long id) {
		Booking booking = bookingService.cancelBooking(id);

		return bookingAssembler.toCancelModel(booking);
	}

	@PostMapping("/booktrail")
	ResponseEntity<?> newBooking(@NotNull @RequestBody Booking newBooking)
			throws TrailNotFoundException, BadRequestDataException {
		validate(newBooking);

		log.info("New booking request received for trailId = " + newBooking.getTrailId());
		Trail trail = oneTrail(newBooking.getTrailId()).getContent();
		Booking bookingResponse = bookingService.newBooking(newBooking, trail);

		if (failedBooking(bookingResponse)) {
			return ResponseEntity.badRequest().body(bookingResponse);
		}

		log.info("New booking completed. BookingId = " + bookingResponse.getId());
		EntityModel<Booking> bookingResponseModel = bookingAssembler.toModel(bookingResponse);

		return ResponseEntity.created(bookingResponseModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(bookingResponseModel);
	}

	private void validate(Booking newBooking) {

		StringBuilder errors = new StringBuilder();

		if (null == newBooking.getTrailId()) {
			errors.append("trailId field is mandatory. ");
		}
		if (null == newBooking.getHikingDate()) {
			errors.append("hikingDate field is mandatory. ");
		}
		if (null == newBooking.getHikers() || newBooking.getHikers().isEmpty()) {
			errors.append("hikers field information is mandatory. ");
		}
		if (null != newBooking.getHikingDate() && newBooking.getHikingDate().isBefore(LocalDate.now().plusDays(1))) {
			errors.append("hikingDate should be atlest tomorrow. ");
		}

		boolean mandatoryFieldsForAllHikersArePresent = true;
		for (Hiker h : newBooking.getHikers()) {
			if (null == h.getName() || null == h.getDateOfBirth() || h.getName().isBlank()) {
				mandatoryFieldsForAllHikersArePresent = false;
				break;
			}
		}
		if (!mandatoryFieldsForAllHikersArePresent) {
			errors.append("name and dateOfBirth fields are mandatory for each hiker. ");
		}

		if (!errors.toString().isEmpty()) {
			throw new BadRequestDataException(errors.toString());
		}
	}

	private boolean failedBooking(Booking bookingResponse) {
		return bookingResponse.getStatus() == BookingStatus.FAILED;
	}

}
