package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Flight;

public class FlightDAO extends BaseDAO<Flight>{

	public FlightDAO(Connection conn) {
		super(conn);
	}
	
	public void saveFlight(Flight f) throws ClassNotFoundException, SQLException {
		save("insert into flight values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new Object[] {f.getId(), f.getRouteID(), f.getAirplaneID(), f.getDepartureDate(), f.getDepartureTime(),
				f.getReservedSeatsFirstclass(), f.getReservedSeatsBusiness(), f.getReservedSeatsEconomy(), f.getTotalSeatsFirstclass(), f.getTotalSeatsBusiness(), 
				f.getTotalSeatsEconomy(), f.getSeatPriceFirstclass(), f.getSeatPriceBusiness(), f.getReservedSeatsEconomy()});
	}
	
	public void updateFlight(Flight f) throws ClassNotFoundException, SQLException {
		save("update flight set route_id = ?, airplane_id = ?, departure_date = ?, departure_time = ?, reserved_seats_firstclass = ?, reserved_seats_business = ?,"
				+ "reserved_seats_economy = ?, seats_firstclass = ?, seats_business = ?, seats_economy = ?, seat_price_firstclass = ?, seat_price_business = ?, seat_price_economy = ?"
				+ " where id = ?", new Object[] {f.getRouteID(), f.getAirplaneID(), f.getDepartureDate(), f.getDepartureTime(),
				f.getReservedSeatsFirstclass(), f.getReservedSeatsBusiness(), f.getReservedSeatsEconomy(), f.getTotalSeatsFirstclass(), f.getTotalSeatsBusiness(), 
				f.getTotalSeatsEconomy(), f.getSeatPriceFirstclass(), f.getSeatPriceBusiness(), f.getReservedSeatsEconomy(), f.getId()});
	}
	
	public void updateReservedSeats(Flight f) throws ClassNotFoundException, SQLException {
		save("update flight set reserved_seats_firstclass = ?, reserved_seats_business = ?, reserved_seats_economy = ? where id = ?", new Object[] {f.getReservedSeatsFirstclass(),
				f.getReservedSeatsBusiness(), f.getReservedSeatsEconomy(), f.getId()});
	}
	
	public List<Flight> readAllFlights() throws ClassNotFoundException, SQLException {
		return read("select * from flight", new Object[] {});
	}
	
	public Flight readFlightFromID(int id) throws ClassNotFoundException, SQLException {
		List<Flight> list = read("select * from flight where id = ?", new Object[] {id});
		return list.get(0);
	}
	
	public void deleteFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("delete from flight where id = ?", new Object[] {flight.getId()});
	}

	@Override
	public List<Flight> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Flight> flights = new ArrayList<Flight>();
		while(rs.next()) {
			Flight f = new Flight();
			f.setId(rs.getInt("id"));
			f.setRouteID(rs.getInt("route_id"));
			f.setAirplaneID(rs.getInt("airplane_id"));
			f.setDepartureDate(rs.getString("departure_date"));
			f.setDepartureTime(rs.getString("departure_time"));
			f.setReservedSeatsFirstclass(rs.getInt("reserved_seats_firstclass"));
			f.setReservedSeatsBusiness(rs.getInt("reserved_seats_business"));
			f.setReservedSeatsEconomy(rs.getInt("reserved_seats_economy"));
			f.setTotalSeatsFirstclass(rs.getInt("seats_firstclass"));
			f.setTotalSeatsBusiness(rs.getInt("seats_business"));
			f.setTotalSeatsEconomy(rs.getInt("seats_economy"));
			f.setSeatPriceFirstclass(rs.getFloat("seat_price_firstclass"));
			f.setSeatPriceBusiness(rs.getFloat("seat_price_business"));
			f.setSeatPriceEconomy(rs.getFloat("seat_price_economy"));
			flights.add(f);
		}
		return flights;
	}

}
