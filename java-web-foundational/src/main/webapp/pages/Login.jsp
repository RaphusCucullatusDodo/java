<%@ page import="cn.raphuscucullatus.java.web.foundational.util.CookieUtil" %>
<%--
  Created by IntelliJ IDEA.
  UserMapper: pw
  Date: 2021/8/9
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登入</title>
</head>
<body>

<div style="color: brown">${errorMassage}</div>
<%-- <form action="${pageContext.request.contextPath}"> --%>
<form action="${pageContext.request.contextPath}/login" method="post">
    用户名：<input type="text" name="name" value="${cookie.name.value}"><br/>
    密 码：<input type="password" name="password" value="${cookie.password.value}"><br/>
    验证码：<input type="text" name="captcha" autocomplete="off"><br/>
    <img src="${pageContext.request.contextPath}/captcha" width="130px" height="48px" id="captcha"
         onclick="changeImg()"/><br/>
    <input type="checkbox" name="remember">记住我<br/>

    <input type="submit" name="登入">
</form>
<script>
    /**
     * 通过点击验证码图片更换验证码图片
     */
    function changeImg() {
        var captcha = document.getElementById(`captcha`);
        //仅仅改变属性，没改变请求服务器会认为是同一个请求，返回304状态吗，浏览器直接读取缓存
        // 所以可以在请求后接动态参数 如日期时间
        captcha.setAttribute("src", "${pageContext.request.contextPath}/captcha?" + new Date())
    }
</script>
</body>
</html>
