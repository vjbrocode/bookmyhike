package com.booking.app.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Hiker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hid;
	
	private String name;
	private LocalDate dateOfBirth;
	@Transient
	private boolean ageAppropriateForHiking = true;
	@Transient
	private String alert = "None";

	Hiker() {
	}

	public Hiker(String name, LocalDate dob) {
		this.name = name;
		this.dateOfBirth = dob;
	}

	public boolean isAgeAppropriateForHiking() {
		return ageAppropriateForHiking;
	}

	public void markNotAgeAppropriateForHiking() {
		this.ageAppropriateForHiking = false;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public Long getHid() {
		return hid;
	}

	public void setHid(Long hid) {
		this.hid = hid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		Period period = Period.between(dateOfBirth, LocalDate.now());
		return period.getYears();
	}

	@Override
	public String toString() {
		return "Hiker [hid=" + hid + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", age=" + getAge() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ageAppropriateForHiking, alert, dateOfBirth, hid, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hiker other = (Hiker) obj;
		return ageAppropriateForHiking == other.ageAppropriateForHiking && Objects.equals(alert, other.alert)
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(hid, other.hid)
				&& Objects.equals(name, other.name);
	}
	
}
