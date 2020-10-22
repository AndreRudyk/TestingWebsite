package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.service.TestService;

public class DeleteTestCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {

		String name = request.getParameter("tname");

		TestService service = new TestService();

		service.deleteTest(name);
		
		return "/serv/find-all-tests";
	}

}
