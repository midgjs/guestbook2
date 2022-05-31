<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="com.javaex.dao.GuestbookDao" %>
<%@ page import ="com.javaex.vo.GuestbookVo" %>

<%
	int number = (int)request.getAttribute("no");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="./gbc" method="post">
		<label for="password">비밀번호</label>
		<input type="password" name = "password" value="">
		<button type="submit">확인</button>
		<input type="hidden" name="number" value="<%=number%>">
		<input type="hidden" name="action" value="delete">
	</form>
	
	<br>
	<br>
	
	<a href="http://localhost:8088/guestbook1/addList.jsp">메인으로 돌아가기</a>
	
	

</body>
</html>