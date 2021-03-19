package com.ss.utopia.session;

import com.ss.utopia.console.DialogueManager;
import com.ss.utopia.entity.User;

public class Session {
	
	private DialogueManager dialogueManager;
	private User currentUser;
	private int role;
	
	public static void main(String[] args) {
		Session session = new Session();
		session.launchDialogue();
	}
	
	private void launchDialogue() {
		dialogueManager = new DialogueManager(this);
		dialogueManager.launchStartup();
	}
	
	private void initUser() {
		currentUser = new User();
	}
	
	public DialogueManager getDialogueManager() {
		return dialogueManager;
	}
	
	public User getUser() {
		return currentUser;
	}
	
	public int getRole() {
		return role;
	}
	
	public void setRole(int newRole) {
		this.role = newRole;
	}
}
