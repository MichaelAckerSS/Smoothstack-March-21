package com.ss.utopia.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ss.utopia.session.Session;

public class DialogueManager {
	
	private Session session;
	private StartupDialogue startup;
	private LoginDialogue login;
	private TravelerDialogue traveler;
	private AdminDialogue admin;
	private EmployeeDialogue employee;
	
	public DialogueManager(Session session) {
		this.session = session;
	}
	
	public void launchStartup() {
		startup = new StartupDialogue(session);
		startup.launch();
	}
	
	public void launchLogin() {
		login = new LoginDialogue(session);
		login.launch();
	}
	
	public void launchTraveler() {
		traveler = new TravelerDialogue(session);
		traveler.launch();
	}
	
	public void launchAdmin() {
		admin = new AdminDialogue(session);
		admin.launch();
	}
	
	public void launchEmployee() {
		employee = new EmployeeDialogue(session);
		employee.launch();
	}
	
	public int getIntResponse(int max, String options) {
		Scanner in = session.getScanner();
		Boolean valid = false;
		int response = 0;
		while(!valid) {
			try {
			response = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.printf("Input must be an integer! " + options);
				in.next();
				continue;
			}
			if (response < 1 || response > max) {
				System.out.printf("Invalid selection! " + options);
				continue;
			} else {
				valid = true;
			}
		}
		return response;
	}

}
