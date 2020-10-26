package com.diary.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter
{
	ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig)
	{
		this.context = filterConfig.getServletContext();
		context.log("AuthenticatinFilter has initialized...");
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
		throws IOException,
			ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String uri = request.getRequestURI();
		
		this.context.log("Requested URI :" + uri);
		HttpSession session = request.getSession(false);
		
		//should not have session only on signup (signup.html) or login(login.html)
		if (session == null && !(uri.endsWith("html")))
		{
			this.context.log("Unauthorized access");
			response.sendRedirect("login.html");
		}
		else
		{
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void destroy()
	{
		
	}
	
}
