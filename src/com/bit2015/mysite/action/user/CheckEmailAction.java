package com.bit2015.mysite.action.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.bit2015.mysite.dao.MySiteDao;
import com.bit2015.mysite.vo.MySiteVo;
import com.bit2015.web.action.Action;

public class CheckEmailAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try{
			String email = request.getParameter("email");
			
			MySiteDao dao = MySiteDao.getInstance();
			MySiteVo vo  = dao.get(email);
			
			//{"result: "exist"}
			//{"result: "not exist"}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", (vo == null)?"not exist":"exist");
			
			JSONObject jsonObject = JSONObject.fromObject(map);
					
			response.setContentType("application/json; charset=utf-8");			
			PrintWriter out = response.getWriter();
			out.print(jsonObject.toString());
			
		}catch(SQLException ex){
			System.out.println("DB오류 : " + ex);
		}

	}

}
