package ua.training.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class EditTestCommand implements Command {
	
	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();

		String name = request.getParameter("tname");

		TestService service = new TestService();

		Optional<Test> testOptional = service.getCompleteTest(name);
		
		if (testOptional.isPresent()) {
			Test test = testOptional.get();
			session.setAttribute("test", test);
		}
		return "/admin/test-edit-main-info.jsp";
	}

}
