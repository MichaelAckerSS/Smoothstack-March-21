package com.ss.utopia.session;

import com.ss.utopia.console.DialogueManager;
import com.ss.utopia.entity.User;

public class Session {
	
	private DialogueManager dialogueManager;
	private SessionUser currentUser;
	
	public Session() {
		currentUser = new SessionUser();
		launchDialogue();
	}
	
	private void launchDialogue() {
		dialogueManager = new DialogueManager(this);
		dialogueManager.launchStartup();
	}
	
	public DialogueManager getDialogueManager() {
		return dialogueManager;
	}
	
	public SessionUser getCurrentUser() {
		return currentUser;
	}
}
