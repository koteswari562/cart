<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Display</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
    /* Style for cart icon */
    #cartIcon {
        position: fixed;
        top: 20px;
        right: 20px;
        font-size: 24px;
        color: #333;
        cursor: pointer;
    }
</style>
<script>
$(document).ready(function(){
    $("#category").change(function(){
        var categoryId = $(this).val();
        $.ajax({
            url: "ProductsServlet",
            method: "POST",
            data: {category: categoryId},
            success: function(data){
                // Clear existing products
                $("#products").empty();
                
                // Loop through each product and create a card
                $.each(data, function(index, product){
                    var card = $("<div>").addClass("card");
                    var cardBody = $("<div>").addClass("card-body");
                    var productImage = $("<img>").addClass("card-img-top").attr("src", product.image); 
                    var productName = $("<h5>").addClass("card-title").text(product.name);
                    var productPrice = $("<p>").addClass("card-text").text("Price: $" + product.price);
                    var addToCartButton = $("<button>").addClass("btn btn-primary").text("Add to Cart");
                    
                    // Add click event to the "Add to Cart" button
                    addToCartButton.click(function(){
                        // Send AJAX request to add product to cart
                        $.ajax({
                            url: "CartServlet",
                            method: "POST",
                            data: {productId: product.id},
                            dataType: "text",
                            success: function(response){
                                console.log("Product added to cart:", product);
                            },
                            error: function(xhr, status, error){
                                console.error("Error adding product to cart:", error);
                            }
                        });
                    });
                    
                    cardBody.append(productImage,productName, productPrice, addToCartButton);
                    card.append(cardBody);
                    $("#products").append(card);
                });
            }
        });
    });
    
    // Redirect to cartdisplay.jsp when cart icon is clicked
    $("#cartIcon").click(function() {
        window.location.href = "cartdisplay.jsp";
    });
});
</script>
</head>
<body>
    <div class="container">
        <h2>Products</h2>
        <div class="row">
            <div class="col-md-6">
                <select id="category" class="form-control">
                	<option>Category</option>
                    <option value="1">Mobiles</option>
                    <option value="2">Clothes</option>
                </select>
            </div>
        </div>
        <div class="row mt-3" id="products">
            <!-- Product cards will be dynamically added here -->
        </div>
    </div>
    <!-- Cart icon at the top right corner -->
    <i id="cartIcon" class="fas fa-shopping-cart"></i>
</body>
</html>
    