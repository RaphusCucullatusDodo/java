<%--
  Created by IntelliJ IDEA.
  User: pw
  Date: 2021/8/9
  Time: 7:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>这是我的第一个JSP</h1>
<%
    //Java代码
    System.out.println("hello JSP");
    String str = "Hello JSP";
%>
<%--
    此处会在页面上打印输出str的值
--%>
<%=str%>
<%--
    这种脚本代码运行在Servlet类中，不在之前两种脚本代码所在的 _jspxFactory 方法中
--%>
<%!
    private int number;
%>
<%
    System.out.println("number = " + number);
%>
</body>
</html>
