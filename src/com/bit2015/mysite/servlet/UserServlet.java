package com.bit2015.mysite.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.action.user.UserActionFactory;
import com.bit2015.web.action.Action;
import com.bit2015.web.action.ActionFactory;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String actionName = request.getParameter("a");
		
		ActionFactory actionFactory = new UserActionFactory();
		Action action  = actionFactory.getAction(actionName);
		
		action.execute(request, response);
	}

}