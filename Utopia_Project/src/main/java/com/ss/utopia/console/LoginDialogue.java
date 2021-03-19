package com.ss.utopia.console;

import com.ss.utopia.session.Session;

public class LoginDialogue {
	int role;
	
	public LoginDialogue(Session session) {
		this.role = session.getCurrentUser().getRole();
	}
	
	public void launch() {
		System.out.printf("User role %d. ", role);
		System.out.println("Enter your user ID:");
	}

}
