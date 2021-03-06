package com.bit2015.mysite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.bit2015.mysite.vo.MySiteVo;

/**
 * Servlet implementation class JSONServlet
 */
public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		/*
		 * var o = {
		 * 		"name" : "한제희",
		 * 		"message" : "안녕하세요"
		 * }
		 * 
		 */
		List<MySiteVo> list = new ArrayList<MySiteVo>();
		MySiteVo vo = new MySiteVo();
		vo.setName("안대혁");
		vo.setEmail("kickscar@gmail.com");
		list.add(vo);
		list.add(new MySiteVo());
		list.add(new MySiteVo());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "한제희");
		map.put("message", "안녕하세요");
		map.put("list", list);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		out.print(jsonObject.toString());
	}

}
