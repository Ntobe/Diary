package com.diary.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter
{

	ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig)
	{
		this.context = filterConfig.getServletContext();
		this.context.log("RequestLoggingFilter has initialized...");
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		Enumeration<String> paramNames = request.getParameterNames();
		
		while (paramNames.hasMoreElements())
		{
			String paramName = paramNames.nextElement();
			String paramValue = request.getParameter(paramName);
			
			this.context.log("Request parameter: { " + paramName + " = " + paramValue + " }");
		}
		
		Cookie [] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				this.context.log("Cookie: {" + cookie.getName() + " = " + cookie.getValue() + " }");
			}
		}
		
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy()
	{
		
	}
}
