<html>
<style>
input[type=text], select {
  width: 100%;
  padding: 5px 5px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
input[type=password], select {
  width: 100%;
  padding: 5px 5px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

div {
  width:250px;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 10px;
}
</style>
<body>

<h3>Welcome to Meeting Room Booking System</h3>

<div>
  <form action="Login" method="POST">
    <label for="username">Enter Username</label>
    <input type="text" id="username" name="username" placeholder="username">
    <label for="password">Enter Password</label>
    <input type="password" id="password" name="password" placeholder="password">
    <input type="submit" value="Submit">
    <%
    String operation = request.getParameter("operation");
    		if(operation!=null){
    			if(operation.equals("error")){
    				%>
    				<label for="error" style="color: red;">Enter valid Credentials</label>
    				<%
    			}
    		}
    %>
  </form>
</div>
</html>
