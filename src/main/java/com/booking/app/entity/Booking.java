package com.booking.app.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.booking.app.util.Utils;

@Entity
@Table(name = "BookingDetails")
public class Booking {

	public enum BookingStatus {
		SUCCESS, FAILED, CANCELLED;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long trailId;
	private LocalDate hikingDate;

	@OneToMany(targetEntity = Hiker.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id_fk", referencedColumnName = "id")
	private Collection<Hiker> hikers = new ArrayList<>();

	@CreationTimestamp
	private Timestamp recordCreateTimestampUtc = new Timestamp(System.currentTimeMillis());
	@UpdateTimestamp
	private Timestamp recordLastUpdateTimestampUtc = new Timestamp(System.currentTimeMillis());

	private BookingStatus status = BookingStatus.SUCCESS;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "trailId", referencedColumnName = "id", updatable = false, insertable = false)
	private Trail bookedTrailDetails;

	private float totalCost;

	Booking() {
	}

	public Booking(Long trailId, LocalDate hikeDate, Collection<Hiker> hikers) {
		this.trailId = trailId;
		this.hikingDate = hikeDate;
		this.hikers = hikers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTrailId() {
		return trailId;
	}

	public void setTrailId(Long trailId) {
		this.trailId = trailId;
	}

	public LocalDate getHikingDate() {
		return hikingDate;
	}

	public void setHikingDate(LocalDate hikingDate) {
		this.hikingDate = hikingDate;
	}

	public Collection<Hiker> getHikers() {
		return hikers;
	}

	public void setHikers(Collection<Hiker> hikers) {
		this.hikers = hikers;
	}

	public Timestamp getRecordCreateTimestampUtc() {
		return recordCreateTimestampUtc;
	}

	public Timestamp getRecordLastUpdateTimestampUtc() {
		return recordLastUpdateTimestampUtc;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = Utils.formatFloat(totalCost);
	}

	public Trail getBookedTrailDetails() {
		return bookedTrailDetails;
	}
	
	public void setBookedTrailDetails(Trail trail) {
		this.bookedTrailDetails = trail;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setFailureStatus() {
		this.status = BookingStatus.FAILED;
	}

	public void cancelBooking() {
		this.status = BookingStatus.CANCELLED;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", trailId=" + trailId + ", hikingDate=" + hikingDate + ", hikersInfo="
				+ hikers + ", recordCreateTimestampUtc=" + recordCreateTimestampUtc
				+ ", recordLastUpdateTimestampUtc=" + recordLastUpdateTimestampUtc + ", status=" + status
				+ ", bookedTrailDetails=" + bookedTrailDetails + ", totalCost=" + totalCost + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookedTrailDetails, hikers, hikingDate, id, recordCreateTimestampUtc,
				recordLastUpdateTimestampUtc, status, totalCost, trailId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		return Objects.equals(bookedTrailDetails, other.bookedTrailDetails) && Objects.equals(hikers, other.hikers)
				&& Objects.equals(hikingDate, other.hikingDate) && Objects.equals(id, other.id)
				&& Objects.equals(recordCreateTimestampUtc, other.recordCreateTimestampUtc)
				&& Objects.equals(recordLastUpdateTimestampUtc, other.recordLastUpdateTimestampUtc)
				&& status == other.status && Float.floatToIntBits(totalCost) == Float.floatToIntBits(other.totalCost)
				&& Objects.equals(trailId, other.trailId);
	}

}
