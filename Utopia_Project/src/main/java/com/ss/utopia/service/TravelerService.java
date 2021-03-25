package com.ss.utopia.service;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.BookingUserDAO;
import com.ss.utopia.dao.FlightBookingDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingUser;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBooking;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.User;

public class TravelerService extends Service{
	
	Util util = new Util();
	
	public List<String> getAirportIDs() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			List<Airport> airportList = adao.readAllAirports();
			List<String> IDs = new ArrayList<String>();
			for (Airport a : airportList) {
				String id = a.getId();
				IDs.add(id);
			}
			
			return IDs;
					
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
	
	public Map<String,String> getTravelerLogins() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO udao = new UserDAO(conn);
			List<User> userList = udao.readRoleUsers(3);
			HashMap<String,String> logins = new HashMap<String,String>();
			for (User u : userList) {
				String username = u.getUserName();
				String password = u.getPassword();
				logins.put(username, password);
			}
			
			return logins;
					
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
	

	public List<Flight> getFlightsFromUser(User currentUser) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingUserDAO budao = new BookingUserDAO(conn);
			FlightBookingDAO fbdao = new FlightBookingDAO(conn);
			BookingDAO bdao = new BookingDAO(conn);
			
			List<BookingUser> bookingUsers = budao.readBookingUsersByUserID(currentUser.getId());
			List<FlightBooking> flightBookings = new ArrayList<FlightBooking>();
			for (BookingUser bu : bookingUsers) {
				int bookingID = bu.getBookingID();
				FlightBooking fbooking = fbdao.readFromBookingID(bu.getBookingID());
				Booking booking = bdao.readBookingFromID(bookingID);
				if (booking.getIsActive()) {
					flightBookings.add(fbooking);
				}
			}
			List<Flight> userFlights = new ArrayList<Flight>();
			FlightDAO fdao = new FlightDAO(conn);
			for (FlightBooking fb : flightBookings) {
				Flight flight = fdao.readFlightFromID(fb.getFlightID());
				userFlights.add(flight);
			}
			return userFlights;
					
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

	public void cancelFlight(Flight flight, User user) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			Integer flightID = flight.getId();
			FlightBookingDAO fbdao = new FlightBookingDAO(conn);
			BookingUserDAO budao = new BookingUserDAO(conn);
			BookingDAO bdao = new BookingDAO(conn);
			
			List<FlightBooking> flightBookings = fbdao.readFromFlightID(flightID);
			List<BookingUser> bookingUsers = budao.readBookingUsersByUserID(user.getId());
			int bookingID = 0;
			for (FlightBooking fb : flightBookings) {
				for (BookingUser bu : bookingUsers) {
					if (fb.getBookingID() == bu.getBookingID()) {
						bookingID = fb.getBookingID();
					}
				}
			}
		
			bdao.cancelBookingByID(bookingID);
			conn.commit();
					
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
		}finally{
			if(conn!=null) {
				conn.close();
			}
		}
		
	}

}