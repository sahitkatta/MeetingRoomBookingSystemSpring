<%@page import="com.spring.comakeit.application.entity.MeetingRequest"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.spring.comakeit.application.entity.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<c:set var="path" value ="${pageContext.request.contextPath} "></c:set>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User</title>
<style>
* {
	box-sizing: border-box;
}

.header {
	padding: 5px;
}

.menu {
	width: 25%;
	float: left;
	padding: 10px;
}

.main {
	/*border-left: 2px solid grey;*/
	width: 75%;
	float: left;
	padding: 15px;
}

a:link {
	color: blue;
	text-decoration: none;
}

a:visited {
	color: blue;
	text-decoration: none;
}

/* mouse over link */
a:hover {
	color: green;
	text-decoration: none;
}

/* selected link */
a:active {
	color: blue;
	text-decoration: none;
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<%
		Login user = (Login) session.getAttribute("user");
		String username = user.getUsername();
		String password = user.getPassword();
		
		ArrayList<MeetingRoom> meetingRoomList = (ArrayList<MeetingRoom>) session.getAttribute("meetingRoomList");
		ArrayList<Resource> resourceList = (ArrayList<Resource> )session.getAttribute("resourceList");
		
	%>
	<div class="header">
		<h1>
			Welcome
			<%=username%>
		</h1>
	</div>
	<div class="menu">
		<!-- <a href="?operation=request">Request a Slot</a><br> -->
		<a href="User.jsp?operation=requests">Request Slot(s)</a><br>
		<a href="User.jsp?operation=cancelBulkRequests">Cancel Slot(s)</a><br>
		<a href="User?operation=view">View Requested Slot</a><br> <a
			href="User?operation=history">Request History</a><br> <br>
		<a href="/">LOGOUT</a>
	</div>
	<div class="main">
		<%
			String operation = request.getParameter("operation");
			if (operation != null) {
				if (operation.equals("request")) {

		%>
		<form action="User?operation=request" method="post">
			Date: <input type="date" name="date" id="date"
				value="<%=LocalDate.now().toString()%>"><br> Start
			Time: <input type="time" name="startTime"><br> End Time:<input
				type="time" name="endTime"><br> <select
				name="meetingRoomNumber">
				<%
				for(MeetingRoom room: meetingRoomList){
					%>
				<option value="<%=room.getId() %>"><%= room.getMeetingRoomName() %></option>
				<%
				}
				%>

			</select> <br> <select name="resource">
				<%
				for(Resource resource: resourceList){
				%>
				<option value="<%=resource.getId() %>"><%= resource.getResourceName() %></option>
				<%
				}
				%>
			</select><br> <br> <input type="submit">
		</form>


		<%
			}else if(operation.equals("requests")){
				%>
		<form action="User?operation=requests" method="post">
			Start Date: <input type="date" name="startDate"
				value="<%=LocalDate.now().toString()%>"><br> End Date:
			<input type="date" name="endDate"
				value="<%=LocalDate.now().toString()%>"> <br> Start
			Time: <input type="time" name="startTime"><br> End Time:<input
				type="time" name="endTime"><br> <select
				name="meetingRoomNumber">
				<%
				for(MeetingRoom room: meetingRoomList){
					%>
				<option value="<%=room.getId() %>"><%= room.getMeetingRoomName() %></option>
				<%
				}
				%>

			</select> <br> <select name="resource">
				<%
				for(Resource resource: resourceList){
					%>
				<option value="<%=resource.getId() %>"><%= resource.getResourceName() %></option>
				<%
				}
				%>
			</select><br> <br> <input type="submit">
		</form>

		<%
			} else if (operation.equals("cancel")) {
					
					ArrayList<MeetingRequest> requestList = (ArrayList<MeetingRequest>) session.getAttribute("requestList");
		%>
		<table>
			<tr>
				<th>RequestID</th>
				<th>Date</th>
				<th>Start Time</th>
				<th>End time</th>
				<th>user</th>
				<th>Meeting no</th>
				<th>resource</th>
				<th>Status</th>
				<th>Cancel Request</th>
			</tr>
			<%
				for (MeetingRequest req : requestList) {
							
			%>
			<tr>
				<td><%=req.getRequestId()%></td>
				<td><%=req.getDate()%></td>
				<td><%=req.getStartTime()%></td>
				<td><%=req.getEndTime()%></td>
				<td><%=req.getUser().getUsername()%></td>
				<td><%=req.getMeetingRoom().getMeetingRoomName()%></td>
				<td><%=req.getResource().getResourceName() %></td>
				<td><%=req.getStatus()%></td>
				<td><a href="User?operation=cancel&id=<%=req.getRequestId()%>"
					id="text<%=req.getRequestId()%>">cancel</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			} else if (operation.equals("history")) {
					
					ArrayList<MeetingRequest> historyList = (ArrayList <MeetingRequest>) session.getAttribute("historyList");
		%>
		<table>
			<tr>
				<th>RequestID</th>
				<th>Date</th>
				<th>Start Time</th>
				<th>End time</th>
				<th>user</th>
				<th>Meeting no</th>
				<th>resource</th>
				<th>Status</th>
			</tr>
			<%
				for (MeetingRequest req : historyList) {
							
			%>
			<tr>
				<td><%=req.getRequestId()%></td>
				<td><%=req.getDate()%></td>
				<td><%=req.getStartTime()%></td>
				<td><%=req.getEndTime()%></td>
				<td><%=req.getUser().getUsername()%></td>
				<td><%=req.getMeetingRoom().getMeetingRoomName()%></td>
				<td><%=req.getResource().getResourceName()%></td>
				<td><%=req.getStatus()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			}else if(operation.equals("cancelBulkRequests")){
				%>
		<form action="User?operation=cancelBulkRequests" method="post">
			Start Date: <input type="date" name="startDate" id="date"
				value="<%=LocalDate.now().toString()%>"><br>End Date: <input
				type="date" name="endDate" id="date"
				value="<%=LocalDate.now().toString()%>"><br> Start
			Time: <input type="time" name="startTime"><br> End Time:<input
				type="time" name="endTime"><br> <select
				name="meetingRoomNumber">
				<%
				for(MeetingRoom room: meetingRoomList){
					%>
				<option value="<%=room.getId() %>"><%= room.getMeetingRoomName() %></option>
				<%
				}
				%>

			</select> <br> <select name="resource">
				<%
				for(Resource resource: resourceList){
				%>
				<option value="<%=resource.getId() %>"><%= resource.getResourceName() %></option>
				<%
				}
				%>
			</select><br> <br> <input type="submit">
		</form>

		<%
			}

			}
		%>


	</div>
</body>
</html>