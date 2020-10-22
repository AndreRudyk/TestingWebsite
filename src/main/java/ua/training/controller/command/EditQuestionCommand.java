package ua.training.controller.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;
import ua.training.model.entity.builder.TestBuilder;
import ua.training.model.service.TestService;

public class EditQuestionCommand implements Command {
	
	private static final int START_INDEX = 0;

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		int index = START_INDEX;
		
		Integer sessionIndex = (Integer) session.getAttribute(Constants.INDEX);
		List<Question> newQuestions = new ArrayList<>();
		
		if (sessionIndex != null) {
			index = sessionIndex;
			newQuestions = (List<Question>) session.getAttribute(Constants.NEW_QUESTIONS);
		} 
		
		Test test = (Test) session.getAttribute(Constants.TEST);
		
		String newDescription = request.getParameter(Constants.DESCRIPTION);
		String newTime = request.getParameter(Constants.TIME);
		
		
		if (newDescription != null && newTime != null) {
			String newDifficulty = request.getParameter(Constants.DIFFICULTY);
			String newCategory = request.getParameter(Constants.CATEGORY);
			
			TestBuilder builder = new TestBuilder(test);
			builder.setDescription(newDescription)
				   .setTime(Integer.parseInt(newTime))
				   .setDifficulty(newDifficulty)
				   .setCategory(newCategory);
			session.setAttribute("test", test);
		}
		
		TestService service = new TestService();
		
		Question newQuestion = service.getQuestion(request);
		
		if (newQuestion != null) {
			newQuestions.add(newQuestion);
		}
		
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
