<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>
<sitemesh:write property='title' />
</title>
<sitemesh:write property='head' />
<link rel="stylesheet" href="../js/dojo/dijit/themes/claro/claro.css" media="screen">
<link href="../css/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/ai.css" media="screen">
<link rel="stylesheet" href="../../js/dojo/dijit/themes/claro/claro.css" media="screen">
<link href="../../css/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">
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
	<div>
		 <sitemesh:write property='body' />
	</div>
</body>
</html>