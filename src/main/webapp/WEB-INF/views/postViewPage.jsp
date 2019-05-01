<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.neu.findmyroomie.pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Posts</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
	integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
	crossorigin="anonymous"></script>
</head>
<body bgcolor="#C0C0C0">
	<h2>Here You Go! list of all the available posts from your
		selected Category.</h2>
	<c:forEach var="post" items="${sessionScope.allPostList}">
		<ul>
			<li>[<a
				href="/findmyroomie/user/postData?selectedPost=${post.title}">${post.title}</a>]
			</li>
		</ul>
	</c:forEach>
	<h4>Want to have all posts in single file?</h4>
	<a
		href="/findmyroomie/user/getPDFView?selectedCategory=${sessionScope.categoryName}">View/Download
		PDF</a>
	<br>
	<br>
	<form action="/findmyroomie/user/categoryList.htm" method="get">
		<input type="submit" value="Explore Categories Again!" />
	</form>
</body>
</html>