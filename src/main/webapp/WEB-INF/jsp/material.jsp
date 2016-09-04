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
<title><c:out value="${material.name}"/></title>
<link rel="stylesheet" href="${basePath}css/material.css" media="screen">
<script src="${basePath}js/ckeditor/ckeditor.js"></script>
<script src="${basePath}js/util.js"></script>
</head>
<body>
<div class="download"><a id="download">下载</a></div>
<div class="title"><c:out value="${material.name}"/></div>
<div class="introduction"><c:out value="${material.description}" escapeXml="false"/></div>
<div class="discuss">
	<div class="subtitle" id="discussarea">讨论区</div>
	<div class="subcontent">
		<div id="comments">
			<c:forEach var="comment" items="${comments}">
				<div>
					<div><fmt:formatDate type="both"  pattern="yyyy-MM-dd HH:mm:ss"  value="${comment.tm}" /></div>
					<div><c:out value="${comment.content}" escapeXml="false"/></div>
				</div>
			</c:forEach>
		</div>
		<br/>
		<div>
			<div id="view">评论你的观点：</div>
			<form id="submit_form">
	            <textarea name="editor" id="editor" rows="10" cols="80">
	            </textarea> 
	            <input type="submit" value="提交" class="submit_btn" id="materialsubmit"/>
	        </form>
	   	</div>
	</div>
</div>
<script>
    CKEDITOR.replace( 'editor' );
    CKEDITOR.config.width='600';
    CKEDITOR.config.height='100';
    
    require(["dojo/dom", "dojo/on", "dojo/request", "dojo/dom-form", "dojo/json"],
    	    function(dom, on, request, domForm, JSON){

    	        var form = dom.byId('submit_form');
    	        var commentsDiv = dom.byId('comments');
    	        // Attach the onsubmit event handler of the form
    	        on(form, "submit", function(evt){

    	            // prevent the page from navigating after submit
    	            evt.stopPropagation();
    	            evt.preventDefault();
    	            var comment = CKEDITOR.instances.editor.getData();
    	            // Post the data to the server
    	            request.post("${basePath}material/insertcomment/${material.id}.do", {
    	                // Send the username and password
    	                data: {
    	                	comment:comment,
    	                },
    	                handleAs: "json"
    	            }).then(function(response){
    	            	if(response.status==true){
    	            		commentsDiv.innerHTML = "";
    	                	for( var i=0; i< response.data.length; i++){
    	                		var time = new   Date(response.data[i].tm);
    	                		commentsDiv.innerHTML+="<div><div>"+time.pattern("yyyy-MM-dd HH:mm:ss")+"</div><div>"+response.data[i].content+"</div></div>";
    	                	}
    	                };
    	            });
    	        });
    	    }
    	);
</script>
</body>
</html>