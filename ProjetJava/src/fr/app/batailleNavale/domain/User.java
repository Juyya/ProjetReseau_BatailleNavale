package fr.app.batailleNavale.domain;

import java.io.Serializable;

public class User implements Serializable {
	
	private static int CPT_USER = 0;
	
	private final int id;
	
	private final String login;
	
	private final String password;
	
	private final UserType type;
	
	private Click dernierClick;
	

	public User(String login, String password, Boolean isAdmin) {
		super();
		this.id = CPT_USER++;
		this.login = login;
		this.password = password;
		this.type = (isAdmin == false ? UserType.User : UserType.Admin);
		this.dernierClick = null;
	}

	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public UserType getType() {
		return type;
	}

	public Click getDernierClick() {
		return dernierClick;
	}

	public void setDernierClick(Click dernierClick) {
		this.dernierClick = dernierClick;
	}
}
