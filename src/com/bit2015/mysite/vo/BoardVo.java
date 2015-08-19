package com.bit2015.mysite.vo;

public class BoardVo {
	
	private long no;
	private String name;
	private String title;
	private String content;
	private String regDate;
	private long memberNo;
	private long readNo;
	private long rNum;
	private long page;
	private long totcnt;
	
	public long getrNum() {
		return rNum;
	}
	public void setrNum(long rNum) {
		this.rNum = rNum;
	}
	public long getPage() {
		return page;
	}
	public void setPage(long page) {
		this.page = page;
	}
	public long getTotcnt() {
		return totcnt;
	}
	public void setTotcnt(long totcnt) {
		this.totcnt = totcnt;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}
	public long getReadNo() {
		return readNo;
	}
	public void setReadNo(long readNo) {
		this.readNo = readNo;
	}
	
}
