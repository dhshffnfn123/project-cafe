package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

public class UpdateStockCount {
	private String sql = "UPDATE stock_table SET stock_count = ? WHERE stock_name = ?";
	public UpdateStockCount(String menu_name, int total_count) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, total_count);
			pstmt.setString(2, menu_name);
			ResultSet rs = pstmt.executeQuery();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}