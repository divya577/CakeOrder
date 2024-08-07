<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
<style>
body {
	background-color: #fdf5e6;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
}

.form-group input {
	width: 50%;
	padding: 8px;
}

.form-group button {
	padding: 8px 16px;
}
/* Newly added for header */
.header {
	background-color: black;
	color: white;
	padding: 2px;
	text-align: center;
}
</style>
<script>
	function validateForm() {

		var newPassword = document.getElementById("newPassword").value;

		if (newPassword.length < 8) {

			alert("Password must be at least 8 characters long.");

			return false;

		}
		return true;

	}
</script>
</head>
<body>

<div class="header"> 
<h1>User Management</h1> 
</div> 

    <h2>Edit Password</h2>
    <form action="EditUserServlet" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword" required>
        </div>
        <div class="form-group">
            <button type="submit">Update Password</button>
        </div>
    </form>

    <h2>Delete Account</h2>
    <form action="DeleteUserServlet" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <button type="submit">Delete Account</button>
        </div>
    </form>
</body>
</html>
