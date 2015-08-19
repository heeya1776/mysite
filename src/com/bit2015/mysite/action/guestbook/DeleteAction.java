package com.bit2015.mysite.action.guestbook;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.GuestBookDao;
import com.bit2015.mysite.vo.GuestBookVo;
import com.bit2015.web.action.Action;

public class DeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			String no = request.getParameter("no");
			String password = request.getParameter("password");

			GuestBookVo vo = new GuestBookVo();
			vo.setNo(Long.parseLong(no));
			vo.setPassword(password);

			GuestBookDao dao = GuestBookDao.getInstance();
			dao.delete(vo);

			response.sendRedirect("/guestbook");
			
		} catch ( SQLException ex ) {
			System.out.println( "DB Error : " + ex );
		}
	}

}
