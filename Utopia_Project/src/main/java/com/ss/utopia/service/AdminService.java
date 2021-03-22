package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.entity.Flight;
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

}