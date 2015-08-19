<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/assets/css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/include/header.jsp">
			</c:import>
		</div>
		<div id="content">
			<div id="board">
				<form id="search_form" action="board?a=search" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list) }"></c:set>
					<c:forEach var="vo" items="${list }" varStatus="status">
					<tr>
						<td>${(count-status.index)+5*(pageNo) }</td>
						<td><a href="board?a=view&no=${vo.no }">${vo.title }</a></td>
						<td>${vo.name }</td>
						<td>${vo.readNo }</td>
						<td>${vo.regDate }</td>
						<td>
							<c:choose>
								<c:when test="${authUser.no == vo.memberNo }">
									<a href="board?a=delete&no=${vo.no }" class="del">삭제</a>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</td>
					</tr>
					</c:forEach>	
					</table>
				<div class="bottom">
					<c:choose>
						<c:when test="${empty authUser }">
						</c:when>
						<c:otherwise>
							<a href="board?a=writeform" id="new-book">글쓰기</a>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="paging">
					<table id="pageNo">
					<td><a href="board?pageNo=${pageNo-5 }"><<</a></td>
					<td><a href="board?pageNo=${pageNo-1 }"><</a></td>
					<c:forEach var="totcnt" begin="${pageList-2 }" end="${pageList+2 }">
							<td><a href="board?pageNo=${totcnt }">[${totcnt }]</a></td>
					</c:forEach>
					<td><a href="board?pageNo=${pageNo+1 }">></a></td>
					<td><a href="board?pageNo=${pageNo+5 }">>></a></td>
					</table>
				</div>				
			</div>
		</div>
		<div id="navigation">
			<c:import url="/views/include/navigation.jsp">
				<c:param name="pagename" value="board"/>
			</c:import>
		</div>
		<div id="footer">
			<c:import url="/views/include/footer.jsp">
			</c:import>
		</div>
	</div>
</body>
</html>