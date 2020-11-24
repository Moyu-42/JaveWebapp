<%--
  Created by IntelliJ IDEA.
  User: Alcumn
  Date: 2020/11/23
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="bean.Database" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <head>
        <title>数据库操作结果</title>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <!-- 使用Edge最新的浏览器的渲染方式 -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
        width: 默认宽度与设备的宽度相同
        initial-scale: 初始的缩放比，为1:1 -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <!-- 新 Bootstrap 核心 CSS 文件 -->
        <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
        <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
        <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
        <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
    </head>
</head>
<body>

<table border="1" class="table table-bordered table-hover" style="width: 50%; margin: auto;">
    <c:forEach items="${list}" var = "show">
        <tr style="text-align: center;">
            <td style="text-align: center;">
                <c:if test="${fn:substring(show, 0, 1) == '1'}">
                    从user
                </c:if>
                <c:if test="${fn:substring(show, 0, 1) == '2'}">
                    从person
                </c:if>

                <c:if test="${fn:substring(show, 1, 2) == '1'}">
                    插入
                </c:if>
                <c:if test="${fn:substring(show, 1, 2) == '2'}">
                    更新
                </c:if>
                <c:if test="${fn:substring(show, 1, 2) == '3'}">
                    删除
                </c:if>

                <c:set var="username" value="${fn:substring(show, 3, -1)}"/>
                ${username}

                <c:if test="${fn:substring(show, 2, 3) == '0'}">
                    失败
                </c:if>
                <c:if test="${fn:substring(show, 2, 3) == '1'}">
                    成功
                </c:if>

            </td>
        </tr>
    </c:forEach>
</table>
<center><a href="index.jsp">返回数据库操作页面</a></center>
<center><a href="queryServlet">查看数据库数据</a></center>
</body>
</html>
