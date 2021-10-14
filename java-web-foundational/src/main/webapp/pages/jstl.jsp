<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  UserMapper: pw
  Date: 2021/8/10
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSTL标签库的使用</title>
</head>
<body>
<h1>JSTL if标签的使用</h1>
<%
    //往request中存储年龄
    request.setAttribute("age", 16);
%>
<%--
test 属性是必须包含的 以是el表达式
var 将判断结果赋值给var声明变量
--%>
<c:if test="${age>=18}" var="flag" scope="page">
    你已经成年了可以办信用卡
</c:if><br/>
判断结果${flag}<br/>
<c:if test="${age<18}">
    你未成年，不可以办信用卡
</c:if><br/>

<h1>JSTL choose when标签的使用</h1>
<%
    request.setAttribute("score", 90);
%>
<c:choose>
    <c:when test="${score<=100&&score>=90}">成绩优秀</c:when>
    <c:when test="${score<90&&score>=80}">成绩中等</c:when>
    <c:when test="${score<80&&score>=70}">成绩良好</c:when>
    <c:when test="${score<70&&score>=60}">及格</c:when>
    <c:when test="${score<60&&score>=0}">不及格</c:when>
    <c:otherwise>成绩有误</c:otherwise>
</c:choose>

<h1>JSTL foreach标签的使用</h1>
<c:forEach begin="0" end="9" step="1" var="i">
    ${i}<br/>
</c:forEach>
<%--
begin：开始下标，默认为零
end；结束下标
step：步长，默认为1
items：需要遍历的变量名
var：储存便利出的数据
varstatus：表示将当前遍历的信息状态储存到page作用域，遍历出来的每个元素都有以下常用状态属性
    index 下标
    count 序号，计数
    first 是否第一个
    last 是否最后一个
    获取varstatus对象如（status）就可以获取这些信息
    默认varstatus对象为
--%>
<h1>JSTL foreach标签遍历集合</h1>

<%
    ArrayList<String> cities = new ArrayList<>(Arrays.asList("北京", "上海", "广州", "深圳"));
    request.setAttribute("cities", cities);
%>
一线城市：<br/>
<c:forEach items="${cities}" var="city" varStatus="status">
    ${city}<br/>
</c:forEach>

状态对象的属性信息的使用
<table width="960px">
    <tr>
        <th>下标</th>
        <th>序号</th>
        <th>城市名</th>
        <th>是否第一个元素</th>
        <th>是收最后一个元素</th>
    </tr>
    <c:forEach items="${cities}" var="city" varStatus="status">
        <tr>
            <th>${status.index}</th>
            <th>${status.count}</th>
            <th>${status.current}</th>
            <th>${status.first}</th>
            <th>${status.last}</th>
        </tr>
    </c:forEach>
</table>

</body>
</html>
