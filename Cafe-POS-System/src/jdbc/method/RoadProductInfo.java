package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

public class RoadProductInfo {
	private String sql = "SELECT product_name FROM product_table";

	public RoadProductInfo(String menu_name,int total_count) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {

			while (rs.next()) {
				if (menu_name.equals(rs.getString(1))) {
					new RoadStockCount(menu_name, total_count);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	
	}
}