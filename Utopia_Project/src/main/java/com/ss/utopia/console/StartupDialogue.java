package com.ss.utopia.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ss.utopia.session.Session;

public class StartupDialogue extends Dialogue {
	
	private DialogueManager dialogueManager;
	
	public StartupDialogue(Session session) {
		super(session);
		dialogueManager = session.getDialogueManager();
	}
	
	public void launch() {
		String options = "Please select:%n1. Administrator%n2. Employee%n3. Traveler%n4. Guest%n";
		System.out.printf("Welcome to Utopia Airlines!%nWhat type of user are you? " + options);
		int response = dialogueManager.getIntResponse(4, options);
			switch(response) {
			case 1: 
				System.out.println("Proceeding to administrator login...");
				break;
			case 2: 
				System.out.println("Proceeding to employee login...");
				break;
			case 3: 
				System.out.println("Proceeding to traveler login...");
				break;
			case 4: 
				System.out.println("Proceeding as guest...");
				break;
			default: 
				System.out.printf("Sorry, something went wrong.");
				System.exit(2);
				break;
			}
		this.getSession().getCurrentUser().setRole(response);
		dialogueManager.launchLogin();
	}

}
