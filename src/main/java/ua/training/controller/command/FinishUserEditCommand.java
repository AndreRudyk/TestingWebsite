package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.service.UserService;

public class FinishUserEditCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		UserService service = new UserService();
		
		service.updateUser(request);
		
		return "/admin/u-update-finished.jsp";
	}

}
