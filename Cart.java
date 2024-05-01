package shop.cart;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
    	int flag=0;
    	 for (Map.Entry<Product,Integer> hm : items.entrySet()) {
             Product key = hm.getKey();
             if(key.getId()==product.getId()) {
            	 items.put(key,hm.getValue()+1);
            	 flag=1;
            	 break;
             }
        }if(flag==0) {
        	items.put(product, quantity);
        }
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public void updateQuantity(Product product, int quantity) {
        if (items.containsKey(product)) {
            items.put(product, quantity);
        }
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public int getTotal() {
        int totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }

    public void clearCart() {
        items.clear();
    }
}