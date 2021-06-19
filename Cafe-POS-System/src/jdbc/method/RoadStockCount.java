package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;
import swing.frame.OrderFrame;

public class RoadStockCount {
	private String sql = "SELECT stock_count FROM stock_table WHERE stock_name = ?";
	private int updateCount;
	public RoadStockCount(String menu_name, int total_count) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, menu_name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				updateCount = rs.getInt(1) - total_count;
				new UpdateStockCount(menu_name, updateCount);				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
