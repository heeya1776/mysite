package com.bit2015.mysite.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.MySiteDao;
import com.bit2015.mysite.vo.MySiteVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class JoinAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try{
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			MySiteVo vo = new MySiteVo();
			vo.setName(name);
			vo.setEmail(email);
			vo.setPassword(password);
			vo.setGender(gender);
			
			MySiteDao dao = MySiteDao.getInstance();
			dao.insert(vo);
			
			WebUtil.forward(request, response, "/user?a=joinsuccess");
		}catch(SQLException ex){
			System.out.println("DB오류 : " + ex);
		}
		
		
	}

}
