package com.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.app.entity.Trail;

public interface TrailRepository extends JpaRepository<Trail, Long> {

}
