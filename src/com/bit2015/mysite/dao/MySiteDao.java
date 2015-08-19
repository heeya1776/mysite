package com.bit2015.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bit2015.mysite.vo.MySiteVo;

public class MySiteDao {
	
	private static MySiteDao instance = null;
	private MySiteDao(){
	}
	public static MySiteDao getInstance(){
		if(instance == null){
			instance = new MySiteDao();
		}
		return instance;
	}
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try{
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bitdb", "bitdb");
		} catch(ClassNotFoundException ex) {
			System.out.println("드라이버 오류  : " +ex);
		}
		return conn;
	}
	public MySiteVo get(long no) throws SQLException {
		Connection conn = getConnection();
		
		String sql = "select no, email, name, gender from member where no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		
		MySiteVo vo = null;
		if(rs.next()){
			vo = new MySiteVo();
			
			vo.setNo(rs.getLong(1));
			vo.setEmail(rs.getString(2));
			vo.setName(rs.getString(3));
			vo.setGender(rs.getString(4));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return vo;
	}
	public MySiteVo get(String email) throws SQLException{
		Connection conn = getConnection();
		
		String sql = "select no, email, name, gender from member where email=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
	
		
		ResultSet rs = pstmt.executeQuery();
		
		MySiteVo vo = null;
		if(rs.next()){
			vo = new MySiteVo();
			vo.setNo(rs.getLong(1));
			vo.setEmail(rs.getString(2));
			vo.setName(rs.getString(3));
			vo.setGender(rs.getString(4));
			
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return vo;
	
	}
	public MySiteVo get(String email, String password) throws SQLException{
		Connection conn = getConnection();
		
		String sql = "select no, email, name from member where email=? and password=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		
		MySiteVo vo = null;
		if(rs.next()){
			vo = new MySiteVo();
			vo.setNo(rs.getLong(1));
			vo.setEmail(rs.getString(2));
			vo.setName(rs.getString(3));
			
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return vo;
	}
	public void insert(MySiteVo vo) throws SQLException {
		Connection conn = getConnection();
		
		String sql = "insert" + 
					 " into MEMBER " +
					 " values (member_no_seq.nextval, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getEmail());
		pstmt.setString(3, vo.getPassword());
		pstmt.setString(4, vo.getGender());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
			
	}

}
