package ua.training.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class PreviewTestCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {

		String name = request.getParameter(Constants.TNAME);

		TestService service = new TestService();

		Optional<Test> test = service.getCompleteTest(name);
		
		if (test.isPresent()) {
			request.getSession().setAttribute(Constants.TEST, test.get());
			return "/admin/test-preview.jsp";
		}
		return "redirect:/serv/find-all-tests";
	}

}
