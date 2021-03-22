package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingUser;
import com.ss.utopia.entity.FlightBooking;

public class FlightBookingDAO extends BaseDAO<FlightBooking>{

	public FlightBookingDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public void addFlightBooking(FlightBooking fb) throws ClassNotFoundException, SQLException {
		save("insert into flight_bookings values (?, ?)", new Object[] {fb.getFlightID(), fb.getBookingID()});
	}
	
	public FlightBooking readFromBookingID(Integer id) throws ClassNotFoundException, SQLException{
		List<FlightBooking> list = read("select * from flight_bookings where booking_id = ?", new Object[] {id});
		return list.get(0);
	}
	
	public List<FlightBooking> readFromFlightID(Integer id) throws ClassNotFoundException, SQLException{
		return read("select * from flight_bookings where flight_id = ?", new Object[] {id});
	}

	@Override
	public List<FlightBooking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<FlightBooking> bookings = new ArrayList<FlightBooking>();
		while(rs.next()) {
			FlightBooking b = new FlightBooking();
			b.setFlightID(rs.getInt("flight_id"));
			b.setBookingID(rs.getInt("booking_id"));
			bookings.add(b);
		}
		return bookings;
	}

}
