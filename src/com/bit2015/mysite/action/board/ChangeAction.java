package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.vo.BoardVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class ChangeAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		try{
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String no = request.getParameter("no");
			long num = Long.parseLong(no);
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setNo(num);
			
			BoardDao dao = BoardDao.getInstance();
			dao.change(vo);
			
			WebUtil.redirect(request, response, "board");
		}catch(SQLException ex){
			System.out.println("DB 오류 : " + ex);
		}

	}

}
