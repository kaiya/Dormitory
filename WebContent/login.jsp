<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Login</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-select.min.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="index.jsp">Home</a></li>
		<li class="active"><a href="login.jsp">Login</a></li>
		<li><a href="signup.jsp">Signup</a></li>
	</ul>
<div id="alert_warning" style="display:none">
<div class="alert alert-warning alert-dismissible"  role="alert" >
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong>Warning!</strong> <span id="link-pwd"></span>
</div>
</div>
	<h2>Login</h2>

	<div class="input-group" id="selectpicker">
		<span class="input-group-addon" id="basic-addon1">Login as
			Role:</span> <select class="selectpicker" name="role" id="role">
			<option>User</option>
			<option>Admin</option>
		</select>
	</div>
	<div class="input-group" id="username">
		<span class="input-group-addon" id="basic-addon1">UserName</span> <input
			type="text" name="uname" id ="uname" class="form-control">
	</div>
	<div class="input-group" id="password">
		<span class="input-group-addon" id="basic-addon1">Password</span> <input
			type="password" name="upwd" id="upwd" class="form-control">
				<input type="hidden" name="return_uri" id = "return_uri" value="${sessionScope.return_uri}"/>
	</div>
	<div id="buttons">
		<button type="submit" class="btn btn-success btn-submit" name="submit">Login</button>
		<button type="reset" class="btn btn-danger" name="reset">Reset</button>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/md5.min.js"></script>
	<script src="js/bootstrap-select.min.js"></script>
	<script src="js/login.js"></script>
	<script>
		$(document).ready(function() {
			$('.selectpicker').selectpicker();
		});
	</script>
</body>
</html>