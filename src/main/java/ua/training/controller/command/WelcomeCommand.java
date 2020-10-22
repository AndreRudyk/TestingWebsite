package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.User;

public class WelcomeCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute(Constants.USER);
		
		if (user != null) {
			String role = user.getRole().toLowerCase();
			return "redirect:/" + role + "/home.jsp";
		}
		
		return "/registration-username-taken.jsp";	//<-- need to create this page
	}

}
