package ua.training.controller.command;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class FinishTestCreationCommand implements Command {

	private static final String DOWNLOAD_DIR = File.separator + "WEB-INF" + File.separator + "tests";

	@Override
	public String execute(HttpServletRequest request) {

		HttpSession session = request.getSession();

		Test test = (Test) session.getAttribute(Constants.TEST);

		TestService service = new TestService();

		if (request.getParameter(Constants.QUESTION) != null) {
			test = service.addQuestion(request);
		}

		session.setAttribute(Constants.TEST, test);

		ServletContext context = session.getServletContext();
		String realContextPath = context.getRealPath("");
		String downloadPath = realContextPath + DOWNLOAD_DIR;
		String filePath = downloadPath + File.separator + test.getName() + ".xml";
		
		test.setLocation(filePath);

		service.saveToXml(test, filePath);
		
		service.addToDatabase(test);
		
		setAttributesToNull(session);

		return "redirect:/admin/test-creation-finished.jsp";
	}
	
	private void setAttributesToNull(HttpSession session) {
		session.setAttribute(Constants.NAME, null);
		session.setAttribute(Constants.DESCRIPTION, null);
		session.setAttribute(Constants.DIFFICULTY, null);
		session.setAttribute(Constants.CATEGORY, null);
		session.setAttribute(Constants.TIME, null);
	}

}
