package com.ss.utopia.entity;

public class Airplane {
	
	private Integer id;
	private Integer typeID;
	private Integer flightID;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getTypeID() {
		return typeID;
	}
	
	public void setType(Integer type) {
		this.typeID = type;
	}
	
	public Integer getFlightID() {
		return flightID;
	}
	
	public void setFlightID(Integer flight) {
		this.flightID = flight;
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
		Airplane other = (Airplane) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
