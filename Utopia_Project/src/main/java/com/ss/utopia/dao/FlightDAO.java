package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ss.utopia.entity.Flight;

public class FlightDAO extends BaseDAO<Flight>{

	public FlightDAO(Connection conn) {
		super(conn);
	}
	
	public void saveFlight(Flight f) throws ClassNotFoundException, SQLException {
		save("insert into flight values (?, ?, ?, ?, ?, ?)", new Object[] {f.getId(), f.getRouteID(), f.getAirplaneID(), f.getDepartureTime(), f.getReservedSeats(), f.getSeatPrice()});
	}
	
	public void updateFlightSeats(Flight f) throws ClassNotFoundException, SQLException {
		save("update flight set reserved_seats = ? where id = ?", new Object[] {f.getReservedSeats(), f.getId()});
	}
	
	public List<Flight> readAllFlights() throws ClassNotFoundException, SQLException {
		return read("select * from flight", new Object[] {});
	}
	
	public Flight readFlightFromID(int id) throws ClassNotFoundException, SQLException {
		List<Flight> list = read("select * from flight where id = ?", new Object[] {id});
		return list.get(0);
	}

	@Override
	public List<Flight> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Flight> flights = new ArrayList<Flight>();
		while(rs.next()) {
			Flight f = new Flight();
			f.setId(rs.getInt("id"));
			f.setRouteID(rs.getInt("route_id"));
			f.setAirplaneID(rs.getInt("airplane_id"));
			f.setDepartureTime(rs.getString("departure_time"));
			f.setReservedSeats(rs.getInt("reserved_seats"));
			f.setSeatPrice(rs.getFloat("seat_price"));
			flights.add(f);
		}
		return flights;
	}

}
