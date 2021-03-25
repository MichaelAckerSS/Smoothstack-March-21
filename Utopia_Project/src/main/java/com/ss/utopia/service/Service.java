package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.BookingUserDAO;
import com.ss.utopia.dao.FlightBookingDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.AirplaneType;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingUser;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBooking;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.User;

public abstract class Service {
	
	Util util = new Util();
	
	public String bookFlight(Flight flight, User user, int seatClass) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			FlightBookingDAO fbdao = new FlightBookingDAO(conn);
			BookingDAO bdao = new BookingDAO(conn);
			BookingUserDAO budao = new BookingUserDAO(conn);
			
		    String confCode = util.genConfCode();
		    
		    Booking booking = new Booking();
		    booking.setConfirmationCode(confCode);
		    booking.setIsActive(true);
		    booking.setSeatClass(seatClass);
		    int bookingID = bdao.addBooking(booking);
		    
		    BookingUser bookingUser = new BookingUser();
		    bookingUser.setUserID(user.getId());
			bookingUser.setBookingID(bookingID);
			budao.addBookingUser(bookingUser);
			
			flight.reserveSeat(seatClass);
			fdao.updateReservedSeats(flight);
			
			FlightBooking fb = new FlightBooking();
			fb.setFlightID(flight.getId());
			fb.setBookingID(bookingID);
			fbdao.addFlightBooking(fb);
			
			conn.commit();
			return confCode;
					
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return null;
		}finally{
			if(conn!=null) {
				conn.close();
			}
		}
	}

	public List<User> getAllUsers() throws SQLException {
		Connection conn = null;
		Util util = new Util();
		try {
			conn = util.getConnection();
			UserDAO udao = new UserDAO(conn);
			List<User> users = udao.readAllUsers();
			return users;
					
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return null;
		}finally{
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	public int getCapacity(Flight flight) throws SQLException {
		Connection conn = null;
		Util util = new Util();
		try {
			conn = util.getConnection();
			AirplaneDAO adao = new AirplaneDAO(conn);
			AirplaneTypeDAO tdao = new AirplaneTypeDAO(conn);
			Airplane plane = adao.getAirplaneByID(flight.getAirplaneID());
			AirplaneType type = tdao.getAirplaneTypeByID(plane.getTypeID());
			Integer capacity = type.getCapacity();
			return capacity;
					
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return 0;
		}finally{
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	public List<Flight> getAllFlights() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			List<Flight> flightList = fdao.readAllFlights();	
			return flightList;
					
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return null;
		}finally{
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	public Route getRouteFromID(int id) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			RouteDAO rdao = new RouteDAO(conn);
			List<Route> flightList = rdao.readRouteFromID(id);
			Route r = flightList.get(0);
			return r;
					
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return null;
		}finally{
			if(conn!=null) {
				conn.close();
			}
		}
	}
}
