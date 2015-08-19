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

public class ModifyFormAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		try{
			String no = request.getParameter("no");
			long num = Long.parseLong(no);
			
			BoardDao dao = BoardDao.getInstance();
			BoardVo vo = dao.getVo(num);
			request.setAttribute("userVo", vo);
			
			WebUtil.forward(request, response, "/views/board/modify.jsp");
		}catch(SQLException ex){
			System.out.println("DB 오류 ; " + ex);
		}
			
	}

}
