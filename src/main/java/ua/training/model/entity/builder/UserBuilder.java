package ua.training.model.entity.builder;

import ua.training.model.entity.User;

public class UserBuilder {
	
	private User user;
	
	public UserBuilder(User user) {
		this.user = user;
	}
	
	public UserBuilder setUsername(String username) {
		user.setUsername(username);
		return this;
	}

	public UserBuilder setEmail(String email) {
		user.setEmail(email);
		return this;
	}

	public UserBuilder setPassword(String password) {
		user.setPassword(password);
		return this;
	}

	public UserBuilder setId(int id) {
		user.setId(id);
		return this;
	}

	public UserBuilder setTimeCreated(String timeCreated) {
		user.setTimeCreated(timeCreated);
		return this;
	}

	public UserBuilder setFirstname(String firstname) {
		user.setFirstname(firstname);
		return this;
	}

	public UserBuilder setLastname(String lastname) {
		user.setLastname(lastname);
		return this;
	}

	public UserBuilder setRole(String role) {
		user.setRole(role);
		return this;
	}

}
