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
<title></title>
</head>
<body>
<div class="title" id="searchresult">搜索结果</div><br/>
<div class="introduction"></div>
<div class="subcontent">
 <div class="row" style="text-align:center;background-color: purple;color:white;">
  <div class="col-md-2" id="searchNum">序号</div>
  <div class="col-md-2" id="searchName" >名称</div>
  <div class="col-md-8" id="searchDescription">简介</div>
 </div>
 <c:forEach var="item" items="${list}" varStatus="s">
	 <div class="row">
	  <div class="col-md-2" style="text-align:center;"><fmt:formatNumber type="number" value="${s.index+1}" /> </div>
	  <div class="col-md-2" style="text-align:center;"><a href="${basePath}${type}/queryarticle/${item.id}.do"><c:out value="${item.name}"/></a></div>
	  <div class="col-md-8"><c:out value="${item.description}" escapeXml="false"/></div>
	 </div>
 </c:forEach>
</div>
</body>
</html>