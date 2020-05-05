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
			<span class="wel_word">我的订单</span>

		<%-- 静态包含，登录成功之后的菜单--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
<%--			<c:forEach items="${sessionScope.cart.items}" var="entry">--%>
			<c:forEach items="${sessionScope.orders}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td>
						<c:if test="${order.status == 0}">未发货</c:if>
						<c:if test="${order.status == 1}"><a href="orderServletreceiveOrders?orderId=${order.orderId}">可签收</a></c:if>
						<c:if test="${order.status == 2}">已签收</c:if>

					</td>
					<td><a href="orderServletorderDetails?orderId=${order.orderId}">查看详情</a></td>
				</tr>
			</c:forEach>
		</table>
		
	
	</div>


	<%--	静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>