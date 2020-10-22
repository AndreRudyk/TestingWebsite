package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.service.UserService;

public class GrantAdminRightsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		String username = request.getParameter("uname");

		UserService service = new UserService();

		service.grantAdminRights(username);

		return "redirect:/serv/find-all-registered";
	}

}
