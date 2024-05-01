import java.util.Map;

import shop.cart.Order;
import shop.cart.Product;

public interface AddOrders {
	public void insertOrderItems(double orderId, Map<Product, Integer> items);
	public double insertOrder(Order order);
}
