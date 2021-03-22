package com.ss.utopia.console;

import com.ss.utopia.session.Session;

public abstract class Dialogue {
	
	protected Session session;
	
	
	public Dialogue(Session session) {
		this.session = session;
	}
	
	public abstract void launch();
	
	public Session getSession() {
		return session;
	}

}
