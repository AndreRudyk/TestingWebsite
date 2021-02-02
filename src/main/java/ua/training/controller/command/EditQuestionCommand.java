package ua.training.controller.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class EditQuestionCommand implements Command {

//	private static final int START_INDEX = 0;
//	private static final String DEFAULT = "default";

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request) {

		HttpSession session = request.getSession();

//		int index = START_INDEX;

		Integer index = (Integer) session.getAttribute(Constants.INDEX);
		
		List<Question> newQuestions = (List<Question>) session.getAttribute(Constants.NEW_QUESTIONS);
		if (newQuestions == null) {
			newQuestions = new ArrayList<>();
		}
//		List<Question> newQuestions = new ArrayList<>();

//		if (sessionIndex != null) {
//			index = sessionIndex;
//			newQuestions = (List<Question>) session.getAttribute(Constants.NEW_QUESTIONS);
//		}

		Test test = (Test) session.getAttribute(Constants.TEST);

//		String newDescription = request.getParameter(Constants.DESCRIPTION);
//		String newTime = request.getParameter(Constants.TIME);
//		String newDifficulty = request.getParameter(Constants.DIFFICULTY);
//		String newCategory = request.getParameter(Constants.CATEGORY);
//
//		if (newDescription != null) {
//			test.setDescription(newDescription);
//		}
//
//		if (newTime != null) {
//			test.setTime(Integer.parseInt(newTime));
//		}
//
//		if (!newDifficulty.contains(DEFAULT)) {
//			test.setDifficulty(TestDifficulty.valueOf(newDifficulty.toUpperCase()));
//		}
//
//		if (!newCategory.contains(DEFAULT)) {
//			test.setCategory(TestCategory.valueOf(newCategory.toUpperCase()));
//		}
//
//		session.setAttribute("test", test);

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
