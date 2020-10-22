package ua.training.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

public class RegistrationCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {

		UserService userService = new UserService();
		
		Optional<User> userOpt = userService.register(request);
		HttpSession session = request.getSession(); 
		
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			session.setAttribute(Constants.USER, user);
			session.setAttribute(Constants.ROLE, Constants.USER);		//<--need to delete this
			LogManager.getLogger(LoginCommand.class).info("New user logged in successfully.");
			return "redirect:/user/home.jsp";
		}
    	
		return "/registration.jsp";
	}

}
