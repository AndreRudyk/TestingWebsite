package ua.training.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

public class FindAllUsersCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		UserService service = new UserService();
		
		List<User> users = service.findAllUsers();
		
		session.setAttribute("users", users);
		
		List<User> admins = service.findAllAdmins();
		
		session.setAttribute("admins", admins);
		
		return "redirect:/admin/all-registered.jsp";
	}

}
