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

public class ModifyFormAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		try {
			HttpSession session = request.getSession();
			if( session == null ) {
				WebUtil.redirect(request, response, "/");
				return;
			}
			
			MySiteVo vo = (MySiteVo)session.getAttribute( "authUser" );
			MySiteDao dao = MySiteDao.getInstance();
			vo = dao.get( vo.getNo() );
			
			request.setAttribute( "user", vo );
			WebUtil.forward(request, response, "/views/user/modifyform.jsp");
		} catch( SQLException ex ) {
			System.out.println( "DB Error:" + ex );
		}
	}
}
