<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><c:out value="${material.name}"/></title>
<link rel="stylesheet" href="../../css/material.css" media="screen">
<script src="../../js/ckeditor/ckeditor.js"></script>
<script src="../../js/util.js"></script>
</head>
<body>
<div class="download"><a>下载</a></div>
<div class="title"><c:out value="${material.name}"/></div>
<div class="introduction"><c:out value="${material.description}" escapeXml="false"/></div>
<div>
	<div class="subtitle">讨论区</div>
	<div class="content">
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
			<div>评论你的观点：</div>
			<form id="submit_form">
	            <textarea name="editor" id="editor" rows="10" cols="80">
	                This is my textarea to be replaced with CKEditor.
	            </textarea> 
	            <input type="submit" value="提交" class="submit_btn"/>
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
    	            request.post("../insertComment/${material.id}.do", {
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