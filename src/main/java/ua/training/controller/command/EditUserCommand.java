package ua.training.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

public class EditUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {

		UserService userService = new UserService();
		
		String username = request.getParameter(Constants.UNAME);
		
		Optional<User> userOpt = userService.findByUsername(username);
		
		if (userOpt.isPresent()) {
			request.getSession().setAttribute(Constants.USER_TO_EDIT, userOpt.get());
		}
    	
		return "/admin/edit-u.jsp";
	}

}
