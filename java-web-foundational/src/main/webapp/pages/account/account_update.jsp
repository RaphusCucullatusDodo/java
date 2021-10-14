<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  UserMapper: pw
  Date: 2021/8/16
  Time: 2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改账户信息</title>
</head>
<body>
<h1>修改账户信息</h1>
<div style="color: red">${errorMassage}</div>
<form action="${pageContext.request.contextPath}/account" method="post">
    <input type="hidden" name="method" value="accountUpdate"/>
    <input type="hidden" name="id" value="${account.id}"/>
    账户名:<input type="text" name="name" value="${account.name}"/><br/>
    账户余额:<input type="text" name="balance" value="${account.balance}"/><br/>
    账号状态:
    <c:if test="${account.status==1}">
        <input type="radio" name="status" value="1" checked>启用<input type="radio" name="status" value="0">禁用<br/>
    </c:if>
    <c:if test="${account.status==0}">
        <input type="radio" name="status" value="1">启用<input type="radio" name="status" value="0" checked>禁用<br/>
    </c:if>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
