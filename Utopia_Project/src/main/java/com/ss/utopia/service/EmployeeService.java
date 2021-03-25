package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.User;

public class EmployeeService extends Service {
	
	public Map<String, String> getEmployeeLogins() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			UserDAO udao = new UserDAO(conn);
			List<User> userList = udao.readRoleUsers(2);
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

}
