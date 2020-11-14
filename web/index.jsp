<%--
        Created by IntelliJ IDEA.
        User: Alcumn
        Date: 2020/11/3
        Time: 17:29
        To change this template use File | Settings | File Templates.
        --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bean.Database" %>

<html>
<head>
    <title>数据插入</title>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.min.js"></script>
    <script src="js/insert.js" type="text/javascript"></script>
    <script src="js/submit.js" type="text/javascript"></script>
</head>
<body>
<%
    Database db = new Database();
    application.setAttribute("database", db);
%>
<div class="container">
    <center> <h4>向users表插入数据</h4></center>
    <br>
    <form class="form-horizontal" role="form" id="form-users">
        <div class="form-group">
            <label for="username_user" class="col-sm-1 control-label">Username</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username_user"
                       name="username_user" placeholder="Please input username">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-1 control-label">Password</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="password"
                       name="password" placeholder="Please input password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-default" id="btn-users" name="btn-users">Submit</button>
            </div>
        </div>
    </form>
</div>

<div class="container">
    <br>
    <center> <h4>向person表插入数据</h4></center>
    <br>
    <form class="form-horizontal" role="form" id="form-person"
          data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
          data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
          data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <div class="form-group">
            <label for="username_person" class="col-sm-1 control-label">Username</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username_person"
                       name="username_person" placeholder="Please input username">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-1 control-label">Name</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name"
                       name="name" placeholder="Please input name">
            </div>
        </div>
        <div class="form-group">
            <label for="age" class="col-sm-1 control-label">Age</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="age"
                       name="age" placeholder="Please input age">
            </div>
        </div>
        <div class="form-group">
            <label for="teleno" class="col-sm-1 control-label">Teleno</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="teleno"
                       name="teleno" placeholder="Please input teleno">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-default" id="btn-person" name="btn-person">Submit</button>
            </div>
        </div>
    </form>
</div>

<div class="container">
    <center> <h4>从users表删除数据</h4></center>
    <br>
    <form class="form-horizontal" role="form" id="form-users-del">
        <div class="form-group">
            <label for="username_user" class="col-sm-1 control-label">Username</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username_user_del"
                       name="username_user_del" placeholder="Please input username">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-default" id="btn-users-del" name="btn-users-del">Submit</button>
            </div>
        </div>
    </form>
</div>

<center><a href="queryServlet">查看数据库数据</a></center>


</body>

</html>
