package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.service.UserService;

public class DeleteUserCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		
		int userId = Integer.parseInt(request.getParameter("uid"));
		
		UserService service = new UserService();
		
		service.deleteUser(userId);
		
		return "redirect:/serv/find-all-registered";
	}

}
