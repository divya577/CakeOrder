
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>
</head>
<body>
    <h1>Your Cart</h1>
    <div id="cartItems">
        <% 
            // Retrieve cart items from session
            List<String> cart = (List<String>) session.getAttribute("cart");
        
            if (cart == null || cart.isEmpty()) {
                // Cart is empty
                out.println("<p>Your cart is empty.</p>");
            } else {
                // Display cart items
                out.println("<ul>");
                for (String item : cart) {
                    out.println("<li>" + item + " <button class='delete-btn'>Delete</button></li>");
                }
                out.println("</ul>");
            }
        %>
    </div>

    <script>
        // Function to handle deletion of cart items
        function deleteCartItem() {
            var itemText = this.parentNode.firstChild.nodeValue.trim();
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "RemoveCartItemServlet", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        var cartItemsDiv = document.getElementById('cartItems');
                        var itemToRemove = null;
                        // Find and remove the deleted item from the cart display
                        cartItemsDiv.childNodes.forEach(function (node) {
                            if (node.firstChild.nodeValue.trim() === itemText) {
                                itemToRemove = node;
                            }
                        });
                        if (itemToRemove) {
                            cartItemsDiv.removeChild(itemToRemove);
                        }
                    } else {
                        console.error('Error deleting item from cart.');
                    }
                }
            };
            xhr.send("item=" + encodeURIComponent(itemText));
        }

        var deleteButtons = document.getElementsByClassName('delete-btn');
        for (var i = 0; i < deleteButtons.length; i++) {
            deleteButtons[i].addEventListener('click', deleteCartItem);
        }
    </script>
</body>
</html>
