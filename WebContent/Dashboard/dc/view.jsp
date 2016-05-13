<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dormitory Candidate</title>
<%@include file="/Dashboard/header.html"%>
<%@include file="/Dashboard/footer.html"%>
<link href="/css/bootstrap-select.min.css" rel="stylesheet">
<style>
#selectpicker {
	margin: 0 auto;
	margin-top: 20px;
	margin-bottom: 0px;
}

.bselect {
	width: 200px;
}
</style>
</head>
<body>
	<%@include file="/Dashboard/nav.jsp"%>
	<%@include file="/Dashboard/dc/bstable.jsp"%>
	<script src="/js/bootstrap-table.min.js"></script>
	<script src="/js/bootstrap-select.min.js"></script>
	<script src="/js/logout.js"></script>
	<script>
		$('.selectpicker').selectpicker();
	</script>
</body>
</html>
