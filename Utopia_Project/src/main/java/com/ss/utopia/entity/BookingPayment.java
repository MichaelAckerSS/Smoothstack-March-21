package com.ss.utopia.entity;

public class BookingPayment {
	
	private String stripeID;
	private Boolean refunded;
	private Booking booking;
	
	public String getStripeID() {
		return stripeID;
	}
	
	public void setStripeID(String stripeID) {
		this.stripeID = stripeID;
	}
	
	public Boolean getRefunded() {
		return refunded;
	}
	
	public void setRefunded(Boolean refunded) {
		this.refunded = refunded;
	}
	
	public Booking getBooking() {
		return booking;
	}
	
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}
