package com.booking.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.booking.app.businesserror.BookingNotFoundException;
import com.booking.app.entity.Booking;
import com.booking.app.entity.Hiker;
import com.booking.app.entity.Trail;
import com.booking.app.repository.BookingRepository;

/**
 * @author vijaysingh
 */
@Service
public class BookingService {

	private static final Logger log = LoggerFactory.getLogger(BookingService.class);
	
	private static final String AGE_ALERT = "This hiker is not within allowed age limit for this trail.";

	private final BookingRepository bookingRepository;

	BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	public Booking getOneBooking(Long id) {
		return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
	}

	public Booking cancelBooking(Long id) {
		return bookingRepository.findById(id).map(booking -> {
			booking.cancelBooking();
			return bookingRepository.save(booking);
		}).orElseThrow(() -> new BookingNotFoundException(id));
	}

	public Booking newBooking(Booking newBooking, Trail trail) {
		boolean anyHikerNotInAllowedAgeLimit = isAnyHikerNotInAllowedAgeLimit(newBooking.getHikers(), trail);

		if (anyHikerNotInAllowedAgeLimit) {
			return failedBooking(newBooking, trail);
		}

		return successfulBooking(newBooking, trail);
	}

	private boolean isAnyHikerNotInAllowedAgeLimit(Collection<Hiker> hikers, Trail trail) {
		return hikers.stream().anyMatch(hiker -> notWithinAgeLimit(trail, hiker));
		
		//10x faster than streams
		/*
		for (Hiker hiker : hikers) {
			if (notWithinAgeLimit(trail, hiker)) {
				return true;
			}
		}
		return false;
		*/
	}
	
	private Booking failedBooking(Booking newBooking, Trail trail) {
		newBooking.setHikers(addAgeAlertForHikers(newBooking.getHikers(), trail));
		newBooking.setFailureStatus();
		return newBooking;
	}

	private Collection<Hiker> addAgeAlertForHikers(Collection<Hiker> hikers, Trail trail) {
		Collection<Hiker> updatedHikersInfo = new ArrayList<>();
		for (Hiker hiker : hikers) {
			if (notWithinAgeLimit(trail, hiker)) {
				addAgeAlertForHiker(hiker);
			}
			updatedHikersInfo.add(hiker);
		}
		return updatedHikersInfo;
	}
	
	private boolean notWithinAgeLimit(Trail trail, Hiker hiker) {
		return (hiker.getAge() < trail.getMinimumAge()) || (hiker.getAge() > trail.getMaximumAge());
	}
	
	private void addAgeAlertForHiker(Hiker hiker) {
		hiker.markNotAgeAppropriateForHiking();
		hiker.setAlert(AGE_ALERT);
	}

	private void updateTotalBookingCost(Booking newBooking) {
		int numOfHikers = newBooking.getHikers().size();
		float trailUnitCost = newBooking.getBookedTrailDetails().getUnitPrice();
		newBooking.setTotalCost(trailUnitCost * numOfHikers);
	}

	private Booking successfulBooking(Booking newBooking, Trail trail) {
		newBooking.setBookedTrailDetails(trail);
		updateTotalBookingCost(newBooking);
		return bookingRepository.save(newBooking);
	}
}
