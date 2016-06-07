<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录</title>
<link rel="stylesheet" href="../css/artificialintelligent.css" media="screen">
</head>
<body>
	<div class="title">登录</div>
    <div class="category">
	    <form>
			<table class="table">
				<tr>
					<td class="col-left">用户名：</td>
					<td class="col-right"><input type="text" value=""></td>
					<td class="col-right"><label>请输入用户名</label></td>
				</tr>
				<tr>
					<td class="col-left">密码：</td>
					<td class="col-right"><input type="password"></td>
					<td class="col-right"><label>请输入用户名</label></td>
				</tr>
				<tr>
					<td class="col-left"><a>找回密码</a>&nbsp;&nbsp;<a>注册</a></td>
					<td class="col-right"><input type="password"></td>
					<td class="col-right"><label></label></td>
				</tr>
				<tr>
					<td class="col-left"><a>qq登录</a>&nbsp;&nbsp;<a>微信登录</a></td>
					<td class="col-right"><input type="password"></td>
					<td class="col-right"><label></label></td>
				</tr>
			</table>
		</form>
    </div>
</body>
</html>