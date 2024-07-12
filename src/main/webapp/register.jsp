<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <style>
        body {
            background-color: #fdf5e6;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            background-color: white;
            border: 1px solid #ddd;
            box-shadow: 2px 2px 12px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="password"], input[type="email"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .form-group button {
            padding: 10px 15px;
            background-color: #333;
            color: white;
            border: none;
            cursor: pointer;
        }
        .header a {
            color: black;
            text-decoration: none;
            margin-right: 20px;
        }
    </style>
</head>
<body>
    <div class="header">
        <a href="/CakeOrderSys/HomeServlet/home">Home</a>
        <a href="/CakeOrderSys/shop.jsp">Shop</a>
        <a href="/CakeOrderSys/contactUs.jsp">Contact Us</a>
    </div>
    <div class="container">
        <h2>Create an Account</h2>
        <form action="RegisterServlet" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="text" id="phone" name="phoneNum" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <button type="submit">Register</button>
            </div>
        </form>
    </div>
</body>
</html>
