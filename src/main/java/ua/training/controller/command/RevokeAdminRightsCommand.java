package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.dao.impl.Constants;
import ua.training.model.service.UserService;

public class RevokeAdminRightsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		String username = request.getParameter(Constants.UNAME);

		UserService service = new UserService();

		service.revokeAdminRights(username);

		return "redirect:/serv/find-all-registered";
	}

}
