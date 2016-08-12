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
<title>
<sitemesh:write property='title' />
</title>
<sitemesh:write property='head' />
<link rel="stylesheet" href="${basePath}css/ai.css" media="screen">
<link rel="stylesheet" href="${basePath}js/dojo/dijit/themes/claro/claro.css" media="screen">
<link href="${basePath}css/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="${basePath}js/jquery.min.js"></script>
<script src="${basePath}css/bootstrap-3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">
var dojoConfig = {
	    async: true,
	    parseOnLoad:true,
	    baseUrl: '${basePath}js/dojo',
	    locale:'${country}',
	    packages: [
	        'dojo',
	        'dijit',
	        'dojox'
	    ]
	};
</script>
<script src="${basePath}js/dojo/dojo/dojo.js"></script>
<script src="${basePath}js/util.js"></script>
<script type="text/javascript">
	require(["dojo/parser", "dijit/layout/BorderContainer", "dijit/layout/TabContainer",
	         "dijit/layout/ContentPane"]);
	require(["dojo/i18n!locale/label/nls/maindecorator",
	         "dojo/i18n!locale/label/nls/register",
	         "dojo/i18n!locale/label/nls/login",
	         "dojo/i18n!locale/label/nls/algorithm",
	         "dojo/i18n!locale/label/nls/article",
	         "dojo/i18n!locale/label/nls/artificialintelligent",
	         "dojo/i18n!locale/label/nls/category",
	         "dojo/i18n!locale/label/nls/material",
	         "dojo/i18n!locale/label/nls/registersuccess",
	         "dojo/i18n!locale/label/nls/system",
	         "dojo/dom-style", "dojo/dom"],  
	         function(i18nmaindecorator,i18nregister,i18nlogin,i18nalgorithm,i18narticle,
	        		 i18nartificialintelligent,i18ncategory,i18nmaterial,i18nregistersuccess,i18nsystem,style,dom){
		localization(i18nmaindecorator);
		localization(i18nregister);
		localization(i18nlogin);
		localization(i18nalgorithm);
		localization(i18narticle);
		localization(i18nartificialintelligent);
		localization(i18ncategory);
		localization(i18nmaterial);
		localization(i18nregistersuccess);
		localization(i18nsystem);
		
		 var username="${username}"; 
		 var islogin = "${islogin}";
		 var usernameLabel=dom.byId("username");
		 if(username!=undefined&&username!=null&&username!=""&&islogin!=undefined&&islogin!=null&&islogin!=""){
			 var register=dom.byId("register");
			 var login=dom.byId("login");
			 usernameLabel.innerHTML=username;
			 register.style.display = "none";
			 login.style.display = "none";
		 }else{
			 var afterlogin=dom.byId("afterlogin");
			 var logout=dom.byId("logout");
			 afterlogin.style.display = "none";
			 logout.style.display = "none";
		 }
	});
</script>
</head>
<body  class="claro">
	<div class="headbar">
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="${basePath}artificialintelligent/getcategory.do" ><img alt="AI" src="${basePath}logo.png" style="height:30px;width:30px;"></a>
		    </div>
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		      	<li><a href="${basePath}artificialintelligent/getcategory.do" id="homepage">主页</a></li>
		      </ul>
		      <form class="navbar-form navbar-left" role="search">
		 		<div class="form-group">
		   			<input type="text" class="form-control"  id="search" value="搜索">
		 		</div>
		 		<button type="submit" class="btn btn-default" id="submit">提交</button>
			  </form>
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="${basePath}register/register.do" id="register">注册</a></li>
				<li><a href="${basePath}login/transfertologin.do" id="login">登陆</a></li>
				<li id="afterlogin"><a><span id="greeting">您好, </span><span id="username"></span></a></li>
				<li><a href="${basePath}login/logout.do" id="logout">退出</a></li>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" ><span id="language_change">语言切换</span><span class="caret" ></span></a>
		          <ul class="dropdown-menu">
		            <li><a onclick="changeLanguage('zh-cn')" href=" javascript:void(0);" id="chinese">中文</a></li>
		            <li><a onclick="changeLanguage('en')" href=" javascript:void(0);" id="english">英文</a></li>
		          </ul>
		        </li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
	</div>	
	<div class="content">
		 <sitemesh:write property='body' />
	</div>
	<div class="footer">
		<div class="navis" id="footer">
			该网站提供的内容仅用于培训。我们不保证内容的正确性。通过使用本站内容随之而来的风险与本站无关。该网站简体中文版的所有内容仅供测试，对任何法律问题及风险不承担任何责任。
当使用本站时，代表您已接受了本站的使用条款和隐私条款。版权所有，保留一切权利。
		</div>
	</div>
</body>
</html>