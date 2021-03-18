package com.ss.utopia.entity;

import java.util.List;

public abstract class Booking {
	
	private Integer id;
	private Boolean isActive;
	private String confirmationCode;
	
	private List<FlightBooking> flightBookings;
	private List<Passenger> passengers;
	private BookingPayment payment;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getConfirmationCode() {
		return confirmationCode;
	}
	
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}
	
	public List<FlightBooking> getFlightBookings() {
		return flightBookings;
	}
	
	public void setFlightBookings(List<FlightBooking> flightBookings) {
		this.flightBookings = flightBookings;
	}
	
	public List<Passenger> getPassengers() {
		return passengers;
	}
	
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	public BookingPayment getPayment() {
		return payment;
	}
	
	public void setPayment(BookingPayment payment) {
		this.payment = payment;
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
		Booking other = (Booking) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
