package com.ss.utopia.entity;

import java.time.ZonedDateTime;
import java.util.List;

public class Flight {
	
	private Integer id;
	private Integer routeID;
	private Integer airplaneID;
	private String departureTime;
	private Integer reservedSeats;
	private float seatPrice;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRouteID() {
		return routeID;
	}
	
	public void setRouteID(Integer route) {
		this.routeID = route;
	}
	
	public Integer getAirplaneID() {
		return airplaneID;
	}
	
	public void setAirplaneID(Integer airplane) {
		this.airplaneID = airplane;
	}
	
	public String getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	public Integer getReservedSeats() {
		return reservedSeats;
	}
	
	public void setReservedSeats(Integer reservedSeats) {
		this.reservedSeats = reservedSeats;
	}
	
	public float getSeatPrice() {
		return seatPrice;
	}
	
	public void setSeatPrice(float seatPrice) {
		this.seatPrice = seatPrice;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
