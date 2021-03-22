package com.ss.utopia.session;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.console.DialogueManager;
import com.ss.utopia.entity.User;
import com.ss.utopia.service.Service;

public class Session {
	
	private DialogueManager dialogueManager;
	private User currentUser;
	private Service service;
	private Scanner scanner;
	
	public Session() {
		currentUser = new User();
		scanner = new Scanner(System.in);
		launchDialogue();
	}
	
	private void launchDialogue() {
		dialogueManager = new DialogueManager(this);
		dialogueManager.launchStartup();
	}
	
	public DialogueManager getDialogueManager() {
		return dialogueManager;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public Service getService() {
		return service;
	}
	
	public void setService(Service service) {
		this.service = service;
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	
	public void loginUser(String username) throws SQLException {
		List<User> users = service.getAllUsers();
		for (User u : users) {
			if (u.getUserName().equals(username)){
				currentUser = u;
			}
		}
	}
}
