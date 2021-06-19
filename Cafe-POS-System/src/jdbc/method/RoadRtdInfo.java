package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

public class RoadRtdInfo {
	private String sql = "SELECT rtd_name FROM rtd_table";

	public RoadRtdInfo(String menu_name,int total_count) {
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
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	
	}
}