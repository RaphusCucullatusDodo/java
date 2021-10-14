<%--
  Created by IntelliJ IDEA.
  User: pw
  Date: 2021/8/13
  Time: 5:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>评论页面</title>
</head>
<body>
<h1>评论页面</h1>
<form action="${pageContext.request.contextPath}/comments">
    评论内容：<input type="text" name="comments"><br/>
    <input type="submit" value="评论"/>
</form>
</body>
</html>
