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
import ua.training.model.entity.builder.UserBuilder;

public class JDBCUserDAO implements UserDAO {

	private Connection connection;

	public JDBCUserDAO(Connection connection) {
		this.connection = connection;
	}

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

	@Override
	public void delete(int id) {
		
		try {

			PreparedStatement statement = connection.prepareStatement(DBQueries.DELETE_USER_BY_ID);
			
			statement.setString(1, Integer.toString(id));

			connection.setAutoCommit(false);
			statement.execute();
			connection.commit();
			LogManager.getLogger(JDBCUserDAO.class.getName()).info("Deleted user with id " + id);
			
		} catch (SQLException e) {
			LogManager.getLogger(JDBCUserDAO.class).fatal("Failed to delete user");
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

	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

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

	@Override
	public List<User> findAll() {
		
		List<User> users = new ArrayList<>();
		ResultSet rs = null;
		
		User user;
		
		UserBuilder builder;
		
		try {
			
			PreparedStatement statement = connection.prepareStatement(DBQueries.SELECT_ALL_USERS_WITH_ROLES);
			rs = statement.executeQuery();
			
			while (rs.next()) {
				user = new User();
				
				builder = new UserBuilder(user);
				
				builder.setUsername(rs.getString(Constants.USERNAME))
					   .setRole(rs.getString(Constants.ROLE))
					   .setEmail(rs.getString(Constants.EMAIL))
					   .setFirstname(rs.getString(Constants.FIRSTNAME))
					   .setLastname(rs.getString(Constants.LASTNAME))
					   .setTimeCreated(rs.getString(Constants.CREATE_TIME))
					   .setId(rs.getInt(Constants.ID)); //<-- need to delete this
					   
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
	
	@Override
	public Optional<User> findByUsernameAndPassword(String username, String password) {
		Optional<User> result = Optional.empty();
		ResultSet rs = null;
		User user = new User();
		UserBuilder builder = new UserBuilder(user);
		try {
			PreparedStatement statement = connection.prepareStatement(DBQueries.GET_USER_AND_ROLE_BY_UNAME_AND_PASSWORD);
			statement.setString(1, username);
			statement.setString(2, password);
			rs = statement.executeQuery();
			if (rs.next()) {
				builder.setUsername(username)
				   .setRole(rs.getString(Constants.ROLE))
				   .setEmail(rs.getString(Constants.EMAIL))
				   .setFirstname(rs.getString(Constants.FIRSTNAME))
				   .setLastname(rs.getString(Constants.LASTNAME))
				   .setTimeCreated(rs.getString(Constants.CREATE_TIME))
				   .setId(rs.getInt(Constants.ID)); //<-- need to delete this
				
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
	
	@Override
	public Optional<User> findByUsername(String username) {
		Optional<User> result = Optional.empty();
		ResultSet rs = null;
		User user = new User();
		UserBuilder builder = new UserBuilder(user);
		try {
			PreparedStatement statement = connection.prepareStatement(DBQueries.GET_USER_EMAIL_FIRSTNAME_LASTNAME_BY_UNAME);
			statement.setString(1, username);
			rs = statement.executeQuery();
			if (rs.next()) {
				builder.setUsername(username)
				   .setEmail(rs.getString(Constants.EMAIL))
				   .setFirstname(rs.getString(Constants.FIRSTNAME))
				   .setLastname(rs.getString(Constants.LASTNAME));
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
	
	@Override
	public void revokeAdminRights (String username) {
		changeRights (username, Constants.USER);
	}
	
	@Override
	public void grantAdminRights (String username) {
		changeRights (username, Constants.ADMIN);
	}
	
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

}
