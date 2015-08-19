<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/include/header.jsp">
			</c:import>
		</div>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${writer.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${writer.content }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">	
					<a href="board">글목록</a>
					<c:choose>
						<c:when test="${empty authUser }"></c:when>
						<c:otherwise>
							<a href="board?a=modifyform&no=${writer.no }">글수정</a>
						</c:otherwise>	
					</c:choose>
				</div>
			</div>
		</div>
		<div id="navigation">
			<c:import url="/views/include/navigation.jsp">
				<c:param name="pagename" value="view"/>
			</c:import>
		</div>
		<div id="footer">
			<c:import url="/views/include/footer.jsp">
			</c:import>
		</div>
	</div>
</body>
</html>