import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import shop.cart.Order;
import shop.cart.Product;

public class OrderDAO implements AddOrders{

	public void insertOrderItems(double orderId, Map<Product, Integer> items) {
        DBConn dbobj = new DBConn();
        Connection con = dbobj.getDBConnection();
        try (CallableStatement stmt = con.prepareCall("{call insertOrderProducts(?, ?)}")) {
            stmt.setDouble(1, orderId);
            stmt.setString(2, convertItemsToJson(items));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	public double insertOrder(Order order) {
	    double orderId = -1;
	    DBConn dbobj = new DBConn();
	    Connection con = dbobj.getDBConnection();

	    try (
	        PreparedStatement stmt = con.prepareStatement("INSERT INTO orders(orddate, ordtotal) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)
	    ) {
	        stmt.setDate(1, new java.sql.Date(order.getDate().getTime()));
	        stmt.setInt(2, order.getTotal());
	        stmt.executeUpdate();
	        ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            orderId = rs.getDouble(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return orderId;
	}
	
	
	
	public String convertItemsToJson(Map<Product, Integer> items) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(items);
        
        return json;
    }


}
