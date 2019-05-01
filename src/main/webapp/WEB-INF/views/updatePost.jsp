<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Post</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
	integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
	crossorigin="anonymous"></script>
</head>
<body  bgcolor="#C0C0C0">

<div class="content">
  <form enctype="multipart/form-data" method="POST" action="/findmyroomie/seeker/seekerUpdate.htm">
  				
  				<div class="form-group">
					<label for=categoryName class="col-sm-4 control-label">Category	Name: </label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="name"
							 value="${sessionScope.post.category.categoryName}" disabled />
					</div>
				</div>
  				
  				<div class="form-group">
					<label for=postTitle class="col-sm-4 control-label">Post Title: </label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="title"
							 value="${sessionScope.post.title}" disabled />
					</div>
				</div>

				<div class="form-group">
					<label for="last" class="col-sm-4 control-label">Post Description:</label>
					<div class="col-sm-8">
						<input type="descprition" class="form-control" name="descprition"
							value="${sessionScope.post.description}" required="required" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="last" class="col-sm-4 control-label">Status:</label>
					<div class="col-sm-8">
						<input type="radio" name="available" value="true" >Still Available
						<input type="radio" name="available" value="false" checked >I got the roomie
					</div>
				</div>
				
				<div class="col-sm-offset-4 col-sm-8">
				<input type="submit" class="btn btn-success" value="Update my post" />
				</div>
  </form>
</div>

</body>
</html>