//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * Servlet implementation class CartServlet
// */
//@WebServlet("/CartServlet")
//public class CartServlet extends HttpServlet {
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		int productId = Integer.parseInt(request.getParameter("prouctId"));
//		System.out.println(productId);
//		int quantity = Integer.parseInt(request.getParameter("quantity"));
//		Product product = ProductDAO.getProductById(productId);
//		HttpSession session = request.getSession();
//		Cart cart = (Cart) session.getAttribute("cart");
//		if (cart == null) {
//			cart = new Cart();
//			session.setAttribute("cart", cart);
//			cart.addProduct(product, quantity);
//			response.sendRedirect("products.jsp");
//		}
//	}
//
//}





import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.cart.Cart;
import shop.cart.Product;
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the product ID from the request parameter
        int productId = Integer.parseInt(request.getParameter("productId"));
        System.out.println(productId);

        // Assuming you have a Cart class that manages the cart items
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            HttpSession session = request.getSession();
            session.setAttribute("cart", cart);

            //request.getSession().setAttribute("cart", cart);
        }

        // Add the product to the cart
        Product product = ProductDAO.getProductById(productId);
        cart.addProduct(product,1);

        // Send a response indicating success
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print("Product added to cart successfully");
        out.flush();
    }
}
