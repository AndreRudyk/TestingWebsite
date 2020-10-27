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

/**
 * This filter sets the locale of pages according to what the user has chosen. 
 *
 */
public class LocaleFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String locale = req.getParameter(Constants.LOCALE);
		String defaultLocale = "en";
		
		if (locale != null) {
			session.setAttribute(Constants.LANG, locale);
		} else if (session.getAttribute(Constants.LANG) == null){
			session.setAttribute(Constants.LANG, defaultLocale);
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	

}
