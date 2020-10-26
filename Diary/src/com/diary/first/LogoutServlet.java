package com.diary.first;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/*Cookie [] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : request.getCookies())
			{
				if (cookie.getName().equals("user"));
				{
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}*/
		
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			session.invalidate();
		}
		
		response.sendRedirect("login.html");
	}

}
