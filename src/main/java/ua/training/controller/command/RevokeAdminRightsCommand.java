package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

public class RevokeAdminRightsCommand implements Command {
	
	public static final UserService service = new UserService();


	@Override
	public String execute(HttpServletRequest request) {
		
		String username = request.getParameter(Constants.UNAME);
		
		HttpSession session = request.getSession();
		
		User currentUser = (User) session.getAttribute(Constants.USER);
		
		if (!username.equals(currentUser.getUsername())) {
			service.revokeAdminRights(username);
		}

		return "redirect:/serv/find-all-registered";
	}

}
