<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>商品管理</title>
		<meta charset="utf-8"/>
		<link href="${ pageContext.request.contextPath }/css/managestyle.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<div class="top">
		<h1>商品管理</h1>
	</div>	
	<div class="content">
		<div class="left">			
			<%@ include file = "_left.jsp" %>
		</div>
	<div class="right">	
			<a href="${pageContext.request.contextPath}/admin/addprod">添加商品</a>
	</div>
	<table width="799" height="80" border="1" cellpadding="0" cellspacing="0" bordercolor="#d8d8d8">
				<tr>
					<th width="276">商品图片</th>
					<th width="247">商品名称</th>
					<th width="231">商品单价</th>
					<th width="214">销售数量</th>
					<th width="232">库存数量</th>
					<th width="232">修改</th>
					<th width="232">删除</th>
				</tr>
				<c:forEach items="${products}" var="prod">
				<tr>
					<td><img src="${ pageContext.request.contextPath }${prod.imgurl}" 
					width="90" height="90" class="prodimg" /></td>
					<td>${prod.name }</td>
					<td>${prod.price }元</td>
					<td>${prod.soldnum }件</td>
					<td>${prod.pnum }件</td>
					<td>修改</td>
					<td><a href="${ pageContext.request.contextPath }/admin/delprod?id=${prod.id}">删除</a></td>
				</tr>				
				</c:forEach>
			</table>
	
	</div>		
	<a href="${pageContext.request.contextPath}/poiexcel">添加商品</a>
	</body>	
</html>