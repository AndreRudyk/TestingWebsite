package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;

public class DeleteQuestionCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();

		int index = (int) session.getAttribute(Constants.INDEX);

		Test test = (Test) session.getAttribute(Constants.TEST);

		if (index < test.getSize()) {
			Question question = test.getQuestions().get(index);
			session.setAttribute(Constants.QUESTION, question);
			index++;
			session.setAttribute(Constants.INDEX, index);
			return "/admin/edit-question.jsp";
		}

		return "/serv/finish-test-edit";
	}

}
