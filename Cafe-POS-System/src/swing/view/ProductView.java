package swing.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.hikari.HikariCP;
import jdbc.model.Product;

public class ProductView extends Product {
	
	private String sql = "SELECT * FROM product_table WHERE m_type_id = ?";
	private ArrayList<String> products = new ArrayList<>();
	
	private void getProduct(String product_name, int product_price) {
		setProduct_name(product_name);
		setProduct_price(product_price);
		getProduct_name();
		getProduct_price();
	}
	
	public ArrayList<String> getProducts(){
		return products;
	}
	
	public ProductView(int type_id) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			
			pstmt.setInt(1, type_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				getProduct(rs.getString(3), rs.getInt(4));
				String product = String.format("<HTML><body style='text-align:center;'>%s<br>%d</body></HTML>", getProduct_name(), getProduct_price());
				products.add(product);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		ArrayList<String> food = new ProductView(140).getProducts();
		System.out.println(food.toString());
	}

}
