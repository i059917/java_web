<%@ page language="java" import="java.util.*, java.io.*, user.jdbc.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
	<header>
		<script>
			let createUser = function() {
				window.location.href = "http://localhost:8088/java_web/inputuser.jsp";
			};
			
			let deleteUser = function(userId) {
				window.location.href = "http://localhost:8088/java_web/users/delete-user?id=" + userId;
			};
			let logout = function() {
				window.location.href = "http://localhost:8088/java_web/logout";
			};
			let createCourse = function() {
				window.location.href = "http://localhost:8088/java_web/course/create";
			}
		</script>
	</header>
	<body>
		<div style="background:red; width:256">
			<img src="../img/shopping_man.jpeg" height="20" width="20"></img>
			<span style="height:20; width:20"><%= session.getAttribute("login-id") %></span>
			&nbsp;&nbsp;
			<input type="button" value="Logout" onclick="logout()"></input>
		</div>
		<div style="background:green; width:256">
			User list:
			<table border="1">
			    <%
					List<User> userList = (List<User>)request.getAttribute("users");
  			    	if(userList == null) {
			    		UserDAO userDAO = new UserDAO();
			    		userList = userDAO.getAllUsers();
			    	} 
			    	out.println("<th>ID</th><th>Name</th><th>Gender</th><th>Age</th><th>Delete</th>");
			    	for(User user : userList) {
			    		String idCell = "<td>" + user.getId() + "</td>";
			    		String nameCell = "<td>" + user.getName() + "</td>";
			    		String genderCell = "<td>" + user.getGender() + "</td>";
			    		String ageCell = "<td>" + user.getAge() + "</td>";
			    		String deleteButton = "<td><input type=\"button\" value=\"Delete\" onclick=\"deleteUser(" 
			    			+ user.getId() + ")\"/></td>";
			    		String row = "<tr>" + idCell + nameCell + genderCell + ageCell + deleteButton + "</tr>";
			    		out.println(row);
			    	}
			    %>
			</table>
		</div>
		<div style="background:grey; width:256">
			<input type="button" value="Create User" onclick="createUser()"></input>
			<input type="button" value="Create Course" onclick="createCourse()"></input>
		</div>
	</body>
</html>