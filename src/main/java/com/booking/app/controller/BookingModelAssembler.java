package com.booking.app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.booking.app.entity.Booking;

/**
 * @author vijaysingh
 */
@Component
public class BookingModelAssembler implements RepresentationModelAssembler<Booking, EntityModel<Booking>> {

	@Override
	public EntityModel<Booking> toModel(Booking booking) {
		return EntityModel.of(booking,
				linkTo(methodOn(BookHikingController.class).oneBooking(booking.getId())).withSelfRel(),
				linkTo(methodOn(BookHikingController.class).allBookings()).withRel("bookings"));
	}

	public CollectionModel<EntityModel<Booking>> toModel(List<EntityModel<Booking>> allBookings) {
		return CollectionModel.of(allBookings,
				linkTo(methodOn(BookHikingController.class).allBookings()).withSelfRel());
	}

	public EntityModel<Booking> toCancelModel(Booking booking) {
		return EntityModel.of(booking,
				linkTo(methodOn(BookHikingController.class).cancelBooking(booking.getId())).withSelfRel(),
				linkTo(methodOn(BookHikingController.class).allBookings()).withRel("bookings"));
	}
}
