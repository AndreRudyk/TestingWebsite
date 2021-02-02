package ua.training.model.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;

import ua.training.controller.command.RegistrationCommand;
import ua.training.model.dao.DAOFactory;
import ua.training.model.dao.UserDAO;
import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.User;
import ua.training.model.enums.Role;

/**
 * Service that serves as a link between the servlet and the database for the user class.
 */
public class UserService {

	private DAOFactory daoFactory;
	
	/**
	 * Class constructor.
	 */
	public UserService() {
		this.daoFactory = DAOFactory.getInstance();
	}
	
	/**
	 * Returns an optional of user that has a provided username and password.
	 * Returns an empty optional if such a user isn't found.
	 * 
	 * @param username	the username of the user
	 * @param password	the password of the user
	 */
	public Optional<User> login(String username, String password) {
		Optional<User> result = Optional.empty();
		String encryptedPass = encryptPassword(password);
		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			result = userDAO.findByUsernameAndPassword(username, encryptedPass);
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).warn("An error occured");
			throw new RuntimeException();
		}
		LogManager.getLogger(UserService.class).info("User {} logged in.", username);
		return result;
	}

	/**
	 * Creates a user from the data provided in the request and insert the data into the database.
	 * Returns an empty optional if the user already exists.
	 * 
	 * @param request HttpServletRequest with the data about the user.
	 */
	public Optional<User> register(HttpServletRequest request) {
		
		Optional<User> optUser;
		
		String username = request.getParameter(Constants.USERNAME);
		String encryptedPass = encryptPassword(request.getParameter(Constants.PASSWORD));
		String email = request.getParameter(Constants.EMAIL);
		String firstname = request.getParameter(Constants.FIRSTNAME);
		String lastname = request.getParameter(Constants.LASTNAME);
		
		User newUser = User.builder()
				.username(username)
				.password(encryptedPass)
				.role(Role.USER)
				.email(email)
				.firstname(firstname)
				.lastname(lastname)
				.build();
		
		DAOFactory daoFactory = DAOFactory.getInstance();

		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			userDAO.create(newUser);
			optUser = Optional.of(newUser);
			Logger.getLogger(RegistrationCommand.class.getName())
					.info("New user \"" + username + "\" was registered successfully!");
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to register the user");
			optUser = Optional.empty();
		}
		return optUser;
	}
	
	/**
	 * Returns an encrypted password using MD5 encryption.
	 * 
	 * @param password	password to be encrypted.
	 */
	private String encryptPassword(String password) {
		String encrypted = null;
		String encryption = "MD5";
		try {
			MessageDigest md = MessageDigest.getInstance(encryption);
			md.update(password.getBytes());
			byte[] digest = md.digest();
			encrypted = DatatypeConverter.printHexBinary(digest);
		} catch (NoSuchAlgorithmException e) {
			LogManager.getLogger(UserService.class).fatal("Failed to encypt the password");
			throw new RuntimeException();
		}
		return encrypted;
	}
	
	/**
	 * Returns an optional of user if the user with the provided username exists in the database.
	 * 
	 * @param username	the username of the user to be found.
	 */
	public Optional<User> findByUsername (String username) {
		
		Optional<User> user = Optional.empty();
		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			user = userDAO.findByUsername(username);
		} catch (Exception e) {
			Logger.getLogger(UserService.class.getName()).severe("An error occured");
			throw new RuntimeException();
		}
		return user;
	}
	
	/**
	 * Returns a list of all users from the database with the role "user".
	 */
	public List<User> findAllUsers(){
		List<User> users = new ArrayList<>();
		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			List<User> allRegistered = userDAO.findAll();
			users = allRegistered.stream()
					   .filter(user -> user.getRole().equalsIgnoreCase(Constants.USER))
					   .collect(Collectors.toList());
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to get the users");
			throw new RuntimeException();
		}
		return users;
	}
	
	/**
	 * Returns a list of all users from the database with the role "admin".
	 */
	public List<User> findAllAdmins(){
		List<User> admins = new ArrayList<>();
		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			List<User> allRegistered = userDAO.findAll();
			admins = allRegistered.stream()
								   .filter(user -> user.getRole().equalsIgnoreCase(Constants.ADMIN))
								   .collect(Collectors.toList());
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to get the users");
			throw new RuntimeException();
		}
		return admins;
	}
	
	/**
	 * Updates user information based on the data in the request.
	 * @param request	HttpServletRequest with the data about the user.
	 */
	public void updateUser(HttpServletRequest request) {
		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			HttpSession session = request.getSession();
			
			User user = (User) session.getAttribute(Constants.USER_TO_EDIT);
			
			String email = request.getParameter(Constants.EMAIL);
			String firstname = request.getParameter(Constants.FIRSTNAME);
			String lastname = request.getParameter(Constants.LASTNAME);
			
			if (email != null) {
				user.setEmail(email);
			}
			if (firstname != null) {
				user.setFirstname(firstname);
			}
			if (lastname != null) {
				user.setLastname(lastname);
			}
			userDAO.update(user);
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to update the user");
			throw new RuntimeException();
		}
	}
	
	/**
	 * Deletes a user with the provided username if such exists.
	 * 
	 * @param username	the username of the user to be deleted.
	 */
	public void deleteUser(String username) {
		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			userDAO.deleteByUsername(username);
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to delete the user");
			throw new RuntimeException();
		}
	}
	
	/**
	 * Changes the user's role to "admin".
	 * 
	 * @param username	the username of the user to be updated.
	 */
	public void grantAdminRights(String username) {
		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			userDAO.grantAdminRights(username);
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to update the user");
			throw new RuntimeException();
		}
	}
	
	/**
	 * Changes the user's role to "user".
	 * 
	 * @param username	the username of the user to be updated.
	 */
	public void revokeAdminRights(String username) {
		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			userDAO.revokeAdminRights(username);
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to update the user");
			throw new RuntimeException();
		}
	}
	
}
