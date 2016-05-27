<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><c:out value="${category.name}"/></title>
<link rel="stylesheet" href="../../css/category.css" media="screen">
</head>
<body>
<div class="title"><c:out value="${category.name}"/></div>
<div class="introduction"><c:out value="${category.description}" escapeXml="false"/></div>
<div class="content">
	<table class="table table-striped table-bordered">
		<tr>
			<th class="col-left">算法名称以及链接</th>
			<th class="col-right">算法简介</th>
		</tr>
		<c:forEach var="algorithm" items="${algorithmList}">
		<tr>
			<td class="col-left"><a href="../../algorithm/queryalgorithm/${algorithm.id}.do"><c:out value="${algorithm.algorithmName}"/></a></td>
			<td class="col-right"><c:out value="${algorithm.algorithmDescription}" escapeXml="false"/></td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>