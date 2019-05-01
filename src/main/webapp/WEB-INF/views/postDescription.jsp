<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page import="com.neu.findmyroomie.pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
	integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Details</title>
</head>
<body  bgcolor="#C0C0C0">
	<h4>Post details! </h4>
	<c:set var="post" value= "${sessionScope.postList}"/>
	<b><c:out value="${post.getTitle()}"/></b><br/>
	
           
            <b>Title:</b> <c:out value="${post.getTitle()}"/><br/>
            <b>Description:</b> <c:out value="${post.getDescription()}"/><br/> 
 			<b>Number of people interested:</b> <c:out value="${post.getInterestedCount()}"/><br/><br/>
 			
 			<img src="<c:url value="C:/Users/parkh/Desktop/WebtoolsFinalProjectImages/${post.getPostImage()}"/>" alt="image"
                             width="300px" height="200px"/></br><br/>
 	<b>Previous comments on this post:</b>
 	<c:forEach var="comment" items="${sessionScope.commentList}">
 		<li><c:out value="${comment.getComment()}"/></li>
 	</c:forEach></br><br/>
 	
 	<h4>This is your Chance! Mark it as interested or Comment on it and be a perfect Roomie! </h4>
 	<form action="/findmyroomie/user/addComment" method="post">
 		Enter your Comment:<input type="text" name="comment"/>
 		<input type="submit" value="Add"/>
 	</form>
 	<form action="/findmyroomie/user/markAsInterested.htm" class="form-horizontal"
		method="post">
		<div class="form-group">
			Let seeker's Know that you are interested in this post: <input type="submit" value="Mark it as interested">
		</div>
	</form></br><br/>
	<form action="/findmyroomie/user/getPost.htm" method="get">
		<input type="submit" value="Explore Posts List Again!" />
	</form>
	<form action="/findmyroomie/user/categoryList.htm" method="get">
		<input type="submit" value="Explore Categories Again!" />
	</form>
</body>
</html>