package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class AnswerQuestionCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {

		HttpSession session = request.getSession();

		int index = (int) session.getAttribute(Constants.INDEX);

		Test test = (Test) session.getAttribute(Constants.TEST_IN_PROGRESS);

		int mark = (int) session.getAttribute(Constants.MARK);

		TestService service = new TestService();

		int previousIndex = index - 1;
		if (previousIndex < test.getSize()) {
			int previousQuestionResult = service.evaluateQuestion(test, previousIndex, request);
			mark += previousQuestionResult;
			session.setAttribute(Constants.MARK, mark);
		}

		int nextIndex = index + 1;

		if (index < test.getSize()) {
			Question nextQuestion = test.getQuestions().get(index);
			session.setAttribute("question", nextQuestion);
			session.setAttribute(Constants.INDEX, nextIndex);

			return "redirect:/user/answer-question.jsp";
		}

		return "redirect:/serv/finish-taking-test";
	}

}
