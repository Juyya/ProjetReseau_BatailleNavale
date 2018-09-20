package fr.app.batailleNavale.domain;

import java.io.Serializable;

public enum UserType implements Serializable {
	
	Admin("ADMIN"),
	User("USER");
	
	
	private final String id;
	
	private UserType (String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
