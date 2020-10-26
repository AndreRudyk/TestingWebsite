package ua.training.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String username = request.getParameter(Constants.USERNAME);
		String password = request.getParameter(Constants.PASSWORD);
		
		if(username == null || username.equals("") || password == null || password.equals("")  ){
            return "/login-invalid.jsp";
        }
		
		UserService userService = new UserService();
		
		Optional<User> userOpt = userService.login(username, password);
		
		if (userOpt.isPresent()) {
			HttpSession session = request.getSession();
			User user = userOpt.get();
			String role = user.getRole().toLowerCase();
			
			session.setAttribute(Constants.USER, user);
			LogManager.getLogger(LoginCommand.class).info("User \"" + username + "\" logged in.");
			return "redirect:/" + role + "/home.jsp";
		}
		return "/login-invalid.jsp";
	}

}
