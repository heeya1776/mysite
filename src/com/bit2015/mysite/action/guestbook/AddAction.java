package com.bit2015.mysite.action.guestbook;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.GuestBookDao;
import com.bit2015.mysite.vo.GuestBookVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class AddAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		try {
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String message = request.getParameter("content");

			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);

			GuestBookDao dao = GuestBookDao.getInstance();
			dao.insert(vo);

			WebUtil.redirect( request, response, "/guestbook" );
		} catch (SQLException ex) {
			System.out.println("DB Error : " + ex);
		}
	}

}
