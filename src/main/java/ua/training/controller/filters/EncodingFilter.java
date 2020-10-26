package ua.training.controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	
	private static final String ENCODING = "UTF-8";
	private static final String CONTENT_TYPE = "text/html";

    public EncodingFilter() {
    	
    }

    public void destroy() {
    	
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		response.setContentType(CONTENT_TYPE);
		request.setCharacterEncoding(ENCODING);
		response.setCharacterEncoding(ENCODING);
		
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
