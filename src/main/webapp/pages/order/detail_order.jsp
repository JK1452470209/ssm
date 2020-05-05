<%--
  Created by IntelliJ IDEA.
  User: MI
  Date: 2020/4/11
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>

    <%--  静态包含base标签,css样式,jQuery文件 --%>
    <%@include file="/pages/common/head.jsp"%>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif" >
    <span class="wel_word">订单详情</span>

    <%-- 静态包含，登录成功之后的菜单--%>
    <%@include file="/pages/common/login_success_menu.jsp"%>

</div>

<div id="main">

    <table>
        <tr>
            <td>名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>总价</td>
            <td>订单编号</td>
        </tr>
        <%--			<c:forEach items="${sessionScope.cart.items}" var="entry">--%>
        <c:forEach items="${sessionScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
                <td>${orderItem.orderId}</td>
            </tr>
        </c:forEach>
    </table>


</div>


<%--	静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>


</body>
</html>