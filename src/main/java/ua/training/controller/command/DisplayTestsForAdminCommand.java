package ua.training.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class DisplayTestsForAdminCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		TestService service = new TestService();

		List<Test> tests = service.findAllTests();
		
		request.getSession().setAttribute("tests", tests);
		
		return "redirect:/admin/all-tests.jsp";
	}

}
