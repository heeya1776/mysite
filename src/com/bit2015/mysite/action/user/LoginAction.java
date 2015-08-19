package com.bit2015.mysite.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2015.mysite.dao.MySiteDao;
import com.bit2015.mysite.vo.MySiteVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class LoginAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			MySiteDao dao = MySiteDao.getInstance();
			MySiteVo vo = dao.get(email, password);
			
			if(vo == null){
				//로그인 실패
				WebUtil.redirect(request, response, "user?a=loginform&error=true");
				return;
			}
			HttpSession session = request.getSession(true);
			session.setAttribute("authUser", vo);
			
			WebUtil.redirect(request, response, "/index");
		} catch(SQLException ex){
			System.out.println("DB오류 : " + ex);
		}
	}
	
}
