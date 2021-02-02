package ua.training.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;

import ua.training.model.dao.UserDAO;
import ua.training.model.entity.User;
import ua.training.model.enums.Role;

/**
 * This is a DAO for the user entity.
 *
 */
public class JDBCUserDAO implements UserDAO {

	private Connection connection;

	/**
	 * Class constructor with a Connection.
	 * @param connection	a connection to the database.
	 */
	public JDBCUserDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Inserts the user into the database using the data from provided user.
	 * 
	 * @param user	user to insert.
	 */
	@Override
	public void create(User user) {

		try {

			PreparedStatement statement = connection.prepareStatement(DBQueries.INSERT_USER_BY_UNAME_EMAIL_PASS_FNAME_LNAME);
			
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getFirstname());
			statement.setString(5, user.getLastname());

			connection.setAutoCommit(false);
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			LogManager.getLogger(JDBCUserDAO.class).fatal("Failed to insert user");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCUserDAO.class).fatal(e1.getMessage());
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Connection failed");
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Closes the connection to the database.
	 */
	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			LogManager.getLogger(JDBCCertificateDAO.class).warn("Failed to close the connection with the database");
			throw new RuntimeException(e);
		}
	}

	/**
	 * Updates the information about the user in the database.
	 * Uses the username in the provided user as the reference point.
	 * 
	 * @param user	the user to be updated.
	 */
	@Override
	public void update(User user) {
		try {

			PreparedStatement statement = connection.prepareStatement(DBQueries.UPDATE_USER);
			
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getFirstname());
			statement.setString(3, user.getLastname());
			statement.setString(4, user.getUsername());

			connection.setAutoCommit(false);
			statement.execute();
			connection.commit();
			
		} catch (SQLException e) {
			LogManager.getLogger(JDBCUserDAO.class).fatal("Failed to update user");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCUserDAO.class).fatal(e1.getMessage());
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Connection failed");
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Returns a list of all users available in the database.
	 * 
	 */
	@Override
	public List<User> findAll() {
		
		List<User> users = new ArrayList<>();
		ResultSet rs = null;
		
		User user;
		
		try {
			
			PreparedStatement statement = connection.prepareStatement(DBQueries.SELECT_ALL_USERS_WITH_ROLES);
			rs = statement.executeQuery();
			
			while (rs.next()) {
				user = User.builder()
						.username(rs.getString(Constants.USERNAME))
						.role(Role.valueOf(rs.getString(Constants.ROLE).toUpperCase()))
						.email(rs.getString(Constants.EMAIL))
						.firstname(rs.getString(Constants.FIRSTNAME))
						.lastname(rs.getString(Constants.LASTNAME))
						.timeCreated(rs.getString(Constants.CREATE_TIME))
						.id(rs.getInt(Constants.ID))
						.build(); 
					   
				users.add(user);
			}
		} catch (SQLException e) {
			LogManager.getLogger(JDBCUserDAO.class).fatal("Connection failed");
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Connection failed");
				throw new RuntimeException(e);
			}
		}
		return users;
	}
	
	/**
	 * Returns an optional of user that has a provided username and password.
	 * Returns an empty optional if such a user isn't found.
	 * 
	 * @param username	the username of the user
	 * @param password	the password of the user
	 */
	@Override
	public Optional<User> findByUsernameAndPassword(String username, String password) {
		Optional<User> result = Optional.empty();
		ResultSet rs = null;
		User user;
		try {
			PreparedStatement statement = connection.prepareStatement(DBQueries.GET_USER_AND_ROLE_BY_UNAME_AND_PASSWORD);
			statement.setString(1, username);
			statement.setString(2, password);
			rs = statement.executeQuery();
			if (rs.next()) {
				user = User.builder()
						.username(username)
						.role(Role.valueOf(rs.getString(Constants.ROLE).toUpperCase()))
						.email(rs.getString(Constants.EMAIL))
						.firstname(rs.getString(Constants.FIRSTNAME))
						.lastname(rs.getString(Constants.LASTNAME))
						.timeCreated(rs.getString(Constants.CREATE_TIME))
						.build();
				
				result = Optional.of(user);
			}
			
		} catch (SQLException e) {
			LogManager.getLogger(JDBCUserDAO.class).fatal("Connection failed");
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Connection failed");
				throw new RuntimeException(e);
			}
		}
		return result;
	}
	
	
	/**
	 * Returns an optional of user with the provided username.
	 * Returns an empty optional if such a user isn't found.
	 * 
	 * @param username	the username of the user
	 */
	@Override
	public Optional<User> findByUsername(String username) {
		Optional<User> result = Optional.empty();
		ResultSet rs = null;
		User user;
		try {
			PreparedStatement statement = connection.prepareStatement(DBQueries.GET_USER_EMAIL_FIRSTNAME_LASTNAME_BY_UNAME);
			statement.setString(1, username);
			rs = statement.executeQuery();
			if (rs.next()) {
				user = User.builder()
						.username(username)
						.email(rs.getString(Constants.EMAIL))
						.firstname(rs.getString(Constants.FIRSTNAME))
						.lastname(rs.getString(Constants.LASTNAME))
						.build();
				result = Optional.of(user);
			}
		} catch (SQLException e) {
			LogManager.getLogger(JDBCUserDAO.class).warn("User not found");
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Failde to close the result set");
				throw new RuntimeException(e);
			}
		}
		return result;
	}
	
	/**
	 * Assigns the role of "user" to the user with the provided username.
	 */
	@Override
	public void revokeAdminRights (String username) {
		changeRights (username, Constants.USER);
	}
	
	/**
	 * Assigns the role of "admin" to the user with the provided username.
	 */
	@Override
	public void grantAdminRights (String username) {
		changeRights (username, Constants.ADMIN);
	}
	
	/**
	 * Changes the role of the user with the provided username.
	 * 
	 * @param username	the username of the user to be updated.
	 * @param newRole	the new role to be assigned to the user.
	 */
	private void changeRights (String username, String newRole) {
		ResultSet rs = null;
		try {
			PreparedStatement statementForRole = connection.prepareStatement(DBQueries.GET_ROLE_ID);
			PreparedStatement statementForUserChange = connection.prepareStatement(DBQueries.CHANGE_USER_ROLE);
			
			statementForRole.setString(1, newRole);
			statementForUserChange.setString(2, username);
			
			connection.setAutoCommit(false);
			rs = statementForRole.executeQuery();
			if(rs.next()) {
				statementForUserChange.setString(1, rs.getString(Constants.ID));
			}
			statementForUserChange.execute();
			connection.commit();
			if (newRole.equals(Constants.ADMIN)) {
				LogManager.getLogger(JDBCUserDAO.class).info("User with username " + username + " was granted admin rights.");
			} else {
				LogManager.getLogger(JDBCUserDAO.class).info("Admin rights of " + username + " were removed."); 
			}
		} catch (SQLException e) {
			LogManager.getLogger(JDBCUserDAO.class).fatal("Connection failed");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Connection failed");
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Connection failed");
				throw new RuntimeException(e);
			}
			try {
				rs.close();
			} catch (SQLException e) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Connection failed");
				throw new RuntimeException(e);
			}
		}
	}
	
	
	/**
	 * Deletes a user with the provided username from the database if present.
	 */
	public void deleteByUsername (String username) {
		try {
			PreparedStatement statement = connection.prepareStatement(DBQueries.DELETE_USER_BY_UNAME);
			
			statement.setString(1, username);

			statement.execute();
			LogManager.getLogger(JDBCUserDAO.class.getName()).info("Deleted user with the username " + username);
		} catch (SQLException e) {
			LogManager.getLogger(JDBCUserDAO.class).fatal("Failed to delete user. " + e.getMessage());
			throw new RuntimeException(e);
		} 
	}

}
