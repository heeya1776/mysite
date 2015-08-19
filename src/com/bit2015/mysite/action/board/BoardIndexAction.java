package com.bit2015.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2015.mysite.dao.BoardDao;
import com.bit2015.mysite.vo.BoardVo;
import com.bit2015.web.action.Action;
import com.bit2015.web.util.WebUtil;

public class BoardIndexAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			
			BoardDao dao = BoardDao.getInstance();
			List<BoardVo> list = new ArrayList<BoardVo>();
			long total=dao.total()/5+1;
			long pageList = 0;
			String pageNo = null;
		
			
			if(request.getParameter("pageNo") == null ){
				list = dao.getList(1);
				pageList = 3;
			}else {
				pageNo = request.getParameter("pageNo");
				long pageNum = Long.parseLong(pageNo);
				if(pageNum <= 1){
					pageNo = "1";
					list = dao.getList(1);
				}else if(pageNum >= total){
					pageNo = String.valueOf(total);
					list = dao.getList(total);
				}else{
					list = dao.getList(pageNum);
				}
			
				pageList = pageNum;
				if(pageList<3 && pageList<total-2){
					pageList=3;
				}else if(pageList>=total-2 && pageList<3){
					pageList=3;
				}else if(pageList>3 && pageList>=total-2){
					pageList=total-2;
				}
			}
			
			request.setAttribute( "pageList", pageList );
			request.setAttribute( "list", list );
			request.setAttribute("total", total);
			request.setAttribute( "pageNo", pageNo );
			
			WebUtil.forward(request, response, "/views/board/boardform.jsp");
		
		}catch(SQLException ex){
		
			System.out.println("DB오류 : " + ex);
		
		}

	}

}
