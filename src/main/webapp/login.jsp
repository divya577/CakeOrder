<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            background-color: #fdf5e6;
            font-family: Arial, sans-serif;
        }
        .form-group {
            margin-bottom: 15px;
            max-width: 300px; 
            margin: 0 auto; 
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box; 
            margin-bottom: 10px; 
        }
        .form-group button {
            padding: 8px 16px;
            background-color: #333;
            color: white;
            border: none;
            cursor: pointer;
            width: 100%; /* Make the button full width */
            box-sizing: border-box; /* Include padding and border in the element's total width and height */
            margin-top: 10px; /* Add spacing above the button */
        }
        h1, p {
            text-align: center;
        }
        a {
            color: #333;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Login</h1>
    <form action="LoginServlet" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <button type="submit">Login</button>
        </div>
    </form>
    <p>Don't have an account? <a href="register.jsp">Create an account</a></p>
    <p>Manage your account? <a href="userManagement.jsp">Edit/Delete Account</a></p>
</body>
</html>
