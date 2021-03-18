package com.ss.utopia.entity;

import java.time.ZonedDateTime;
import java.util.List;

public class Flight {
	
	private Integer id;
	private Route route;
	private Airplane airplane;
	private ZonedDateTime departureTime;
	private Integer reservedSeats;
	private float seatPrice;
	private List<FlightBooking> flightBookings;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Route getRoute() {
		return route;
	}
	
	public void setRoute(Route route) {
		this.route = route;
	}
	
	public Airplane getAirplane() {
		return airplane;
	}
	
	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}
	
	public ZonedDateTime getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(ZonedDateTime departureTime) {
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
	
	public List<FlightBooking> getFlightBookings() {
		return flightBookings;
	}
	
	public void setFlightBookings(List<FlightBooking> flightBookings) {
		this.flightBookings = flightBookings;
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
