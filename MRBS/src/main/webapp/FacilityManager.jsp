<%@page import="com.spring.comakeit.application.entity.MeetingRequest"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.spring.comakeit.application.entity.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Facility Manager</title>
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
	%>
	<div class="header">
		<h1>
			Welcome
			<%=user.getUsername()%>
		</h1>
	</div>
	<div class="menu">
	<a href="FacilityManager.jsp?operation=addMeetingRoom">Add a Meeting Room</a><br>
	<a href="FacilityManager.jsp?operation=addResource">Add a Resource</a><br>
	<a href="FacilityManager.jsp?operation=createUser">Create user</a><br>
	<a href="FacilityManager.jsp?operation=deleteUser">Delete user</a><br>
	<a href="FacilityManager?operation=viewUsers">View all Users</a><br>
	<a href="FacilityManager?operation=getAllPendingRequest">Get All Pending Request</a><br>
	<a href="FacilityManager?operation=getAllRequest">Get All Request</a><br>
	<a href="FacilityManager.jsp?operation=getAllRequestForGivenDay">Get All Request For GivenDay</a><br>
	<a href="FacilityManager?operation=getMRFrequency">Get MeetingRoom Frequency</a><br>
	<a href="FacilityManager?operation=getResourceFrequency">Get Resource Frequency</a><br>
	<a href="FacilityManager?operation=requestHR">Push HR request for next 4 mondays</a><br>
	<a href="FacilityManager?operation=mostResourceUsed">Most Resource Used</a><br><br> <br>
		<a href="index.jsp">LOGOUT</a>
	
	
	</div>
	<div class="main">
	<%String operation = request.getParameter("operation");
	if(operation!=null){
		if(operation.equals("createUser")){
			
			%>
			<form action="FacilityManager?operation=createUser"
					method="post">
					Enter Username : <input type="text" name="username" placeholder="username"><br>
					Enter Password :<input type="password" name="password" placeholder="password"><br>
					<input type="submit" name="submit" value="create">
				</form>
			<%
		}else if(operation.equals("deleteUser")){
			
			
			%>
				<form action="FacilityManager?operation=deleteUser"
					method="post">
					Enter Username : <input type="text" name="username" placeholder="username"><br>
					<input type="submit" name="submit" value="delete">
				</form>
			<%
		}else if(operation.equals("addMeetingRoom")){
			
			
			%>
			<form action="FacilityManager?operation=addMeetingRoom"
					method="post">
					Meeting Room Id : <input type="text" name="id" placeholder="id"><br>
					Meeting Room Name : <input type="text" name="meetingRoom" placeholder="MR name"><br>
					<input type="submit" name="submit" value="Add">
			</form>
			<%
		}else if(operation.equals("addResource")){
			
			
			%>
			<form action="FacilityManager?operation=addResource"
					method="post">
					Resource Id : <input type="text" name="id" placeholder="id"><br>
					Resource Name : <input type="text" name="resource" placeholder="resource name"><br>
					<input type="submit" name="submit" value="Add">
			</form>
			<%
		}else if(operation.equals("getAllRequestForGivenDay")){
			
			
			%>
			<form action="FacilityManager?operation=getAllRequestForGivenDay"
					method="post">
					Enter a  Date: <input type="date" name="date" value="<%=LocalDate.now().toString()%>"><br>
					<input type="submit" name="submit" value="Get Requests">
			</form>
			<%
		}else if(operation.equals("viewUsers")){
			ArrayList<Login> users = (ArrayList<Login>) session.getAttribute("viewUsers");
			%>
			<table>
  			  <tr>
    			<th>Username</th>
    			<th>Role</th>
			  </tr>			
			<%
			for(Login userdetail:users){
				%>
				<tr>
    			<td><%=userdetail.getUsername() %></td>
    			<td><%=userdetail.getRole()%></td>
			  	</tr>	
				<%
			}
			%>
			</table>
			<%
		}else if(operation.equals("getAllPendingRequest")){
			ArrayList<MeetingRequest> requests = (ArrayList<MeetingRequest>) session.getAttribute("getAllPendingRequest");
			String error = request.getParameter("error");
			if(error!=null){
				if(error.equals("declined")){
					%>
					<p style="color: red;">No Slots available</p>
					<%
				}
			}
			
			%>
			
			<table>
  			  <tr>
    			<th>Request ID</th>
    			<th>Date</th>
    			<th>Start Time</th>
    			<th>End Time</th>
    			<th>Booked By</th>
    			<th>Meeting Room</th>
    			<th>Resource</th>
    			<th>Accept</th>
    			<th>Reject</th>
			  </tr>			
			<%
			for(MeetingRequest req:requests){
				%>
				<tr>
    			<td><%= req.getRequestId() %></td>
    			<td><%=req.getDate() %></td>
    			<td><%=req.getStartTime() %></td>
    			<td><%=req.getEndTime() %></td>
    			<td><%=req.getUser().getUsername() %></td>
    			<td><%=req.getMeetingRoom().getMeetingRoomName() %></td>
    			<td><%=req.getResource().getResourceName() %></td>
    			<td><a href="FacilityManager?operation=acceptRequest&id=<%=req.getRequestId()%>">accept</a></td>
    			<td><a href="FacilityManager?operation=rejectRequest&id=<%=req.getRequestId()%>">reject</a></td>
			  </tr>		
				<%
			}
			%>
			</table>
			<%
		}else if(operation.equals("getAllRequest")){
			ArrayList<MeetingRequest> requests = (ArrayList<MeetingRequest>) session.getAttribute("getAllRequest");
			%>
			<table>
  			  <tr>
    			<th>Request ID</th>
    			<th>Date</th>
    			<th>Start Time</th>
    			<th>End Time</th>
    			<th>Booked By</th>
    			<th>Meeting Room</th>
    			<th>Resource</th>
    			<th>Status</th>
			  </tr>			
			<%
			for(MeetingRequest req:requests){
				%>
				<tr>
    			<td><%= req.getRequestId() %></td>
    			<td><%=req.getDate() %></td>
    			<td><%=req.getStartTime() %></td>
    			<td><%=req.getEndTime() %></td>
    			<td><%=req.getUser().getUsername() %></td>
    			<td><%=req.getMeetingRoom().getMeetingRoomName() %></td>
    			<td><%=req.getResource().getResourceName() %></td>
    			<td><%=req.getStatus()%></td>
			  </tr>		
				<%
			}
			%>
			</table>
			<%
		}
		else if(operation.equals("allRequestForGivenDay")){
			ArrayList<MeetingRequest> requests = (ArrayList<MeetingRequest>) session.getAttribute("getAllRequestForGivenDay");
			%>
			<table>
  			  <tr>
    			<th>Request ID</th>
    			<th>Date</th>
    			<th>Start Time</th>
    			<th>End Time</th>
    			<th>Booked By</th>
    			<th>Meeting Room</th>
    			<th>Resource</th>
    			<th>Status</th>
			  </tr>			
			<%
			for(MeetingRequest req:requests){
				%>
				<tr>
    			<td><%= req.getRequestId() %></td>
    			<td><%=req.getDate() %></td>
    			<td><%=req.getStartTime() %></td>
    			<td><%=req.getEndTime() %></td>
    			<td><%=req.getUser().getUsername() %></td>
    			<td><%=req.getMeetingRoom().getMeetingRoomName() %></td>
    			<td><%=req.getResource().getResourceName() %></td>
    			<td><%=req.getStatus()%></td>
			  </tr>		
				<%
			}
			%>
			</table>
			<a href="FacilityManager.jsp?operation=getAllRequestForGivenDay"> Go Back </a>
			<%
		}else if(operation.equals("getMRFrequency")){
			ArrayList<String> frequencyList = (ArrayList<String>) session.getAttribute("getMRFrequency");
			%>
			<table>
  			  <tr>
    			<th>Meeting Room</th>
    			<th>Booking Frequency</th>
			  </tr>	
			  <% 
			  	for(String pairs:frequencyList){
			  		String 	pair[] = pairs.split(",");
			  %><tr>
	    		<th><%= pair[0]%></th>
	    		<th><%=pair[1] %></th>
			  </tr>
			  <%	
			  	}
			  %></table>
			<%
		}else if(operation.equals("getResourceFrequency")){
			ArrayList<String> frequencyList = (ArrayList<String>) session.getAttribute("getResourceFrequency");
			%>
			<table>
  			  <tr>
    			<th>Resource</th>
    			<th>Booking Frequency</th>
			  </tr>	
			  <% 
			  	for(String pairs:frequencyList){
			  		String 	pair[] = pairs.split(",");
			  %><tr>
	    		<th><%= pair[0]%></th>
	    		<th><%=pair[1] %></th>
			  </tr>
			  <%	
			  	}
			  %></table>
			<%
		}else if(operation.equals("mostResourceUsed")){
				String mostResourceUsed = (String) session.getAttribute("mostResourceUsed");
			%>
			<p>The most resource used is : <%=mostResourceUsed %></p>
			<%
		}
		
		
	%><%} %>
	</div>
</body>
</html>