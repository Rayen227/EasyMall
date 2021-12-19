<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/login.css"/>
		<title>EasyMall欢迎您登录后台系统</title>
	</head>
	<body>

		<h1>欢迎登录后台系统</h1>
		<form action="${ pageContext.request.contextPath }/admin/adminlogin" method="POST">			
			<table>
				<tr>	
				 	
					<td class="tdx">用户名：</td>
					<td><input type="text" name="username" value="admin"/></td>
				</tr>
				<tr>
					<td class="tdx">密&nbsp;&nbsp; 码：</td>
					<td><input type="password" name="password" value="123"/></td>
				</tr>
				
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="登录"/>
					</td>	
				</tr>
				<tr><td colspan="2" style="color:red;text-align:center;font-height:bold;">
				${message}</td></tr>
			</table>
		</form>		
	<br><br>

	</body>
</html>

