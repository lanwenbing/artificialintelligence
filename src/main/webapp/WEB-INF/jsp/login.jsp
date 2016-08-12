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
<title>登录</title>
<link rel="stylesheet" href="${basePath}css/login.css" media="screen">
</head>
<body>
	<div class="title" id="loginlogin">登录</div>
    <div class="category login_width">
		<form role="form" class="form-horizontal" action="../login/userlogin.do">
		    <label class="notification"></label>
			<div class="form-group">
				<label class="col-sm-3 control-label" id="usernamelogin">用户名：</label>
				<div class="col-sm-9">
					<input name="username" type="text" class="form-control" placeholder="6位以上的用户名" aria-describedby="basic-addon1" id="usernamelogininput">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" id="passwordlogin">密码：</label>
				<div class="col-sm-9">
					<input name="password" type="password" class="form-control" placeholder="8位以上的密码，包括数字，字母" aria-describedby="basic-addon1" id="passwordlogininput">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12 center">
					<input type="submit" class="btn btn-primary" role="button" value="提交" id="submitlogin">
				</div>
			</div>
			<div class="form-group center">
				<label class="col-sm-12 control-label center"><a id="findpassword">找回密码</a></label>
			</div>
			<div class="form-group center">
				<label class="col-sm-12 control-label center"><a id="weixinlogin">微信登录</a>&nbsp;&nbsp;<a id="qqlogin">QQ登录</a></label>
			</div>
		</form>
    </div>
</body>
</html>