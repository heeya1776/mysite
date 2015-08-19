package com.bit2015.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2015.mysite.vo.BoardVo;

public class BoardDao {
	private static BoardDao instance = null;
	
	private BoardDao(){
	}
	
	public static BoardDao getInstance(){
		if(instance == null){
			instance = new BoardDao();
		}
		return instance;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻기
			conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", "bitdb", "bitdb");
		
		} catch( ClassNotFoundException ex ) {
			System.out.println( "드라이버 오류 :" + ex );
		}
		
		return conn;
	}
	
	
	public long total() throws SQLException {
		
		long total = 0;
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sql = "select count(*) from board";
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next()){
			total=rs.getLong(1);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return total;
		
	}
	
	public List<BoardVo> getList(long pageNo) throws SQLException {
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		Connection conn = getConnection();
		
		String sql = "SELECT * " +
				     "  FROM (" +
					 		  " SELECT  A.*, rownum as rnum, floor((rownum-1)/5+1) as page, count(*) over() as totcnt" + 
					 		  "   FROM (" + 
					 	   	            " SELECT no, name, title, TO_CHAR(reg_date, 'YYYY-MM-DD HH:MI:SS'), member_no, read_no" +
					 	   	            " FROM board" +
					 	   	            " ORDER BY reg_date DESC) A )" +
					 " WHERE page = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, pageNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			
			long no = rs.getLong(1);
			String name = rs.getString(2);
			String title = rs.getString(3);
			String regDate = rs.getString(4);
			long memberNo = rs.getLong(5);
			long readNo = rs.getLong(6);
			long rNum = rs.getLong(7);
			long page = rs.getLong(8);
			long totcnt = rs.getLong(9);
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setTitle(title);
			vo.setRegDate(regDate);
			vo.setMemberNo(memberNo);
			vo.setReadNo(readNo);
			vo.setrNum(rNum);
			vo.setPage(page);
			vo.setTotcnt(totcnt);
			
			list.add(vo);
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}
	
	public BoardVo getVo(long no) throws SQLException {
		
		Connection conn = getConnection();
		
		String sql = "select no, title, content, read_no from board where no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		
		BoardVo vo = null;
		if(rs.next()){
			vo = new BoardVo();
			vo.setNo(rs.getLong(1));
			vo.setTitle(rs.getString(2));
			vo.setContent(rs.getString(3));
			vo.setReadNo(rs.getLong(4));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return vo;
	}
	
	public void insert(BoardVo vo) throws SQLException {
		
		Connection conn = getConnection();
		
		String sql = "insert into board values (BOARD_seq.nextval, ?, ?, ?, sysdate, ?, 0)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		pstmt.setLong(4, vo.getMemberNo());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	public void change(BoardVo vo) throws SQLException {
		
		Connection conn = getConnection();
		
		String sql = "update board set title=?, content=?, reg_date=sysdate where no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setLong(3, vo.getNo());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		
	}
	
	public void delete(BoardVo vo) throws SQLException {
		
		Connection conn = getConnection();
		
		String sql = "delete from board where no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, vo.getNo());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}
	
	public void clickNo(BoardVo vo) throws SQLException {
		
		Connection conn = getConnection();
		
		String sql ="update board set read_no=? where no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, vo.getReadNo()+1);
		pstmt.setLong(2, vo.getNo());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}
	
	public List<BoardVo> search(String str) throws SQLException {
		
		Connection conn = getConnection();
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		BoardDao dao = BoardDao.getInstance();
		
		String sql = "select no, name, title, content, to_char(reg_date, 'YYYY-MM-DD HH:MM:SS'), member_no, read_no from BOARD" +
					 " where name=? or title=? order by reg_date desc";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%" + str + "%");
		pstmt.setString(2, "%" + str + "%");
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			
			long no = rs.getLong(1);
			String name = rs.getString(2);
			String title = rs.getString(3);
			String content = rs.getString(4);
			String regDate = rs.getString(5);
			long memberNo = rs.getLong(6);
			long readNo = rs.getLong(7);
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setRegDate(regDate);
			vo.setMemberNo(memberNo);
			vo.setReadNo(readNo);
			
			list.add(vo);
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
		
	}
	
	
	
	
	
}
