package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Test;
import ua.training.model.enums.TestCategory;
import ua.training.model.enums.TestDifficulty;

public class StartTestEditCommand implements Command {

	private static final String DEFAULT = "default";

	@Override
	public String execute(HttpServletRequest request) {

		HttpSession session = request.getSession();

		Test test = (Test) session.getAttribute(Constants.TEST);

		String newDescription = request.getParameter(Constants.DESCRIPTION);
		String newTime = request.getParameter(Constants.TIME);
		String newDifficulty = request.getParameter(Constants.DIFFICULTY);
		String newCategory = request.getParameter(Constants.CATEGORY);

		if (newDescription != null) {
			test.setDescription(newDescription);
		}

		if (newTime != null) {
			test.setTime(Integer.parseInt(newTime));
		}

		if (!newDifficulty.contains(DEFAULT)) {
			test.setDifficulty(TestDifficulty.valueOf(newDifficulty.toUpperCase()));
		}

		if (!newCategory.contains(DEFAULT)) {
			test.setCategory(TestCategory.valueOf(newCategory.toUpperCase()));
		}

		session.setAttribute("test", test);

		if (0 < test.getSize()) {
			session.setAttribute(Constants.INDEX, 1);
			session.setAttribute(Constants.QUESTION, test.getQuestions().get(0));
			return "/admin/edit-question.jsp";
		}

		return "/serv/finish-test-edit";
	}

}
