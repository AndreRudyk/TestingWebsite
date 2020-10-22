package ua.training.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

@SuppressWarnings("unchecked")
public class FinishTestEditCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Test test = (Test) session.getAttribute(Constants.TEST);
		
		TestService service = new TestService();
		test = service.updateMainInfo(test, request);
		service.updateTest(test);
		
		List<Question> newQuestions = (List<Question>) session.getAttribute(Constants.NEW_QUESTIONS);

		if (newQuestions != null) {
			test.setQuestions(newQuestions);
			service.saveToXml(test, test.getLocation());
			session.removeAttribute(Constants.NEW_QUESTIONS);
			session.removeAttribute(Constants.INDEX);
		}
		return "redirect:/admin/test-update-finished.jsp";
	}

}
