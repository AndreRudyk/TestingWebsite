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
import ua.training.model.entity.builder.UserBuilder;

public class UserService {

	private DAOFactory daoFactory;
	
	public UserService() {
		this.daoFactory = DAOFactory.getInstance();
	}
	
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

	public Optional<User> register(HttpServletRequest request) {
		User newUser = new User();
		UserBuilder builder = new UserBuilder(newUser);
		Optional<User> optUser = Optional.empty();
		
		String username = request.getParameter(Constants.USERNAME);
		String encryptedPass = encryptPassword(request.getParameter(Constants.PASSWORD));
		String email = request.getParameter(Constants.EMAIL);
		String firstname = request.getParameter(Constants.FIRSTNAME);
		String lastname = request.getParameter(Constants.LASTNAME);
		
		builder.setUsername(username)
			   .setPassword(encryptedPass)
			   .setRole(Constants.USER)
			   .setEmail(email)
			   .setFirstname(firstname)
			   .setLastname(lastname);
		
		DAOFactory daoFactory = DAOFactory.getInstance();

		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			userDAO.create(newUser);
			optUser = Optional.of(newUser);
			Logger.getLogger(RegistrationCommand.class.getName())
					.info("New user \"" + username + "\" was registered successfully!");
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to register the user");
			throw new RuntimeException();
		}
		return optUser;
	}
	
	private String encryptPassword(String password) {
		String encrypted = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			encrypted = DatatypeConverter.printHexBinary(digest);
		} catch (NoSuchAlgorithmException e) {
			LogManager.getLogger(UserService.class).fatal("Failed to encypt the password");
			throw new RuntimeException();
		}
		return encrypted;
	}
	
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
	
	public void deleteUser(int id) {
		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			userDAO.delete(id);
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to update the user");
			throw new RuntimeException();
		}
	}
	
	public void grantAdminRights(String username) {
		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			userDAO.grantAdminRights(username);
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to update the user");
			throw new RuntimeException();
		}
	}
	
	public void revokeAdminRights(String username) {
		try (UserDAO userDAO = daoFactory.createUserDAO()) {
			userDAO.revokeAdminRights(username);
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to update the user");
			throw new RuntimeException();
		}
	}
	
}
