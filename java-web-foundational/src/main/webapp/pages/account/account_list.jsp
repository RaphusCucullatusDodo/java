<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  UserMapper: pw
  Date: 2021/8/14
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看用户列表</title>
</head>
<body>
<h1>展示所有用户列表</h1>
<div style="color: red">${errorMassage}</div>
<table width="960px">
    <tr>
        <th>编号</th>
        <th>账户</th>
        <th>资金</th>
        <th>状态</th>
        <th>创建日期</th>
        <th>修改时间</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${accountList}" var="account" varStatus="accountStatus">
        <tr>
            <td>${accountStatus.count}</td>
            <td>${account.name}</td>
            <td>${account.balance}</td>
            <td>
                <c:if test="${account.status==1}">启用</c:if>
                <c:if test="${account.status==0}">禁用</c:if>
            </td>
            <td>${account.createDate}</td>
            <td>${account.updateDate}</td>
            <td>
                <a href="#" onclick="deleteAccountById('${account.id}','${account.name}')">删除</a>
                <a href="#" onclick="toAccountUpdatePage('${account.id}')">修改</a>
            </td>
        </tr>
    </c:forEach>
</table>

<div><a href="pages/account/account_add.jsp" target="_blank">添加账户</a></div>

<script type="text/javascript">
    function deleteAccountById(id, name) {
        console.log("要删除的id是:" + id)
        let flag = confirm("确认要删除" + name + "吗");
        if (flag) {
            window.location.href = "${pageContext.request.contextPath}/account?method=deleteAccountById&id=" + id;
        }
    }

    /**
     * 跳转到AccountServlet
     * @param id
     */
    function toAccountUpdatePage(id) {
        window.location.href = "${pageContext.request.contextPath}/account?method=toAccountUpdatePage&id=" + id;
    }
</script>
</body>
</html>
