package com.ss.utopia.console;

import com.ss.utopia.session.Session;

public class DialogueManager {
	
	private Session session;
	private StartupDialogue startup;
	private LoginDialogue login;
	
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
	

}
