<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<title>
			HighchartsJS Example GAE
		</title>
		<link type="text/css" rel="stylesheet" href="/stylesheets/bootstrap/bootstrap.css">
		<link type="text/css" rel="stylesheet" href="/stylesheets/bootstrap/bootstrap-responsive.css">
		<script type="text/javascript" language="javascript" src="/javascripts/bootstrap/bootstrap.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
		<script src="//code.highcharts.com/3.0.0/highcharts.js" type="text/javascript"></script>
		<script src="/javascripts/highcharts/dynamic-charts.js" type="text/javascript" language="javascript"></script>
		<script src="/javascripts/highcharts/gray.js" type="text/javascript" language="javascript"></script>
		<link rel="icon" type="image/png" href="/moat_icon.png">
	</head>
	<body>
		<%@ page import="com.google.appengine.api.users.UserService" %>
		<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
	<%
		UserService userService = UserServiceFactory.getUserService();
		if (!userService.isUserLoggedIn()) {
	%>
		<h4>Please <a href="<%=userService.createLoginURL("/") %>">log in</a></h4>
	<%
		} else {
	%>
		<h4>Want to <a href="<%=userService.createLogoutURL("/") %>">log out</a>?</h4>
	<%
		}
	%>
   </body>
</html>
