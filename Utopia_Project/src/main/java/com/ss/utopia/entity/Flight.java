package com.ss.utopia.entity;

import java.time.ZonedDateTime;
import java.util.List;

public class Flight {
	
	private Integer id;
	private Integer routeID;
	private Integer airplaneID;
	private String departureDate;
	private String departureTime;
	private Integer reservedSeatsFirstclass;
	private Integer reservedSeatsBusiness;
	private Integer reservedSeatsEconomy;
	private Integer totalSeatsFirstclass;
	private Integer totalSeatsBusiness;
	private Integer totalSeatsEconomy;
	private float seatPriceFirstclass;
	private float seatPriceBusiness;
	private float seatPriceEconomy;
	
	public int getTotalAvailableSeats() {
		int totalSeats = this.totalSeatsFirstclass + this.totalSeatsBusiness + this.totalSeatsEconomy;
		int totalReserved = this.reservedSeatsFirstclass + this.reservedSeatsBusiness + this.reservedSeatsEconomy;
		return totalSeats - totalReserved;
	}
	
	public void reserveSeat(int seatClass) {
		switch(seatClass) {
			case 1: 
				this.setReservedSeatsFirstclass(this.reservedSeatsFirstclass + 1);
				break;
			case 2:
				this.setReservedSeatsBusiness(this.reservedSeatsBusiness + 1);
				break;
			case 3:
				this.setReservedSeatsEconomy(this.reservedSeatsEconomy + 1);
				break;
			default:
				System.out.println("Failed to reserve seat");
		}
	}
	
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
	
	public String getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}


	public Integer getReservedSeatsFirstclass() {
		return reservedSeatsFirstclass;
	}

	public void setReservedSeatsFirstclass(Integer reservedSeatsFirstclass) {
		this.reservedSeatsFirstclass = reservedSeatsFirstclass;
	}

	public Integer getReservedSeatsBusiness() {
		return reservedSeatsBusiness;
	}

	public void setReservedSeatsBusiness(Integer reservedSeatsBusiness) {
		this.reservedSeatsBusiness = reservedSeatsBusiness;
	}

	public Integer getReservedSeatsEconomy() {
		return reservedSeatsEconomy;
	}

	public void setReservedSeatsEconomy(Integer reservedSeatsEconomy) {
		this.reservedSeatsEconomy = reservedSeatsEconomy;
	}

	public Integer getTotalSeatsFirstclass() {
		return totalSeatsFirstclass;
	}

	public void setTotalSeatsFirstclass(Integer totalSeatsFirstclass) {
		this.totalSeatsFirstclass = totalSeatsFirstclass;
	}

	public Integer getTotalSeatsBusiness() {
		return totalSeatsBusiness;
	}

	public void setTotalSeatsBusiness(Integer totalSeatsBusiness) {
		this.totalSeatsBusiness = totalSeatsBusiness;
	}

	public Integer getTotalSeatsEconomy() {
		return totalSeatsEconomy;
	}

	public void setTotalSeatsEconomy(Integer totalSeatsEconomy) {
		this.totalSeatsEconomy = totalSeatsEconomy;
	}

	public float getSeatPriceFirstclass() {
		return seatPriceFirstclass;
	}

	public void setSeatPriceFirstclass(float seatPriceFirstclass) {
		this.seatPriceFirstclass = seatPriceFirstclass;
	}

	public float getSeatPriceBusiness() {
		return seatPriceBusiness;
	}

	public void setSeatPriceBusiness(float seatPriceBusiness) {
		this.seatPriceBusiness = seatPriceBusiness;
	}

	public float getSeatPriceEconomy() {
		return seatPriceEconomy;
	}

	public void setSeatPriceEconomy(float seatPriceEconomy) {
		this.seatPriceEconomy = seatPriceEconomy;
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
