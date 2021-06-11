package swing.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;
import jdbc.model.Product;

public class ProductView extends Product {
	
	public ProductView(String product_name, int product_price) {
		setProduct_name(product_name);
		setProduct_price(product_price);
		getProduct_name();
		getProduct_price();
	}
	
	public static void productSearchView(String group_name) {
		
		String search_name = group_name;
		String sql = "SELECT product_name, product_price FROM product_table "
				+ "INNER JOIN menu_type USING (m_type_id) WHERE m_name LIKE ?";
		
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			
			pstmt.setString(1, search_name);
			
			ResultSet rs = pstmt.executeQuery(sql);
			
			while (rs.next()) {
				System.out.println(new DrinkView(rs.getString(1), rs.getInt(2)));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public String toString() {
		return String.format("%s\n%s", super.getProduct_name(), super.getProduct_price());
	}

}
