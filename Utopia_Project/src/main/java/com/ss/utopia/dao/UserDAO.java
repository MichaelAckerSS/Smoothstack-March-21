package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.User;

public class UserDAO extends BaseDAO<User> {

	public UserDAO(Connection conn) {
		super(conn);
	}
	
	public List<User> readRoleUsers(int role) throws ClassNotFoundException, SQLException{
		return read("select * from user where role_id = ?", new Object[] {role});
	}
	
	public List<User> readAllUsers() throws ClassNotFoundException, SQLException{
		return read("select * from user", new Object[] {});
	}
	
	public User readUserFromID(int id) throws ClassNotFoundException, SQLException{
		List<User> list = read("select * from user where id = ?", new Object[] {id});
		return list.get(0);
	}
	
	public void addUser(User u) throws ClassNotFoundException, SQLException {
		save("insert into user values (?,?,?,?,?,?,?,?)", new Object[] {null, u.getRole(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getEmail(), u.getPassword(), u.getPhone()});
	}
	
	public void updateUser(User u) throws ClassNotFoundException, SQLException {
		save("update user set given_name = ?, family_name = ?, username = ?, email = ?, phone = ? where id = ?", new Object[] {u.getFirstName(), u.getLastName(), u.getUserName(),
				u.getEmail(), u.getPhone(), u.getId()});
	}
	
	public void deleteUser(User u) throws ClassNotFoundException, SQLException {
		save("delete from user where id = ?", new Object[] {u.getId()});
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
