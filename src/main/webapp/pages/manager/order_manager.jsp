<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

	<%--  静态包含base标签,css样式,jQuery文件 --%>
	<%@include file="/pages/common/head.jsp"%>

</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

			<%-- 静态包含manager管理，模块的菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
			
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>用户名</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:set var="count" scope="page" value="-1" target=""/>
			<c:forEach items="${sessionScope.orders}" var="order"  >
				<tr>

					<td>${sessionScope.nameList[count = count+1]}</td>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td><a href="orderServletorderDetails?orderId=${order.orderId}">查看详情</a></td>
					<td>
						<c:if test="${order.status == 0}"><a href="orderServletsendOrders?orderId=${order.orderId}">点击发货</a></c:if>
						<c:if test="${order.status == 1}">已经发货</c:if>
						<c:if test="${order.status == 2}">已经收货</c:if>

					</td>
				</tr>

			</c:forEach>

		</table>
	</div>


	<%--	静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>