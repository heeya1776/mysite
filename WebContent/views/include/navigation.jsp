<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul>
	<c:choose>
		<c:when test="${param.pagename == 'main' }">
			<li class="selected"><a href="#">안대혁</a></li>
			<li><a href="/guestbook">방명록</a></li>
			<li><a href="/board">게시판</a></li>
		</c:when>
		<c:when test="${param.pagename == 'guestbook' }">
			<li><a href="/index">안대혁</a></li>
			<li class="selected"><a href="#">방명록</a></li>
			<li><a href="/board">게시판</a></li>
		</c:when>
		<c:when test="${param.pagename == 'user' }">
			<li><a href="/index">안대혁</a></li>
			<li><a href="/guestbook">방명록</a></li>
			<li><a href="/board">게시판</a></li>
		</c:when>
		<c:when test="${param.pagename == 'board' }">
			<li><a href="/index">안대혁</a></li>
			<li><a href="/guestbook">방명록</a></li>
			<li class="selected"><a href="board">게시판</a></li>
		</c:when>
		<c:when test="${param.pagename == 'view' }">
			<li><a href="/index">안대혁</a></li>
			<li><a href="/guestbook">방명록</a></li>
			<li class="selected"><a href="/board">게시판</a></li>
		</c:when>	
	</c:choose>
</ul>