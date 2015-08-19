package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.vo.BoardVo;
import com.bit2015.mysite.vo.MySiteVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class AddAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			HttpSession session = request.getSession();
			if(session == null){
				WebUtil.redirect(request, response, "/user?a=login");
			}
			MySiteVo userVo = (MySiteVo)session.getAttribute("authUser");
			if(userVo == null){
				WebUtil.redirect(request, response, "/user?a=login");
			}
			
			BoardVo vo = new BoardVo();
			vo.setName(userVo.getName());
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(userVo.getNo());
			
			BoardDao dao = BoardDao.getInstance();
			dao.insert(vo);
			
			WebUtil.redirect(request, response, "board");
		}catch(SQLException ex){
			System.out.println("DB 오류 : " + ex);
		}
			
	}

}
