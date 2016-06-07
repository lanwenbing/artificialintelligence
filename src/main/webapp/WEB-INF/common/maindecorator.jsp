<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>
<sitemesh:write property='title' />
</title>
<sitemesh:write property='head' />
<link rel="stylesheet" href="../js/dojo/dijit/themes/claro/claro.css" media="screen">
<link href="../css/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/ai.css" media="screen">
<link rel="stylesheet" href="../../js/dojo/dijit/themes/claro/claro.css" media="screen">
<link href="../../css/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="../../css/bootstrap-3.3.6/js/bootstrap.min.js"></script>
<script src="../css/bootstrap-3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../../css/ai.css" media="screen">
<script type="text/javascript">
var dojoConfig = {
	    async: true,
	    parseOnLoad:true,
	    baseUrl: '/ai/js/dojo',
	    packages: [
	        'dojo',
	        'dijit',
	        'dojox'
	    ]
	};
</script>
<script src="../js/dojo/dojo/dojo.js"></script>
<script src="../../js/dojo/dojo/dojo.js"></script>
<script type="text/javascript">
	require(["dojo/parser", "dijit/layout/BorderContainer", "dijit/layout/TabContainer",
	         "dijit/layout/ContentPane"]);
</script>
</head>
<body  class="claro">
		<div class="headbar">
			<nav class="navbar navbar-default" role="navigation">
			  <!-- Brand and toggle get grouped for better mobile display -->
			  <div class="navbar-header">
			    <a class="navbar-brand" href="#">AI</a>
			  </div>
			  <!-- Collect the nav links, forms, and other content for toggling -->
			  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			    <ul class="nav navbar-nav navbar-right">
			    	<li>
			    		<form class="navbar-form navbar-left" role="search">
			      			<div class="form-group">
			        		<input type="text" class="form-control" placeholder="搜索">
			      			</div>
			      			<button type="submit" class="btn btn-default">提交</button>
			    		</form>
			    	</li>
			      	<li><a href="../register/register.do">注册</a></li>
			      	<li><a href="../login/userlogin.do">登陆</a></li>
			    </ul>
			  </div><!-- /.navbar-collapse -->
			</nav>
		</div>	
	<div class="content">
		 <sitemesh:write property='body' />
	</div>
	<div class="footer">
		<div class="navis">
			该网站提供的内容仅用于培训。我们不保证内容的正确性。通过使用本站内容随之而来的风险与本站无关。该网站简体中文版的所有内容仅供测试，对任何法律问题及风险不承担任何责任。
当使用本站时，代表您已接受了本站的使用条款和隐私条款。版权所有，保留一切权利。
		</div>
	</div>
</body>
</html>