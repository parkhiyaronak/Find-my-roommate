<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.collapsible {
	background-color: #777;
	color: white;
	cursor: pointer;
	padding: 18px;
	width: 100%;
	border: none;
	text-align: left;
	outline: none;
	font-size: 15px;
}

.active, .collapsible:hover {
	background-color: #555;
}

.content {
	padding: 0 18px;
	display: none;
	overflow: hidden;
	background-color: #f1f1f1;
}
</style>
</head>
<body bgcolor="#C0C0C0">
	<h2>Welcome Back ${sessionScope.seekerName}</h2>
	<p>Please select any of the following option:</p>

	<button class="collapsible">Add New posts</button>
	<div class="content">
		<form:form commandName="post" enctype="multipart/form-data"
			class="form-horizontal">

			<div class="form-group">
				<label for="categoryName" class="col-sm-4 control-label">Category
					Name:</label>
				<div class="col-sm-8">
					<select name="categoryName">
						<c:forEach items="${sessionScope.categoryList}" var="category">
							<option value="${category.categoryName}">${category.categoryName}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for=postName class="col-sm-4 control-label">Post
					Title: </label>
				<div class="col-sm-8">
					<form:input type="text" class="form-control" id="title"
						path="title" placeholder="Post Title" required="required" />
				</div>
			</div>


			<div class="form-group">
				<label for="Product Description" class="col-sm-4 control-label">Home
					Description:</label>
				<div class="col-sm-8">
					<form:input type="text" class="form-control" id="prodDescript"
						path="description" placeholder="Product Description"
						required="required" />
				</div>
			</div>


			<div class="form-group">
				<label for="addressLine1" class="col-sm-4 control-label">Home
					street1:</label>
				<div class="col-sm-8">
					<input type="text" name="addressLine1" placeholder="street"
						class="form-control" required="required">
				</div>
			</div>

			<div class="form-group">
				<label for="addressLine2" class="col-sm-4 control-label">Home
					street2:</label>
				<div class="col-sm-8">
					<input type="text" name="addressLine2" placeholder="street"
						class="form-control" required="required">
				</div>
			</div>

			<div class="form-group">
				<label for="city" class="col-sm-4 control-label">city:</label>
				<div class="col-sm-8">
					<input type="text" name="city" placeholder="city"
						class="form-control" required="required">
				</div>
			</div>

			<div class="form-group">
				<label for="state" class="col-sm-4 control-label">state:</label>
				<div class="col-sm-8">
					<input type="text" name="state" placeholder="state"
						class="form-control" required="required">
				</div>
			</div>

			<div class="form-group">
				<label for="zip" class="col-sm-4 control-label">zip:</label>
				<div class="col-sm-8">
					<input type="text" name="zip" placeholder="zip"
						class="form-control" required="required">
				</div>
			</div>

			<div class="form-group">
				<label for="Country" class="col-sm-4 control-label">Country:</label>
				<div class="col-sm-8">
					<input type="text" name="Country" placeholder="Country"
						class="form-control" required="required">
				</div>
			</div>

			<div class="form-group">
				<label for="photo" class="col-sm-4 control-label">Photo: </label>
				<div class="col-sm-4">
					<input type="file" id="photo" name="photo" required="required" />
					<p class="help-block">Recent photo in JPG format</p>
				</div>
			</div>

			<div class="col-sm-offset-4 col-sm-8">
				<input type="submit" class="btn btn-success" value="Add Post" />
			</div>
		</form:form>
	</div>
	<button class="collapsible">Update a Post</button>
	<div class="content">
		<form action="/findmyroomie/seeker/seekerUpdate.htm"
			enctype="multipart/form-data" class="form-horizontal">
			<div class="form-group">
				<label for="Post Title" class="col-sm-4 control-label">Please
					Enter a Post Title:</label>
				<div class="col-sm-8">
					<input type="text" name="title" value="" />
				</div>
				<input type="submit" value="Submit">
			</div>
			<br />
		</form>
	</div>
	<br />
	<br />
	<form action="/findmyroomie/logout.htm" class="form-horizontal"
		method="post">
		<div class="form-group">
			<input type="submit" value="Logout">
		</div>
	</form>

	<script>
		var coll = document.getElementsByClassName("collapsible");
		var i;

		for (i = 0; i < coll.length; i++) {
			coll[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var content = this.nextElementSibling;
				if (content.style.display === "block") {
					content.style.display = "none";
				} else {
					content.style.display = "block";
				}
			});
		}
	</script>


</body>
</html>