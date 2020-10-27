package ua.training.model.entity;

import java.util.EnumSet;

/**
 * Represents a user.
 */
public class User {
	
	/**
	 * The user's username.
	 */
	private String username;
	
	/**
	 * The user's email.
	 */
	private String email;
	
	/**
	 * The user's password.
	 */
	private String password;
	
	/**
	 * The user's id.
	 */
	private int id;
	
	/**
	 * The date when user was created.
	 */
	private String timeCreated;
	
	/**
	 * The user's first name.
	 */
	private String firstname;
	
	/**
	 * The user's last name.
	 */
	private String lastname;
	
	/**
	 * The roles that the  user may have.
	 */
	public enum ROLE {
        USER, ADMIN, UNKNOWN
    }

	/**
	 * The user's role.
	 */
    private ROLE role;
    
    /**
	* Class constructor.
	*/
    public User() {
    	super();
    }
    
    /**
	* Class constructor specifying the username of the use.
	*/
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
