package ua.training.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class StartTestCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		String name = request.getParameter(Constants.TNAME);

		TestService service = new TestService();

		Optional<Test> test = service.getCompleteTest(name);
		
		if (test.isPresent()) {
			request.getSession().setAttribute(Constants.TEST_IN_PROGRESS, test.get());
			return "/user/start-test.jsp";
		}
		return "redirect:/serv/test-not-found.jsp";	//<-- need to create this page
	}

}
