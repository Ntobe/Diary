package com.diary.first;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    public SignUpServlet()
    {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		boolean isRegistered = false;
		
		PrintWriter pw = response.getWriter();
		
		if (username != null && username.length()>0)
		{
			if (password != null)
			{
				if (password.equals(confirmPassword))
				{
					File file = new File(getServletContext().getInitParameter("userPasswordFile"));
					BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
					PrintWriter out =  new PrintWriter(bw);
					String diaryLocation = "C:\\Users\\Administrator\\eclipse-workspace\\Diary\\" + username + "_diary.txt";
					out.println(username + " " + password + " " + diaryLocation);
					out.close();
					bw.close();
					isRegistered = true;
					response.sendRedirect("login.html");
				}
			}
		}
		
		if (!isRegistered)
		{
			pw.append(
					"<font color=red >User not registered. Check that the username is not empty and the password and confirm password fields are the same</font>");
		}
	}

}
