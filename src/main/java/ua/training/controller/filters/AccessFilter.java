 package ua.training.controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.User;

/**
 * This is a filter that checks if the user is authorized to access the requested page.
 * If the user isn't authorized, the request is redirected to a corresponding page.
 *
 */
public class AccessFilter implements Filter {
	
	private static final String LOGIN = "login";
	private static final String REGISTRATION = "registration";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute(Constants.USER);
		String role = null;
		
		if (user != null) {
			role = user.getRole().toLowerCase();
		}
		
        String path = httpRequest.getRequestURI();
        
        boolean pathContainsUser = path.contains(Constants.USER);
        
        boolean pathContainsAdmin = path.contains(Constants.ADMIN);
        
        boolean pathContainsIndexLoginOrRegistration = path.contains(Constants.INDEX) || path.contains(LOGIN) || path.contains(REGISTRATION);
        
        boolean roleIsUser = Constants.USER.equalsIgnoreCase(role);
        
        boolean roleIsAdmin = Constants.ADMIN.equalsIgnoreCase(role);
        
        if (pathContainsUser && !roleIsUser) {
        	request.getRequestDispatcher("/serv/noaccess").forward(request, response);
        } else if (pathContainsAdmin && !roleIsAdmin){
        	request.getRequestDispatcher("/serv/noaccess").forward(request, response);
        } else if (pathContainsIndexLoginOrRegistration && (roleIsUser || roleIsAdmin)){
        	request.getRequestDispatcher("/serv/welcome").forward(request, response);
        }
        
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
