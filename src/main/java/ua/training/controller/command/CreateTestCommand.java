package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class CreateTestCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		
		TestService testService = new TestService();
		
		HttpSession session = request.getSession();
		
		String name = request.getParameter(Constants.NAME);
		
		if (testService.isNameAvailable(name)) {
			session.setAttribute(Constants.NAME, null);
			session.setAttribute(Constants.DESCRIPTION, null);
			session.setAttribute(Constants.DIFFICULTY, null);
			session.setAttribute(Constants.CATEGORY, null);
			session.setAttribute(Constants.TIME, null);
			
			Test test = testService.createTest(request);
			
			session.setAttribute(Constants.TEST, test);
			
			return "redirect:/admin/new-question.jsp";
		}
		
		session.setAttribute(Constants.NAME, name);
		session.setAttribute(Constants.DESCRIPTION, request.getParameter(Constants.DESCRIPTION));
		session.setAttribute(Constants.DIFFICULTY, request.getParameter(Constants.DIFFICULTY));
		session.setAttribute(Constants.CATEGORY, request.getParameter(Constants.CATEGORY));
		session.setAttribute(Constants.TIME, request.getParameter(Constants.TIME));
		
		return "/admin/create-test-name-taken.jsp";
	}
	
}
