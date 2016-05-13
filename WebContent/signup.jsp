<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Signup</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
<link href="css/signup.css" rel="stylesheet">
</head>
<body>

<ul class="nav nav-tabs">
  <li role="presentation" ><a href="index.jsp">Home</a></li>
  <li role="presentation"><a href="login.jsp">Login</a></li>
  <li role="presentation" class="active"><a href="signup.jsp">Signup</a></li>
</ul>

<div id="alert_warning" style="display:none">
<div class="alert alert-warning alert-dismissible"  role="alert" >
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong>Warning!</strong> <span id="link-pwd"></span>
</div>
</div>

<h2>Sign up</h2>

<div class="input-group" id="username">
<span class="input-group-addon" id="basic-addon1">UserName</span>
<input type="text" name="uname" id="uname" class="form-control"  >
</div>
<div class="input-group" id="password">
<span class="input-group-addon" id="basic-addon1">Password</span>
<input type="password" name="upwd" id="upwd" class="form-control" >
</div>
<div class="input-group" id="emailig">
<span class="input-group-addon" id="basic-addon1">Email</span>
<input type="text" name="email" id="email" class="form-control" >
</div>
<div id="buttons">
<button type="submit" class="btn btn-success btn-submit" name="submit" >Sign up</button>
<button type="reset" class="btn btn-danger" name="reset" >Reset</button>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/md5.min.js"></script>
<script src="js/signup.js"></script>
</body>
</html>