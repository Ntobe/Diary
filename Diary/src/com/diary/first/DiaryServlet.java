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
 * Servlet implementation class DiaryServlet
 */
@WebServlet("/DiaryServlet")
public class DiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DiaryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String diaryText = request.getParameter("diaryText");
		String username = (String)request.getSession().getAttribute("user");
		String diaryLocation = "C:\\Users\\Administrator\\eclipse-workspace\\Diary\\" + username + "_diary.txt";
		File diary = new File(diaryLocation);
		BufferedWriter bwDiary = new BufferedWriter(new FileWriter(diary, true));
		PrintWriter outDiary =  new PrintWriter(bwDiary);
		outDiary.append(diaryText);
		outDiary.close();
	}

}
