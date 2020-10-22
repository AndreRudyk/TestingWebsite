package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

public class AddQuestionCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		TestService service = new TestService();
		
		Test test = service.addQuestion(request);
		
		if (test != null) {
			return "redirect:/admin/new-question.jsp";
		}
		
		return "/admin/new-question-invalid.jsp";
	}

}
