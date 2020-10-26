package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;

public class AnswerFirstQuestionCommand implements Command {
	
	private static final int START_INDEX = 0;
	private static final String ANSWER_QUESTION = "redirect:/user/answer-question.jsp";
	private static final String FINISH_TEST = "redirect:/serv/finish-taking-test";

	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Test test = (Test) session.getAttribute(Constants.TEST_IN_PROGRESS);
		
		Question currentQuestion = test.getQuestions().get(START_INDEX);
		
		int nextIndex = START_INDEX + 1;
		int mark = 0;
		
		if (START_INDEX < test.getSize()) {
			session.setAttribute(Constants.QUESTION, currentQuestion);
			session.setAttribute(Constants.INDEX, nextIndex);
			session.setAttribute(Constants.MARK, mark);
			return ANSWER_QUESTION;
		} 
		
		return FINISH_TEST;
	}

}
