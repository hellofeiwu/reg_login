<%@ page import="com.imooc.utils.CookieUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<link rel="stylesheet" href="./css/login.css">
</head>

<body>
    <div class="login">
        <div class="header">
            <h1>
                <a href="./login.jsp">登录</a> <a href="./regist.jsp">注册</a>
            </h1>

        </div>
        <%
            String username = "";
            String msg = "";
            msg = (String) request.getAttribute("msg");

            Cookie[] cookies = request.getCookies();
            if(CookieUtils.getCookie(cookies, "username") != null) {
                username = CookieUtils.getCookie(cookies, "username").getValue();
            }
            
            if(application.getAttribute("username") != null) {
                username = (String) application.getAttribute("username");
            }

        %>
        <%= msg + "test"%>
        <form action="/reg_login/LoginServlet" method="post">
            <table>
                <tr>
                    <td class="td1">用户名</td>
                    <td><input type="text" class="input1" name="username" value="<%=username%>"></td>
                </tr>
                <tr>
                <td class="td1">密码</td>
                <td><input type="password" class="input1" name="password"></td>
                </tr>
                <tr>
                <td class="td1" colspan="2">
                    <input type="checkbox" name="remember" value="true" checked="checked"> 记住用户名</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="btn-red">
                            <input type="submit" value="登录" id="login-btn">
                        </div>
                    </td>
                </tr>
            </table>

        </form>
    </div>
</body>
</html>