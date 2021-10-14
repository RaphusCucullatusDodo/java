<%@ page import="java.util.*" %>
<%@ page import="cn.raphuscucullatus.java.web.foundational.bean.entity.User" %><%--
  Created by IntelliJ IDEA.
  UserMapper: pw
  Date: 2021/8/9
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL表达式的使用</title>
</head>
<body>
<h1>EL表达式获取域对象获取简单类型的数据</h1>
<%
    // 实际开发项目是，不会把同一个key存储到不同域对象中
//request.setAttribute("message","hello request");
//  这里注释后 只要清除JSESSIONID或重启服务器 获取的还是session
//session.setAttribute("message","hello session");
    application.setAttribute("message", "hello application");

    String message = (String) request.getAttribute("message");
    if (message == null) {
        message = "";
    }

%>
使用JSP脚本的方式获取request域对象名为message的值<%=message%><br/>
使用EL表达式的方式获取request域对象名为message的值${requestScope.message}<br/>
使用EL表达式的方式获取session域对象名为message的值${sessionScope.message}<br/>

使用EL表达式的方式获取application域对象名为message的值${applicationScope.message}<br/>
<%--
 如果没有作用域，则按 request --> session --> application 顺序查找需要的值
--%>
使用EL表达式的方式获取名为message的值${message}<br/>

<h1>EL表达式获取域对象获取复杂类型的数据</h1>
<%
    //存数组
    String[] cities = new String[]{"福建", "龙岩", "武平", "平川"};
    request.setAttribute("cities", cities);
    //存List
    List<String> productNameList = new ArrayList<>(Arrays.asList(cities));
    request.setAttribute("productNameList", productNameList);

    //存Map
    Map<String, String> map = new HashMap<>();
    map.put("tom", "11111");
    request.setAttribute("map", map);
//   存自定义类
    User user = new User();
    user.setId(8);
    user.setName("raphus");
    request.setAttribute("user", user);
    //往request中存入 key=product.name 的数据
    request.setAttribute("product.name", "小米11");
%>

使用EL表达式的方式获取域对象类型为数组,索引为0的元素:${cities[0]}<br/>
使用EL表达式的方式获取域对象类型为List,索引为1的元素:${productNameList.get(1)}<br/>
使用EL表达式的方式获取域对象类型为Map,key为tom对应的值:${map.get("tom")}<br/>
使用EL表达式的方式获取域对象product.name属性的值：${requestScope.get("product.name")}<br/>

<h1>EL表达式的字符串拼接</h1>
el表达式拼接字符串： hello${user.name}
<h1>EL表达式的运算符使用</h1>

判断域对象中是否为null：${empty user}<br/>
判断字符串是否为""：${empty user.createDate}<br/>
判断集合长度时候为0: ${empty user.name}<br/>

<h1>EL表达式获取Cookie中的数据</h1>

<%
    Cookie[] cookies = request.getCookies();
    String value = null;
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("JSESSIONID")) {
            value = cookie.getValue();
        }
    }
    if (null == value) value = "";
%>
使用JSP脚本获取JSESSION信息<%=value%><br/>
使用EL表达式获取JSESSION信息${cookie.JSESSIONID.value}<br/>

</body>
</html>
