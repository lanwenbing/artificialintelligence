<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
pageContext.setAttribute("basePath",basePath); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><c:out value="${category.name}"/></title>
<link rel="stylesheet" href="${basePath}css/category.css" media="screen">
</head>
<body>
<div class="title"><c:out value="${category.name}"/></div>
<div class="introduction"><c:out value="${category.description}" escapeXml="false"/></div>
<div class="subcontent">
	<table class="table table-striped table-bordered">
		<tr>
			<th class="col-left" id="algorithmNameAndLink">算法名称以及链接</th>
			<th class="col-right" id="algorithmDescription">算法简介</th>
		</tr>
		<c:forEach var="algorithm" items="${algorithmList}">
		<tr>
			<td class="col-left"><a href="${basePath}algorithm/queryalgorithm/${algorithm.id}.do"><c:out value="${algorithm.algorithmName}"/></a></td>
			<td class="col-right"><c:out value="${algorithm.algorithmDescription}" escapeXml="false"/></td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>