<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact Us</title>
<style>
    body {
        background-color: #fdf5e6;
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }
    .header {
        background-color: black;
        padding: 10px;
        text-align: center;
    }
    .header a {
        color: white;
        text-decoration: none;
        margin: 0 20px;
        font-weight: bold;
    }
    .contact-details {
        border: 1px solid black;
        padding: 10px;
        margin: 20px auto;
        width: 80%;
        box-sizing: border-box;
        background-color: #fff;
        border-radius: 5px;
    }
    h1, h2 {
        text-align: center;
        color: #333;
    }
    p {
        text-align: center;
    }
</style>
</head>
<body>
    <div class="header">
        <a href="/CakeOrderSys/HomeServlet/home">Home</a>
        <a href="/CakeOrderSys/shop.jsp">Shop</a>
        <a href="/CakeOrderSys/contactUs.jsp">Contact Us</a>
        <a href="/CakeOrderSys/login.jsp">Login</a>
    </div>
    <h1>Contact Us</h1>
    <div class="contact-details">
        <h2>Contact Information</h2>
        <p>Phone: <a href="tel:98344762">+65 98344762</a></p>
        <p>Email: <a href="mailto:info@cakestore.com">info@cakestore.com</a></p>
        <p>Address for in-store collection: 123 Cake Street, Sweet City, Candyland 45678 </p>
    </div>
</body>
</html>
