package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Test;
import ua.training.model.entity.User;
import ua.training.model.service.CertificateService;
import ua.training.model.service.TestService;

public class FinishTakingTestCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();

		Test test = (Test) session.getAttribute(Constants.TEST_IN_PROGRESS);

		int mark = (int) session.getAttribute(Constants.MARK);
		
		TestService testService = new TestService();
		
		mark = testService.calculateFinalGrade (mark, test.getSize());
		
		testService.incrementNumberOfRequests(test);
		
		User user = (User) session.getAttribute(Constants.USER);

		CertificateService certificateService = new CertificateService();
		
		certificateService.create(user, test, mark);
		
		session.setAttribute(Constants.MARK, mark);
		session.setAttribute(Constants.FINISHED_TEST_NAME, test.getName());
		
		setAttributesToNull(session);

		return "/user/user-test-finished.jsp";
	}
	
	private void setAttributesToNull(HttpSession session) {
		session.setAttribute(Constants.INDEX, null);
		session.setAttribute(Constants.QUESTION, null);
		session.removeAttribute(Constants.TEST_IN_PROGRESS);
	}

}
