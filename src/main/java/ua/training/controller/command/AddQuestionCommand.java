package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class AddQuestionCommand implements Command {
	
	private static final String NEW_QUESTION = "redirect:/admin/new-question.jsp";
	private static final String NEW_QUESTION_INVALID = "redirect:/admin/new-question-invalid.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		
		TestService service = new TestService();
		
		Test test = service.addQuestion(request);
		
		if (test != null) {
			return NEW_QUESTION;
		}
		
		return NEW_QUESTION_INVALID;
	}

}
