<%--
  Created by IntelliJ IDEA.
  User: MI
  Date: 2020/4/5
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="orderServletmyOrders?id=${sessionScope.user.id}">我的订单</a>
    <a href="userServletlogout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>

