package com.bit2015.mysite.action.index;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class IndexAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		WebUtil.forward(request, response, "/views/main/index.jsp");
	}

}
