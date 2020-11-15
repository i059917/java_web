<html>
	<header>
		<style type="text/css" src="css/java_web.css"></style>
	</header>
	<body onload="">
		<form class="form-style-9" action="users/create-user">
			<div style="background:red; width:300">
				<img src="./img/shopping_man.jpeg" height="20" width="20"></img>
				<%= session.getAttribute("login-id") %>
				&nbsp;&nbsp;
				<input type="button" value="Logout" onclick="logout()"></input>
			</div>	
			<div style="background:green; width:300">
				Create User Form:
				<br>
				<input type="text" name="id" class="field-style field-split align-left" placeholder="ID" />
				<br>
				<input type="text" name="name" class="field-style field-split align-right" placeholder="Name" />
				<br>
				<input type="text" name="gender" class="field-style field-split align-left" placeholder="Gender" />
				<br>
				<input type="text" name="age" class="field-style field-split align-right" placeholder="Age" />
				<br>
				<input type="text" name="password" class="field-style field-split align-right" placeholder="Password" />
			</div>
			<div style="background:grey; width:300">
				<input type="submit" value="Create" />
			</div>
		</form>
	</body>
</html>