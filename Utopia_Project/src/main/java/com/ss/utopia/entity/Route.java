package com.ss.utopia.entity;

import java.util.List;

public class Route {
	
	private Integer id;
	
	private Airport origin;
	private Airport destination;
	private List<Flight> flights;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Airport getOrigin() {
		return origin;
	}
	
	public void setOrigin(Airport origin) {
		this.origin = origin;
	}
	
	public Airport getDestination() {
		return destination;
	}
	
	public void setDestination(Airport destination) {
		this.destination = destination;
	}
	
	public List<Flight> getFlights() {
		return flights;
	}
	
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
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
		Route other = (Route) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
