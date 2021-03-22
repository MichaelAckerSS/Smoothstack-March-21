package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.User;

public class UserDAO extends BaseDAO {

	public UserDAO(Connection conn) {
		super(conn);
	}
	
	public List<User> readRoleUsers(int role) throws ClassNotFoundException, SQLException{
		return read("select * from user where role_id = ?", new Object[] {role});
	}
	
	public List<User> readAllUsers() throws ClassNotFoundException, SQLException{
		return read("select * from user", new Object[] {});
	}
	
	@Override
	public List<User> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<User> users = new ArrayList<User>();
		while(rs.next()) {
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setRole(rs.getInt("role_id"));
			u.setFirstName(rs.getString("given_name"));
			u.setLastName(rs.getString("family_name"));
			u.setEmail(rs.getString("email"));
			u.setUserName(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setPhone(rs.getString("phone"));
			users.add(u);
		}
		return users;
	}

}
