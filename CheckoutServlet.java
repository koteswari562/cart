
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.cart.Cart;
import shop.cart.Order;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			int totalPrice = cart.getTotal();
			Order order = new Order();
			order.setDate(new Date(0));
			order.setTotal(totalPrice);
			order.setItems(cart.getItems());
			
			AddOrders obj=new OrderDAO();
			double orderId = obj.insertOrder(order);
			obj.insertOrderItems(orderId, cart.getItems());
			
			cart.clearCart();
			request.setAttribute("totalPrice", totalPrice);
			response.sendRedirect("checkout.jsp");

		} else {
			response.sendRedirect("");
		}
	}

}
