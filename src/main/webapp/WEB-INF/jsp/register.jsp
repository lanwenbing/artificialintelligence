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
<title>注册</title>
<link rel="stylesheet" href="${basePath}css/register.css" media="screen">
</head>
<body>
	<div class="title" id="registerreg">注册</div>
    <div class="category">
	    <form role="form" class="form-horizontal" action="../register/adduser.do">
				<div class="form-group">
					<label class="col-sm-3 control-label" id="usernamereg">用户名：</label>
					<div class="col-sm-4">
						<input name="username" type="text" class="form-control" placeholder="6位以上的用户名" aria-describedby="basic-addon1" id="usernamereginput">
					</div>
					<div class="col-sm-5">
						<label class="notification" id="usernamenotification">请输入6位以上的用户名</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" id="mobilephonenumreg">手机号码：</label>
					<div class="col-sm-4">
						<input name="mobilephone" type="text" class="form-control" placeholder="手机号码" aria-describedby="basic-addon1" id="mobilephonenumreginput">
					</div>
					<div class="col-sm-5">
						<label class="notification" id="mobilephonenumnotification">请输入正确的手机号码</label>
					</div>					
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" id="emailreg">电子邮件：</label>
					<div class="col-sm-4">
						<input type="text" name="email" class="form-control" placeholder="电子邮件" aria-describedby="basic-addon1" id="emailreginput">
					</div>
					<div class="col-sm-5">
						<label class="notification" id="emailnotification">请输入正确的电子邮件</label>
					</div>					
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" id="countryreg">国家：</label>
					<div class="col-sm-4">
						<input type="text" name="country" class="form-control" placeholder="国家" aria-describedby="basic-addon1" id="countryreginput">
					</div>
					<div class="col-sm-5">
						<label class="notification" id="countrynotification">请选择国家</label>
					</div>					
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" id="passwordreg">密码：</label>
					<div class="col-sm-4">
						<input name="password" type="password" class="form-control" placeholder="8位以上的密码，包括数字，字母" aria-describedby="basic-addon1" id="passwordreginput">
					</div>
					<div class="col-sm-5">
						<label class="notification" id="passwordnotification">请输入8位以上的密码，包括数字，字母</label>
					</div>					
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" id="repasswordreg">再次确认密码：</label>
					<div class="col-sm-4">
						<input type="password" class="form-control" placeholder="再次确认密码" aria-describedby="basic-addon1" id="repasswordreginput">
					</div>
					<div class="col-sm-5">
						<label class="notification" id="passwordchecknotification">两次密码输入不一致</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12">
						<br/><br/>
						<input type="submit" class="btn btn-primary" role="button" value="提交" id="submitreg">
					</div>
				</div>
		</form>
    </div>
</body>
</html>