package com.ss.utopia.session;

public class SessionUser {
	
	private int role;
	private String username;
	
	public void setRole(int newRole) {
		this.role = newRole;
	}
	
	public int getRole() {
		return role;
	}
	
	public void setUsername(String name) {
		this.username = name;
	}
	
	public String getUsername() {
		return username;
	}
}
