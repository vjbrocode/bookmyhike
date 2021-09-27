package com.booking.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.booking.app.entity.Booking;
import com.booking.app.entity.Hiker;
import com.booking.app.entity.Trail;
import com.booking.app.repository.BookingRepository;
import com.booking.app.repository.TrailRepository;

/**
 * Loads the database with 3 default trails and 3 sample booking for app testing
 * @author vijaysingh
 */
@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Autowired
	TrailRepository trailRepository;
	
	@Autowired
	BookingRepository bookingRepository;

	@Bean
	CommandLineRunner initDatabase() {
		return args -> {
			log.info("Preloading " + trailRepository.save(new Trail("Shire", "07:00", "09:00", 5, 100, 29.90f)));
			log.info("Preloading " + trailRepository.save(new Trail("Gondor", "10:00", "13:00", 11, 50, 59.90f)));
			log.info("Preloading " + trailRepository.save(new Trail("Mordor", "14:00", "19:00", 18, 40, 99.90f)));
			
			List<Hiker> hikers1 = new ArrayList<>();
			Hiker hiker11 = new Hiker("Vijay", LocalDate.of(1992, 9, 14));
			Hiker hiker12 = new Hiker("Mike", LocalDate.of(2000, 5, 12));
			hikers1.add(hiker11);
			hikers1.add(hiker12);
			log.info("Preloading " + bookingRepository.save(new Booking(1L, LocalDate.now(), hikers1)));
			
			List<Hiker> hikers2 = new ArrayList<>();
			Hiker hiker21 = new Hiker("Arun", LocalDate.of(1990, 1, 11));
			Hiker hiker22 = new Hiker("Alex", LocalDate.of(1995, 11, 24));
			hikers2.add(hiker21);
			hikers2.add(hiker22);
			log.info("Preloading " + bookingRepository.save(new Booking(2L, LocalDate.now(), hikers2)));
			
			List<Hiker> hikers3 = new ArrayList<>();
			Hiker hiker31 = new Hiker("Gopal", LocalDate.of(2005, 12, 20));
			Hiker hiker32 = new Hiker("Claire", LocalDate.of(2002, 2, 18));
			hikers3.add(hiker31);
			hikers3.add(hiker32);
			log.info("Preloading " + bookingRepository.save(new Booking(3L, LocalDate.now(), hikers3)));
		};
	}
	
}
