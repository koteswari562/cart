
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.cart.Product;

public class ProductDAO {
	static String url="jdbc:postgresql://localhost:5432/postgres";
	static String username="postgres";
	static String password="koti@21";

	public static List<Product> getProductsByCategory(int categoryId) {
		List<Product> products = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM products WHERE catid=?")) {
			stmt.setInt(1, categoryId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int productId = rs.getInt("proid");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				Product product = new Product(productId, name, price);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public static Product getProductById(int productId) {
		Product product = null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM products WHERE proid=?")) {
			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int price = rs.getInt("price");
				product = new Product(productId, name, price);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
}
