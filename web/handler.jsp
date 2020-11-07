<%--
  Created by IntelliJ IDEA.
  User: Alcumn
  Date: 2020/11/5
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="service.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="dao.*" %>
<%@ page import="bean.User" %>
<%@ page import="bean.Database" %>
<html>
<head>
    <title>处理数据</title>
</head>
<body>
<%
    Database db = new Database();
    PersonOpt p = new PersonOpt(db);
    UserOpt u = new UserOpt(db);
%>
<%
    String action = request.getParameter("submit");
        String username_u = request.getParameter("username_user");
        String password = request.getParameter("password");
        u.insert(new User(username_u, password));
%>
</body>
</html>
