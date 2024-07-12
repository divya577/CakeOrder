<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shop</title>
    <style>
        body { background-color: #fdf5e6; }
        .filter-box { border: 1px solid black; padding: 10px; margin-bottom: 20px; }
        .cake-item { margin-bottom: 20px; }
        
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
        .filter-container {
            margin: 20px 0;
        }
        .shop-item {
            border: 1px solid black;
            padding: 10px;
            margin-top: 20px;
        }
        .add-to-cart {
            background-color: #333;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border: none;
            cursor: pointer;
        }
        .hidden {
            display: none;
        }
    </style>
    <script>
        function filterCakes() {
            var occasion = document.getElementById('occasionFilter').value;
            var price = document.getElementById('priceFilter').value;
            var flavour = document.getElementById('flavourFilter').value;
            var items = document.getElementsByClassName('cake-item');
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                var itemOccasion = item.getAttribute('data-occasion');
                var itemPrice = parseFloat(item.getAttribute('data-price'));
                var itemFlavour = item.getAttribute('data-flavour');

                var occasionMatch = (occasion === 'all' || itemOccasion === occasion);
                var priceMatch = (price === 'all' || (price === '20-50' && itemPrice >= 20 && itemPrice <= 50) ||
                                  (price === '51-80' && itemPrice >= 51 && itemPrice <= 80) ||
                                  (price === '81-100' && itemPrice >= 81 && itemPrice <= 100));
                var flavourMatch = (flavour === 'all' || itemFlavour === flavour);

                if (occasionMatch && priceMatch && flavourMatch) {
                    item.classList.remove('hidden');
                } else {
                    item.classList.add('hidden');
                }
            }
        }
    </script>
</head>
<body>
    <div class="header">
        <a href="/CakeOrderSys/HomeServlet/home">Home</a>
        <a href="/CakeOrderSys/shop.jsp">Shop</a>
        <a href="/CakeOrderSys/contactUs.jsp">Contact Us</a>
        <a href="/CakeOrderSys/login.jsp">Login</a>
    </div>
    <h1>Shop</h1>
    <div class="filter-container">
        <label for="occasionFilter">Filter by Occasion:</label>
        <select id="occasionFilter" onchange="filterCakes()">
            <option value="all">All</option>
            <option value="birthday">Birthday</option>
            <option value="wedding">Wedding</option>
            <option value="christmas">Christmas</option>
            <option value="chinese-new-year">Chinese New Year</option>
        </select>

        <label for="priceFilter">Filter by Price:</label>
        <select id="priceFilter" onchange="filterCakes()">
            <option value="all">All</option>
            <option value="20-50">20-50</option>
            <option value="51-80">51-80</option>
            <option value="81-100">81-100</option>
        </select>

        <label for="flavourFilter">Filter by Flavour:</label>
        <select id="flavourFilter" onchange="filterCakes()">
            <option value="all">All</option>
            <option value="vanilla">Vanilla</option>
            <option value="chocolate">Chocolate</option>
            <option value="red-velvet">Red Velvet</option>
            <option value="durian">Durian</option>
            <option value="strawberry">Strawberry</option>
        </select>
    </div>

    <div class="cake-item" data-occasion="birthday" data-price="25" data-flavour="vanilla">
        <h3>Vanilla Cake - $25</h3>
        <img src="/CakeOrderSys/image/vanilla_sponge_cake.png" alt="Vanilla Cake" width="200" height="150">
        <p>Delicious vanilla cake.</p>
        <form action="CartServlet" method="POST">
            <input type="hidden" name="item" value="Vanilla Cake">
            <input type="submit" value="Add to Cart">
        </form>
    </div>

    <div class="cake-item" data-occasion="birthday" data-price="30" data-flavour="chocolate">
        <h3>Chocolate Cake - $30</h3>
        <img src="/CakeOrderSys/image/truffle_chocolate_cake.png" alt="Chocolate Cake" width="200" height="150">
        <p>Rich chocolate cake.</p>
        <form action="CartServlet" method="POST">
            <input type="hidden" name="item" value="Chocolate Cake">
            <input type="submit" value="Add to Cart">
        </form>
    </div>

    <div class="cake-item" data-occasion="wedding" data-price="50" data-flavour="red-velvet">
        <h3>Red Velvet Cake - $50</h3>
        <img src="/CakeOrderSys/image/red_velvet_cake.png" alt="Red Velvet Cake" width="200" height="150">
        <p>Decadent red velvet cake.</p>
        <form action="CartServlet" method="POST">
            <input type="hidden" name="item" value="Red Velvet Cake">
            <input type="submit" value="Add to Cart">
        </form>
    </div>

    <div class="cake-item" data-occasion="chinese-new-year" data-price="55" data-flavour="durian">
        <h3>Durian Cake - $55</h3>
        <img src="/CakeOrderSys/image/Durian.png" alt="Durian Cake" width="200" height="150">
        <p>Original Durian cake.</p>
        <form action="CartServlet" method="POST">
            <input type="hidden" name="item" value="Durian Cake">
            <input type="submit" value="Add to Cart">
        </form>
    </div>

    <div class="cake-item" data-occasion="birthday" data-price="35" data-flavour="strawberry">
        <h3>Strawberry Cake - $35</h3>
        <img src="/CakeOrderSys/image/Strawberry.png" alt="Strawberry Cake" width="200" height="150">
        <p>Contains extra strawberries. Made from real strawberries</p>
        <form action="CartServlet" method="POST">
            <input type="hidden" name="item" value="Strawberry Cake">
            <input type="submit" value="Add to Cart">
        </form>
    </div>

    <div class="cake-item" data-occasion="christmas" data-price="82" data-flavour="chocolate">
        <h3>Log Cake - $82</h3>
        <img src="/CakeOrderSys/image/Log Cake.png" alt="Chocolate Cake" width="200" height="150">
        <p>Delicious Chocolate Log Cake</p>
        <form action="CartServlet" method="POST">
            <input type="hidden" name="item" value="Log Chocolate Cake">
            <input type="submit" value="Add to Cart">
        </form>
    </div>
    <div class="cake-item" data-occasion="christmas" data-price="75" data-flavour="red-velvet">
        <h3>Red-velvet Log Cake - $75</h3>
        <img src="/CakeOrderSys/image/cake.png" alt="red-velvet Cake" width="200" height="150">
        <p>Delicious red-velvet Log Cake</p>
        <form action="CartServlet" method="POST">
            <input type="hidden" name="item" value="Log Red-Velvet Cake">
            <input type="submit" value="Add to Cart">
        </form>
    </div>
    
</body>
</html>
