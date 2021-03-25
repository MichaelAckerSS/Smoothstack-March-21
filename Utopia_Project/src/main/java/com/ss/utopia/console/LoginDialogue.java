package com.ss.utopia.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ss.utopia.service.AdminService;
import com.ss.utopia.service.EmployeeService;
import com.ss.utopia.service.TravelerService;
import com.ss.utopia.session.Session;

public class LoginDialogue extends Dialogue {
	int role;
	
	public LoginDialogue(Session session) {
		super(session);
		this.role = session.getCurrentUser().getRole();
	}
	
	public void launch() {
		switch(role) {
			case 1:
				AdminService aService = new AdminService();
				this.getSession().setService(aService);
				try {
					Map<String,String> logins = aService.getAdminLogins();
					Scanner sc = this.getSession().getScanner();
					System.out.println("Username:");
					String userIn = sc.next();
					if(!logins.containsKey(userIn)) {
						System.out.println("Username not found.");
						break;
					} else {
						System.out.println("Password for username " + userIn + ":");
						String passIn = sc.next();
						if (logins.get(userIn).equals(passIn)) {
							this.getSession().loginUser(userIn);
							this.getSession().getDialogueManager().launchAdmin();
							break;
						} else {
							System.out.println("Incorrect password.");
							break;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Unable to access users");
				}
				break;
			case 2:
				EmployeeService eService = new EmployeeService();
				this.getSession().setService(eService);
				try {
					Map<String,String> logins = eService.getEmployeeLogins();
					Scanner sc = this.getSession().getScanner();
					System.out.println("Username:");
					String userIn = sc.next();
					if(!logins.containsKey(userIn)) {
						System.out.println("Username not found.");
						break;
					} else {
						System.out.println("Password for username " + userIn + ":");
						String passIn = sc.next();
						if (logins.get(userIn).equals(passIn)) {
							this.getSession().loginUser(userIn);
							this.getSession().getDialogueManager().launchEmployee();
							break;
						} else {
							System.out.println("Incorrect password.");
							break;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Unable to access users");
				}
				break;
			case 3:
				TravelerService tService = new TravelerService();
				this.getSession().setService(tService);
				try {
					Map<String,String> logins = tService.getTravelerLogins();
					Scanner sc = this.getSession().getScanner();
					System.out.println("Username:");
					String userIn = sc.next();
					if(!logins.containsKey(userIn)) {
						System.out.println("Username not found.");
						break;
					} else {
						System.out.println("Password for username " + userIn + ":");
						String passIn = sc.next();
						if (logins.get(userIn).equals(passIn)) {
							this.getSession().loginUser(userIn);
							this.getSession().getDialogueManager().launchTraveler();
							break;
						} else {
							System.out.println("Incorrect password.");
							break;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Unable to access users");
				}
				
			case 4:
				System.out.println("Guest booking is currently unavailable. Please call Utopia Airlines for assistance.");
				break;
			default:
				System.out.println("Error: Invalid user class");
				break;
		}
	}

}
