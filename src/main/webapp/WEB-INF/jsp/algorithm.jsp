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
<title><c:out value="${algorithm.algorithmName}"/>分析和学习</title>
<link rel="stylesheet" href="${basePath}/css/algorithm.css" media="screen">
</head>
<body>
<div class="title"><c:out value="${algorithm.algorithmName}"/>分析和学习</div>
<div class="introduction"><c:out value="${category.algorithmDescription}" escapeXml="false"/></div>
<div class="subcontent">
	<div>
		<div class="subtitle" id="article">1. 文章</div>
		<table class="table table-striped table-bordered">
			<tr>
				<th class="col-left" id="articlenameandlink">文章名称以及链接</th>
				<th class="col-right" id="articledescription">文章简介</th>
			</tr>
			<c:forEach var="article" items="${articles}">
			<tr>
				<td class="col-left"><a href="${basePath}article/queryarticle/${article.id}.do"><c:out value="${article.name}"/></a></td>
				<td class="col-right"><c:out value="${article.description}" escapeXml="false"/></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<div class="subtitle" id="material">2. 材料</div>
		<table class="table table-striped table-bordered">
			<tr>
				<th class="col-left" id="materialnameandlink">材料名称以及链接</th>
				<th class="col-right" id="materialdescription">材料简介</th>
			</tr>
			<c:forEach var="material" items="${materials}">
			<tr>
				<td class="col-left"><a href="${basePath}material/querymaterial/${material.id}.do"><c:out value="${material.name}"/></a></td>
				<td class="col-right"><c:out value="${material.description}" escapeXml="false"/></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<div class="subtitle" id="system">3. 系统</div>
		<table class="table table-striped table-bordered">
			<tr>
				<th class="col-left" id="systemnameandlink">系统名称以及链接</th>
				<th class="col-right" id="systemdescription">系统简介</th>
			</tr>
			<c:forEach var="system" items="${systems}">
			<tr>
				<td class="col-left"><a href="${basePath}system/querysystem/${system.id}.do"><c:out value="${system.name}"/></a></td>
				<td class="col-right"><c:out value="${system.description}" escapeXml="false"/></td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>