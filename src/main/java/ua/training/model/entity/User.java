package ua.training.model.entity;

import java.util.EnumSet;

public class User {
	
	private String username;
	
	private String email;
	
	private String password;
	
	private int id;
	
	private String timeCreated;
	
	private String firstname;
	
	private String lastname;
	
	public enum ROLE {
        USER, ADMIN, UNKNOWN
    }

    private ROLE role;
    
    public User() {
    	super();
    }
    
    public User(String username) {
    	this.username = username;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role.toString().toLowerCase();
	}

	public void setRole(String role) {
		EnumSet.allOf(ROLE.class).forEach(r -> {
			if (r.toString().equalsIgnoreCase(role)) {
				this.role = r;
			}
		});
	}

}
