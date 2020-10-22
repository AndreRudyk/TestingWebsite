package ua.training.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.training.controller.command.AddQuestionCommand;
import ua.training.controller.command.AnswerFirstQuestionCommand;
import ua.training.controller.command.AnswerQuestionCommand;
import ua.training.controller.command.Command;
import ua.training.controller.command.CreateTestCommand;
import ua.training.controller.command.DeleteQuestionCommand;
import ua.training.controller.command.DeleteTestCommand;
import ua.training.controller.command.DeleteUserCommand;
import ua.training.controller.command.DisplayCertificatesCommand;
import ua.training.controller.command.DisplayTestsCommand;
import ua.training.controller.command.DisplayTestsForAdminCommand;
import ua.training.controller.command.EditQuestionCommand;
import ua.training.controller.command.EditTestCommand;
import ua.training.controller.command.EditUserCommand;
import ua.training.controller.command.ExceptionCommand;
import ua.training.controller.command.FindAllUsersCommand;
import ua.training.controller.command.FinishTakingTestCommand;
import ua.training.controller.command.FinishTestCreationCommand;
import ua.training.controller.command.FinishTestEditCommand;
import ua.training.controller.command.FinishUserEditCommand;
import ua.training.controller.command.GrantAdminRightsCommand;
import ua.training.controller.command.LoginCommand;
import ua.training.controller.command.LogoutCommand;
import ua.training.controller.command.NoAccessCommand;
import ua.training.controller.command.PreviewTestCommand;
import ua.training.controller.command.RegistrationCommand;
import ua.training.controller.command.RevokeAdminRightsCommand;
import ua.training.controller.command.SkipQuestionEditCommand;
import ua.training.controller.command.StartTestCommand;
import ua.training.controller.command.WelcomeCommand;

public class TestingServlet extends HttpServlet{
	
	private Map<String, Command> commands = new HashMap<>();

    public void init(){
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("welcome" , new WelcomeCommand());
        commands.put("logout" , new LogoutCommand());
        commands.put("noaccess" , new NoAccessCommand());
        commands.put("exception" , new ExceptionCommand());
        commands.put("find-all-registered" , new FindAllUsersCommand());
        commands.put("edit-u-info" , new EditUserCommand());
        commands.put("finish-u-edit" , new FinishUserEditCommand());
        commands.put("choose-test" , new DisplayTestsCommand());
        commands.put("delete-u" , new DeleteUserCommand());
        commands.put("grant-admin-rights" , new GrantAdminRightsCommand());
        commands.put("revoke-admin-rights" , new RevokeAdminRightsCommand());
        commands.put("create-test" , new CreateTestCommand());
        commands.put("add-question", new AddQuestionCommand());
        commands.put("finish-test", new FinishTestCreationCommand());
        commands.put("finish-test", new FinishTestCreationCommand());
        commands.put("find-all-tests", new DisplayTestsForAdminCommand());
        commands.put("edit-test", new EditTestCommand());
        commands.put("delete-test", new DeleteTestCommand());
        commands.put("preview-test", new PreviewTestCommand());
        commands.put("edit-question", new EditQuestionCommand());
        commands.put("skip-edit-question", new SkipQuestionEditCommand());
        commands.put("delete-question", new DeleteQuestionCommand());
        commands.put("finish-test-edit", new FinishTestEditCommand());
        commands.put("start-test", new StartTestCommand());
        commands.put("answer-next", new AnswerQuestionCommand());
        commands.put("answer-first", new AnswerFirstQuestionCommand());
        commands.put("finish-taking-test", new FinishTakingTestCommand());
        commands.put("display-certificates", new DisplayCertificatesCommand());
        
        
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String path = request.getRequestURI();
        path = path.replaceAll(".*/serv/" , "");
        
        Command command = commands.getOrDefault(path, (r)->"/index.jsp)");
        
        String page = command.execute(request);
        
        if(page.contains("redirect:")){
        	response.sendRedirect(page.replace("redirect:", "/TestingWebsite"));
        } else {
        	request.getRequestDispatcher(page).forward(request, response);
        }
    }

}
