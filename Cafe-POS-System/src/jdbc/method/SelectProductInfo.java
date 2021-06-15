package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

public class SelectProductInfo {
	private String sql = "SELECT p.product_name, r.menu_count, p.product_price FROM product_table p INNER JOIN receipt_table r USING(product_id)";
	private int sum;
	private int productIndex;
	public int getSum() {
		return sum;
	}
	
	public SelectProductInfo(int productIndex) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, 
			             ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = pstmt.executeQuery();
		) {
			rs.relative(productIndex);
			if (rs.next()) {				
				System.out.printf("¦¢%-20s\t%5d\t%4d\t%8d¦¢\n", rs.getString(1), rs.getInt(3), rs.getInt(2), rs.getInt(2) * rs.getInt(3));
				sum += rs.getInt(2) * rs.getInt(3);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
