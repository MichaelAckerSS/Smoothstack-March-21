package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.BookingUserDAO;
import com.ss.utopia.dao.FlightBookingDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingUser;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBooking;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.User;

//Copied from Pramod. Read and understand line by line.

public class AdminService extends Service{
	
	Util util = new Util();
	
	public String addFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.saveFlight(flight);
			conn.commit();
			return "Flight added successfully";
					
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Flight could not be added";
		}finally{
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	public String updateFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.updateFlight(flight);
			conn.commit();
			return "Flight updated successfully";
					
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Flight could not be updated";
		}finally{
			if(conn!=null) {
				conn.close();
			}
		}
	}

	public Map<String, String> getAdminLogins() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO udao = new UserDAO(conn);
			List<User> userList = udao.readRoleUsers(1);
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
	
	public List<Route> getAllRoutes() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			RouteDAO rdao = new RouteDAO(conn);
			List<Route> routes = rdao.readAllRoutes();
			return routes;
					
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
	
	public List<Airplane> getAllAirplanes() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirplaneDAO adao = new AirplaneDAO(conn);
			List<Airplane> planes = adao.readAllAirplanes();
			return planes;
					
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
	
	public List<Airport> getAllAirports() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			List<Airport> airports = adao.readAllAirports();
			return airports;
					
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
	
	public int addRoute(Route route) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			RouteDAO rdao = new RouteDAO(conn);
			int id = rdao.addRoute(route);
			conn.commit();
			return id;
					
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
	
	public void addAirport(Airport airport) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.addAirport(airport);
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
	
	public void deleteFlight(Flight flight) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			FlightBookingDAO fbdao = new FlightBookingDAO(conn);
			BookingDAO bdao = new BookingDAO(conn);
			List<FlightBooking> fbs = fbdao.readFromFlightID(flight.getId());
			for (FlightBooking fb : fbs) {
				bdao.cancelBookingByID(fb.getBookingID());
			}
			fdao.deleteFlight(flight);
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
	
	public List<Booking> getAllBookings() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			return bdao.readAllBookings();
			
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
	
	public User getUserFromBookingID(int id) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingUserDAO budao = new BookingUserDAO(conn);
			UserDAO udao = new UserDAO(conn);
			BookingUser bu = budao.readBookingUsersByBookingID(id);
			User user = udao.readUserFromID(bu.getUserID());
			return user;
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
	
	public Flight getFlightFromBookingID(int id) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			FlightBookingDAO fbdao = new FlightBookingDAO(conn);
			FlightDAO fdao = new FlightDAO(conn);
			FlightBooking fb = fbdao.readFromBookingID(id);
			Flight flight = fdao.readFlightFromID(fb.getFlightID());
			return flight;
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
	
	public void deleteBooking(Booking b) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			bdao.deleteBooking(b);
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
	
	public void updateBooking(Booking b) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			bdao.updateBooking(b);
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
	
	public void addBooking(Booking b) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			bdao.addBooking(b);
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
	
	public void deleteAirport(Airport a) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.deleteAirport(a);
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
	
	public void updateAirport(Airport a) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.updateAirport(a);
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
	
	public void addUser(User u) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.addUser(u);
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
	
	public void updateUser(User u) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.updateUser(u);
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
	
	public void deleteUser(User u) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.deleteUser(u);
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