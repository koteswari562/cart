<%@ page import="java.util.Map" %>
<%@ page import="shop.cart.Cart" %>
<%@ page import="shop.cart.Product" %>

<%
    // Retrieve Cart from session
    Cart cart = (Cart) session.getAttribute("cart");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart Items</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <h1>Cart Items</h1>
    <div class="container">
        <div class="row">
            <% 
                if (cart != null) {
                    // Display cart items
                    Map<Product, Integer> items = cart.getItems();
                    if (items != null && !items.isEmpty()) {
                        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                            Product product = entry.getKey();
                            int quantity = entry.getValue();
            %>
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Product: <%= product.getName() %></h5>
                        <p class="card-text">Price: <%= product.getPrice() %></p>
                        <p class="card-text">Quantity: <%= quantity %></p>
                    </div>
                </div>
            </div>
            <%          
                        }
                    } else {
            %>
            <div class="col">
                <p>No items in the cart.</p>
            </div>
            <%      
                    }
                } else {
            %>
            <div class="col">
                <p>Cart is empty.</p>
            </div>
            <%      
                }
            %>
        </div>
    </div>
    <div class="container">
        <div class="row mt-3">
            <div class="col text-center">
                <button class="btn btn-primary" onclick="checkout()">Checkout</button>
            </div>
        </div>
    </div>

    <script>
    function checkout() {
        // Create a form element
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "CheckoutServlet"); // Set the action to your servlet URL
        
        // Submit the form
        document.body.appendChild(form);
        form.submit();
    }
    </script>
</body>
</html>