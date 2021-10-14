<%--
  Created by IntelliJ IDEA.
  User: pw
  Date: 2021/8/10
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户转账</title>
</head>
<body>
<h1>用户转账页面</h1>
<form action="${pageContext.request.contextPath}/account" method="post">
    <div style="color: red">${transferMassage}</div>
    <input type="hidden" name="method" value="accountTransfer"/>
    转出账号名<input type="text" name="fromAccountName" autocomplete="off"/><br/>
    转入账号名<input type="text" name="toAccountName" autocomplete="off"/><br/>
    转账金额<input type="text" name="amount" autocomplete="off"/><br/>
    <input type="submit" name="确认">

</form>
</body>
</html>
