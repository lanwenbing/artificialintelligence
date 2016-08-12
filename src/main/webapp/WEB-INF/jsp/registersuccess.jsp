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
<title id="registertitle">注册</title>
<link rel="stylesheet" href="${basePath}css/register.css" media="screen">
</head>
<body>
	<div class="title" id="registersuccesstitle">注册</div>
    <div class="category" id="registersuccessnotification">
	    注册成功！  
	  <br/><br/><br/><br/><br/><br/>
    </div>
    <script>
        
    </script>
</body>
</html>