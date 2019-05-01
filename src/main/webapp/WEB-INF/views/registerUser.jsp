<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
	integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
	crossorigin="anonymous">

function isPasswordMatch() {
    var password = $("#txtNewPassword").val();
    var confirmPassword = $("#txtConfirmPassword").val();

    if (password != confirmPassword) $("#divCheckPassword").html("Passwords do not match!");
    else $("#divCheckPassword").html("Passwords match.");
}

$(document).ready(function () {
    $("#txtConfirmPassword").keyup(isPasswordMatch);
});
</script>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	<!--javascript -->
	<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.bundle.min.js"></script>
	<!-- to make responsive-->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<style type="text/css">
		body
		{
			background-color: #C0C0C0;
			font-size: 16px;
			font-family: Calibri;
		}
		.wrapper{
			text-align :center;
		}
		.btn{
			position:absolute;
			top:102%;
		}
		.myreset{
			position:absolute;
			top:102%;
			left:65%;
		}
	</style>
</head>
<body  bgcolor="#C0C0C0">
<form class="col-8" action="register" method="post">

	<div class="row form-group">
		<div class="col-3">
		<label for="gender">User Type:</label>
		</div>
		<div class="col-5 form-check-inline">
			 <label class="radio-inline control-label">
				<input type="radio" name="usertype" value="Seeker" class="form-check-input">Looking for roomies(Seekers)
			</label>
			<label class="radio-inline control-label">
			<input type="radio" name="usertype" value="User" class="form-check-input" checked >Looking for apartments (Users)
			</label>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-3">
		<label for="firstName">First name:</label>
		</div>
		<div class="col-5">
		<input type="text" name="firstName" placeholder="firstName" class="form-control" required>
		</div>
	</div>
		<div class="row form-group">
		<div class="col-3">
		<label for="lastName">Last name:</label>
		</div>
		<div class="col-5">
		<input type="text" name="lastName" placeholder="lastName" class="form-control" required>
		</div>
	</div>
		<div class="row form-group">
		<div class="col-3">
		<label for="email">Email id:</label>
		</div>
		<div class="col-5">
		<input type="email" name="emailId" placeholder="emailid" class="form-control" required>
		</div>
	</div>
	
	</div>
		<div class="row form-group">
		<div class="col-3">
		<label for="phone">Phone Number:</label>
		</div>
		<div class="col-5">
		<input type="text" name="phone" placeholder="PhoneNumber" class="form-control" required>
		</div>
	</div>
	
	<div>
	<p>Your Actual User ID will be email to you soon! check your inbox</p>
		<div class="row form-group">
		<div class="col-3">
		<label for="username">User id:</label>
		</div>
		<div class="col-5">
		<input type="text" name="username" placeholder="username" class="form-control" required>
		<span id="isE"></span>
		</div>
	</div>
	
	<div class="row form-group">
		<div class="col-3">
		<label for="password">Password:</label>
		</div>
		<div class="col-5">
		<input type="password" name="password"  placeholder="password" class="form-control" id="txtNewPassword" required>
		</div>
	</div>

	<div class="row form-group">
		<div class="col-3">
		<label for="confirmpassword">Confirm Password:</label>
		</div>
		<div class="col-5">
		<input type="password" name="confirmpassword"
		placeholder="confirm password" class="form-control"  id="txtConfirmPassword" onChange="isPasswordMatch()" required>
		</div>
	</div>
	<div id="divCheckPassword"></div>
	<div class= "wrapper">
	<input type="submit" class="btn btn-primary" value="Submit">
	<button type="reset" class="myreset btn btn-secondary">Reset</button></div>
</form>

<form action="/findmyroomie/" method="get">
	<input type="submit" value="Home Page!" />
</form>
</body>
</html>