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
		<%@ page import="java.util.List" %>
		<%@ page import="java.util.ArrayList" %>
		<%@ page import="java.util.Map" %>
		<%@ page import="com.yourinventit.moat.gae.hcjsexample.models.SensingData" %>
		<%@ page import="com.yourinventit.moat.gae.hcjsexample.models.SysDevice" %>
		<%@ page import="com.yourinventit.moat.gae.hcjsexample.models.SysDmjob" %>
		<%
		final List<SysDevice> devices = (List<SysDevice>) request.getAttribute("devices");
		final Map<String, List<SensingData>> sensingDataListMap = (Map<String, List<SensingData>>) request.getAttribute("sensing_data_list");
		final List<SysDmjob> jobList = (List<SysDmjob>) request.getAttribute("job_list");
		%>
		
		<div id="wrap">
			<div class="container">
				<h1>
					<img src="/moat_icon.png" alt="MOAT IoT icon" height="36" width="36" style="vertical-align: -12%;"> MOAT IoT HighchartsJS example
				</h1>

				<%-- HighchartsJS --%>
				<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>

				<h2>
					Devices
				</h2><% if (devices == null || devices.isEmpty()) {%>
				<p>
					Install the gateway app and the example Android app into your Android phone. Then tap the <img src="/moat_icon.png" alt="MOAT IoT icon" height="20" width="20"> icon.
				</p><% } %>
				<% for (SysDevice d : devices) { %>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<th>
							Name
						</th>
						<th>
							Device ID
						</th>
						<th>
							Commands
						</th>
					</tr>
					<tr>
						<td>
							<%= d.getName() %>
						</td>
						<td>
							<%= d.getDeviceId() %>
						</td>
						<td>
							<a href="/dashboard/delete_device?device_uid=<%= d.getUid() %>" class="btn">Delete <i class='icon-off'></i></a>
						</td>
					</tr>
				</table>
				<h3>
					Sensing Data for <%= d.getName() %>
				</h3><%
				// Building a comma separated string
				final List<SensingData> list = sensingDataListMap.get(d.getName());
				%>
				<table class="table">
					<tr>
						<td>
							<a href="/dashboard/delete_all_sensing_data" class="btn btn-primary">Clear <i class='icon-fire icon-white'></i></a>
						</td>
					</tr>
				</table>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<th>
							Time
						</th>
						<th>
							DC/AC
						</th>
						<th>
							Value
						</th>
						<th>
							Unit
						</th>
					</tr>
					<% for (SensingData sd : list) { %>
					<tr>
						<td>
							<%= sd.getTimestamp() < 1 ? "" : new java.util.Date(sd.getTimestamp()) %>
						</td>
						<td>
							<%= sd.getDa() %>
						</td>
						<td>
							<%= sd.getValue() %>
						</td>
						<td>
							<%= sd.getUnit() %>
						</td>
					</tr>
					<% } %>
				</table>
				<hr>
				<% } // for (SysDevice d : devices) {} %>

				<h2>
					Active Requests
				</h2>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<th>
							Device Name
						</th>
						<th>
							Status
						</th>
						<th>
							Type
						</th>
						<th>
							Started At
						</th>
						<th>
							Expired At
						</th>
						<th>
							Action
						</th>
					</tr>
					<% for (SysDmjob j : jobList) { %>
					<tr>
						<td>
							<%= j.getName() %>
						</td>
						<td>
							<%= j.getStatus() %>
						</td>
						<td>
							<%= j.getJobServiceId() %>
						</td>
						<td>
							<%= j.getStartedAt() == null ? "" : j.getStartedAt() %>
						</td>
						<td>
							<%= j.getExpiredAt() == null ? "" : j.getExpiredAt() %>
						</td>
						<td>
							<a href="/dashboard/cancel?uid=<%= j.getUid() %>" class="btn btn-primary">Cancel <i class='icon-remove-sign icon-white'></i></a>
						</td>
					</tr>
					<% } %>
				</table>
			<div id="footer">
				<div class="container">
					<p class="muted credit">
						Copyright © 2013 <a href="http://www.yourinventit.com">Inventit Inc.</a>
					</p>
					<p class="muted credit">
						Built with <a href="http://dev.yourinventit.com/references/moat-rest-api-document">MOAT REST 1.0.1</a>, <a href="http://twitter.github.com/bootstrap/index.html">Bootstrap 2.2.2</a> and <a href="https://developers.google.com/appengine/">Google App Engine 1.7.3</a>.
					</p>
					<p class="muted credit">
						Running with <a href="http://dev.yourinventit.com">Inventit IoT Developer Network Development Sandbox Server</a>.
					</p>
				</div>
			</div>
		</div>
	</body>
</html>
