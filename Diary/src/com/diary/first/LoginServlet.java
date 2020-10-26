package com.diary.first;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(
	description = "Login Servlet",
	urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private final String userID = "nontobeko";
	private final String password = "password";
	
	private final String userID2 = "nontobeko2";
	private final String password2 = "password2";
	
	public void init() throws ServletException
	{
		
		if (getServletContext()
			.getInitParameter("userPasswordFile")
			.equals(
				"C:\\Users\\Administrator\\eclipse-workspace\\Diary\\user_password.txt"))
		{
			getServletContext().setAttribute("DB_Success", "True");
		}
		else
		{
			throw new ServletException("DB Connection error " + getServletContext().getInitParameter("userPasswordFile"));
		}
		
	}

	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException
	{

		// get request parameters for userID and password
		String user = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		if ((userID.equals(user) && password.equals(pwd)) ||
			(userID2.equals(user) && password2.equals(pwd)))
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(30*60);
			
			/*Cookie loginCookie = new Cookie("user",user);
			//setting cookie to expiry in 30 mins
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);*/
			String encodedUrl = response.encodeRedirectURL("loginSuccess.jsp");
			response.sendRedirect(encodedUrl);
			//response.sendRedirect("loginSuccess.jsp");
		}
		else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}

	}
}
