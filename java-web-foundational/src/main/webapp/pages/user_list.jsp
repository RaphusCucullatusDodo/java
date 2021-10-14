<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  UserMapper: pw
  Date: 2021/8/10
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示用户数据</title>
</head>
<body>
<h1>使用javabean 自定义Dbutil Sevlet JSP EL JSTL展示用户数据</h1>

<table width="960px" border="1" spellcheck="0" cellpadding="0">
    <tr>
        <%--    一般密码不会展示，这里只是演示  --%>
        <th>id</th>
        <th>name</th>
        <th>password</th>
        <th>create_date</th>
        <th>update_date</th>
    </tr>

    <%--  使用EL表达式+JSTL将域对象的数据展示到JSP页面上--%>
    <c:forEach items="${userVOList}" var="userVO" varStatus="status">
    <tr>
        <th>${status.count}</th>
        <th>${status.current.name}</th>
        <th>${userVO.password}</th>
        <th>${userVO.createDate}</th>
        <th>${userVO.updateDate}</th>
    <tr>
        </c:forEach>
</table>

</body>
</html>
