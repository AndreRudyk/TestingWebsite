package ua.training.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Test;
import ua.training.model.entity.User;
import ua.training.model.service.TestService;

public class DisplayTestsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		String category = request.getParameter(Constants.CATEGORY);
		String sortBy = request.getParameter(Constants.SORT_BY);
		
		TestService service = new TestService();
		
		if (Constants.ALL.equals(category)) {
			category = null;
		}
		
		List<Test> tests = service.findAllByCategoryAndSorted(category, sortBy);
		
		session.setAttribute("tests", tests);
		
		User user = (User) session.getAttribute(Constants.USER);
		
		String path = "redirect:/role/all-tests.jsp";
		path = path.replaceAll(Constants.ROLE, user.getRole());
		
		return path;
	}

}
