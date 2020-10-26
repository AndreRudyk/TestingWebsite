package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class AnswerQuestionCommand implements Command {
	
	private static final String ANSWER_QUESTION = "redirect:/user/answer-question.jsp";
	private static final String FINISH_TEST = "redirect:/serv/finish-taking-test";

	@Override
	public String execute(HttpServletRequest request) {

		HttpSession session = request.getSession();

		int index = (int) session.getAttribute(Constants.INDEX);

		Test test = (Test) session.getAttribute(Constants.TEST_IN_PROGRESS);

		int mark = (int) session.getAttribute(Constants.MARK);
		
		evaluateQuestion(test, index, mark, request);

		int nextIndex = index + 1;

		if (index < test.getSize()) {
			Question nextQuestion = test.getQuestions().get(index);
			session.setAttribute("question", nextQuestion);
			session.setAttribute(Constants.INDEX, nextIndex);

			return ANSWER_QUESTION;
		}
		return FINISH_TEST;
	}
	
	private void evaluateQuestion (Test test, int index, int mark, HttpServletRequest request) {
		int previousIndex = index - 1;
		if (previousIndex < test.getSize()) {
			TestService service = new TestService();
			int previousQuestionResult = service.evaluateQuestion(test, previousIndex, request);
			mark += previousQuestionResult;
			request.getSession().setAttribute(Constants.MARK, mark);
		}
	}
	
}
