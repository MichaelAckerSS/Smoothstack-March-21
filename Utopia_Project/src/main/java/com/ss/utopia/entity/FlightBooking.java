package com.ss.utopia.entity;

public class FlightBooking {
	
	private Integer flightID;
	private Integer bookingID;
	
	public Integer getFlightID() {
		return flightID;
	}
	public void setFlightID(Integer flight) {
		this.flightID = flight;
	}
	public Integer getBookingID() {
		return bookingID;
	}
	public void setBookingID(Integer booking) {
		this.bookingID = booking;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingID == null) ? 0 : bookingID.hashCode());
		result = prime * result + ((flightID == null) ? 0 : flightID.hashCode());
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
		FlightBooking other = (FlightBooking) obj;
		if (bookingID == null) {
			if (other.bookingID != null)
				return false;
		} else if (!bookingID.equals(other.bookingID))
			return false;
		if (flightID == null) {
			if (other.flightID != null)
				return false;
		} else if (!flightID.equals(other.flightID))
			return false;
		return true;
	}
	
}
