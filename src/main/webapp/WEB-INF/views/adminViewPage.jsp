<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
	integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
	crossorigin="anonymous"></script>
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
<body>
	<h2>Welcome ${sessionScope.adminName}!</h2>
	<button class="collapsible">Create New Category</button>
	<div class="content">
		<form action="/findmyroomie/admin/createCategory.htm"
			class="form-horizontal" method="post">
			<div class="form-group">
				<label for="Category Name" class="col-sm-4 control-label">Please
					Enter a Category Name:</label>
				<div class="col-sm-8">
					<input type="text" name="categoryName" value="" /> <br /> <label
						for="Category Name" class="col-sm-4 control-label">Please
						Enter a Category Description:</label>
					<div class="col-sm-8">
						<input type="text" name="categoryDesc" value="" />
					</div>
					<input type="submit" value="Submit">
				</div>
			</div>
			<br />
		</form>
	</div>


	<button class="collapsible">Show Categories</button>
	<div class="content">
		<table>
			<tr>
				<th>Name:</th>
				<th>Description:</th>
			</tr>
			<c:forEach var="category" items="${sessionScope.categoryList}">
				<tr>
					<td>${category.categoryName}</td>
					<td>${category.categoryDescript}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<button class="collapsible">Show All Posts</button>
	<div class="content">
		<table>
			<tr>
				<th>Title</th>
				<th>Description</th>
				<th>Interested Count</th>
				<th>Seeker ID</th>
				<th>Want to remove?</th>
			</tr>
			<c:forEach var="post" items="${sessionScope.postList}">
				<tr>
					<td>${post.title}</td>
					<td>${post.description}</td>
					<td>${post.interestedCount}</td>
					<td>${post.seekerId}</td>
					<td>
						<form action="/findmyroomie/admin/removePost.htm"
							class="form-horizontal" method="post">
							<input type="hidden" name="postTitle" value=${post.title}>
							<input type="submit" value="Remove">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<button class="collapsible">Show Users</button>
	<div class="content">
		<table>
			<tr>
				<th>First name:</th>
				<th>Last Name:</th>
				<th>Email ID:</th>
				<th>Want to remove?</th>
			</tr>
			<c:forEach var="user" items="${sessionScope.userList}">
				<tr>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.emailId}</td>
					<td>
						<form action="/findmyroomie/admin/removeUser.htm"
							class="form-horizontal" method="post">
							<input type="hidden" name="userId" value=${user.userId}>
							<input type="submit" value="Remove">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<button class="collapsible">Show Seekers</button>
	<div class="content">
		<table>
			<tr>
				<th>First name:</th>
				<th>Last Name:</th>
				<th>Email ID:</th>
				<th>Want to remove?</th>
			</tr>
			<c:forEach var="seeker" items="${sessionScope.seekerList}">
				<tr>
					<td>${seeker.firstName}</td>
					<td>${seeker.lastName}</td>
					<td>${seeker.emailId}</td>
					<td>
						<form action="/findmyroomie/admin/removeSeeker.htm"
							class="form-horizontal" method="post">
							<input type="hidden" name="seekerId" value=${seeker.seekerId}>
							<input type="submit" value="Remove">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br />
	<br />
	<form action="/findmyroomie/logout.htm" class="form-horizontal"
		method="post">
		<div class="form-group">
			<input type="submit" value="Logout">
		</div>
	</form>
	<form action="/findmyroomie/admin/adminCont.htm" class="form-horizontal"
		method="get">
		<div class="form-group">
			<input type="submit" value="Refresh">
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