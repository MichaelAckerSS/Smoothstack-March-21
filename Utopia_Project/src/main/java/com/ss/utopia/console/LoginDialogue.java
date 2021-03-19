package com.ss.utopia.console;

import com.ss.utopia.session.Session;

public class LoginDialogue {
	int userClass;
	
	public LoginDialogue(Session session) {
		this.userClass = session.getRole();
	}
	
	public void launch() {
		System.out.printf("User role %d. ", userClass);
		System.out.println("Enter your user ID:");
	}

}
