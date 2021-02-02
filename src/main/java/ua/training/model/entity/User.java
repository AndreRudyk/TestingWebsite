package ua.training.model.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.training.model.enums.Role;

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
    private Role role;
    
    /**
	* Class constructor.
	*/
    @SuppressWarnings("unused")
	private User() {
    	//private constructor
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

	public void setRole(Role role) {
		this.role = role;
	}
	
	private static boolean verifyInput (String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}
	
	public static UserBuilder builder() {
		return new User().new UserBuilder();
	}
	
	/**
	 * A builder for the User class.
	 */
	public class UserBuilder {
		
		private UserBuilder() {
			//a private constructor
		}
		
		public UserBuilder username(String username) {
			User.this.username = username;
			return this;
		}

		public UserBuilder email(String email) {
			User.this.email = email;
			return this;
		}

		public UserBuilder password(String password) {
			User.this.password = password;
			return this;
		}
		
		public UserBuilder id(int id) {
			User.this.id = id;
			return this;
		}

		public UserBuilder timeCreated(String timeCreated) {
			User.this.timeCreated = timeCreated;
			return this;
		}

		public UserBuilder firstname(String firstname) {
			User.this.firstname = firstname;
			return this;
		}

		public UserBuilder lastname(String lastname) {
			User.this.lastname = lastname;
			return this;
		}

		public UserBuilder role(Role role) {
			User.this.role = role;
			return this;
		}
		
		public User build() {
			return User.this;
		}
		
	}
}
