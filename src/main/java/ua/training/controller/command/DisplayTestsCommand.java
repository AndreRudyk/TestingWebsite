package ua.training.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class DisplayTestsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		String category = request.getParameter(Constants.CATEGORY);
		String sortBy = request.getParameter(Constants.SORT_BY);
		
		TestService service = new TestService();
		
		if (Constants.ALL.equals(category)) {
			category = null;
		}
		
		List<Test> tests = service.findAllByCategoryAndSorted(category, sortBy);
		
		request.getSession().setAttribute("tests", tests);
		
		return "redirect:/user/all-tests.jsp";
	}

}
