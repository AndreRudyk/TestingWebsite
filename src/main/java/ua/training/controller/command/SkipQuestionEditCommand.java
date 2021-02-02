package ua.training.controller.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;

@SuppressWarnings("unchecked")
public class SkipQuestionEditCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();

		int index = (int) session.getAttribute(Constants.INDEX);

		List<Question>newQuestions = (List<Question>) session.getAttribute(Constants.NEW_QUESTIONS);
		
		if(newQuestions == null) {
			newQuestions = new ArrayList<Question>();
		}

		Test test = (Test) session.getAttribute(Constants.TEST);
		
		int indexOfPrevoius = index - 1;
		newQuestions.add(test.getQuestions().get(indexOfPrevoius));

		if (index < test.getSize()) {
			Question question = test.getQuestions().get(index);
			session.setAttribute(Constants.QUESTION, question);
			index++;
			session.setAttribute(Constants.INDEX, index);
			session.setAttribute(Constants.NEW_QUESTIONS, newQuestions);
			return "/admin/edit-question.jsp";
		}

		return "/serv/finish-test-edit";
	}

}
