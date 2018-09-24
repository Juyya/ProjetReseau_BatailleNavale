package fr.app.utils;

import java.util.ArrayList;
import java.util.List;

import fr.app.batailleNavale.domain.User;

public class Database {
	
	private static final List<User> users = new ArrayList<>();
	
	static {
		User adminUser = new User("admin", "admin", true);
		
		users.add(adminUser);
	}
	
	public static User findUser(String login, String Password) {
		User res = null;
		for (User u : users) {
			if (u.getLogin().equals(login) && u.getPassword().equals(Password)) {
				res = u;
				break;
			}
		}
		return res;
	}
}
