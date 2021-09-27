package com.booking.app.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.booking.app.util.Utils;

@Entity
public class Trail {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50)
	private String name;
	@Column(length = 5)
	private String startAt;
	@Column(length = 5)
	private String endAt;
	@Column(length = 2)
	private int minimumAge;
	@Column(length = 2)
	private int maximumAge;
	@Column(scale = 2)
	private float unitPrice;
	
	Trail() {}
	
	public Trail(String name, String startAt, String endAt, int minAllowedHikerAge, int maxAllowedHikerAge, float unitPrice) {
		this.name = name;
		this.startAt = startAt;
		this.endAt = endAt;
		this.minimumAge = minAllowedHikerAge;
		this.maximumAge = maxAllowedHikerAge;
		this.unitPrice = unitPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartAt() {
		return startAt;
	}

	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}

	public String getEndAt() {
		return endAt;
	}

	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}

	public int getMinimumAge() {
		return minimumAge;
	}

	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}

	public int getMaximumAge() {
		return maximumAge;
	}

	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = Utils.formatFloat(unitPrice);
	}

	@Override
	public String toString() {
		return "Trail [id=" + id + ", name=" + name + ", startAt=" + startAt + ", endAt=" + endAt + ", minimumAge="
				+ minimumAge + ", maximumAge=" + maximumAge + ", unitPrice=" + unitPrice + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(endAt, id, maximumAge, minimumAge, name, startAt, unitPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trail other = (Trail) obj;
		return Objects.equals(endAt, other.endAt) && Objects.equals(id, other.id) && maximumAge == other.maximumAge
				&& minimumAge == other.minimumAge && Objects.equals(name, other.name)
				&& Objects.equals(startAt, other.startAt)
				&& Float.floatToIntBits(unitPrice) == Float.floatToIntBits(other.unitPrice);
	}

}
