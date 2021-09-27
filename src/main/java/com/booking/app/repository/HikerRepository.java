package com.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.app.entity.Hiker;

public interface HikerRepository extends JpaRepository<Hiker, Long> {

}
