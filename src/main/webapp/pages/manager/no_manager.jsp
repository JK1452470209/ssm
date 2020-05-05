<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>

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
			<span class="wel_word">后台管理系统</span>

		<%-- 静态包含manager管理，模块的菜单--%>
<%--		<%@include file="/pages/common/manager_menu.jsp"%>--%>
		<div>
<%--			<a href="manager/bookServlet?action=page">图书管理</a>--%>
<%--			<a href="pages/manager/order_manager.jsp">订单管理</a>--%>
			<a href="index.jsp">返回商城</a>
		</div>

	</div>
	
	<div id="main">
		<h1>您的权限不足!如想进入后台可联系程序员小坤!</h1>
	</div>


	<%--	静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>