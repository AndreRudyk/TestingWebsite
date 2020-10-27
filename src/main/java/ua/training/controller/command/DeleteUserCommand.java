package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

public class DeleteUserCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		
		String username = request.getParameter(Constants.UNAME);
		
		User user = (User) request.getSession().getAttribute(Constants.USER);
		
		String currentUsername = user.getUsername();
		
		if (!currentUsername.contentEquals(username)) {
			UserService service = new UserService();
			
			service.deleteUser(username);
		}
		
		return "redirect:/serv/find-all-registered";
	}

}
