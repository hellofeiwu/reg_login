<%@page import="com.imooc.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录成功</title>
<link rel="stylesheet" href="./css/login.css">
</head>
<body>
<%
	if(request.getSession().getAttribute("user") != null) {
		User user = (User)session.getAttribute("user");
		int index = user.getPath().lastIndexOf('\\');
		String fileName = user.getPath().substring(index+1);
%>
<%= fileName%>
<div class="login">
	<div class="header">
		<h1>登录成功</h1>
	</div>
	<div class="content">
		<table align="center">
			<tr>
				<td align="center"><img src="/reg_login/upload/<%=fileName%>" /></td>
			</tr>
			<tr>
				<td align="center">欢迎<%= user.getNickname()%>,登录成功！</td>
			</tr>
		</table>

	</div>
</div>
<%
	}else {
%>
<div class="login">
	<div class="header">
		<h1>登录失败</h1>
	</div>
	<div class="content">
		<table align="center">
			<tr>
				<td align="center">You are not logged in yet, please <a href="login.jsp">login</a></td>
			</tr>
		</table>

	</div>
</div>
<%
	}
%>

</body>
</html>