package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class NoAccessCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		return "redirect:/no-access.jsp";
	}

}
