package ua.training.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class EditTestCommand implements Command {
	
	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();

		String name = request.getParameter(Constants.TNAME);

		TestService service = new TestService();

		Optional<Test> test = service.getCompleteTest(name);
		
		if (test.isPresent()) {
			session.setAttribute("test", test.get());
		}
		return "/admin/test-edit-main-info.jsp";
	}

}
