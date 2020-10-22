package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;

public class AnswerFirstQuestionCommand implements Command {
	
	private static final int START_INDEX = 0;

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
			return "/user/answer-question.jsp";
		} 
		
		return "redirect:/serv/finish-taking-test";
	}

}
