package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

public class SelectRtdInfo {
	private String sql = "SELECT rtd_name, t.rtd_price, r.menu_count FROM rtd_table t INNER JOIN receipt_table r USING(rtd_id)";
	private int sum;
	public int getSum() {
		return sum;
	}
	public SelectRtdInfo() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			sum = 0;
			while (rs.next()) {
				System.out.printf("¦¢%-20s\t%5d\t%4d\t%8d¦¢\n", rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(2) * rs.getInt(3));
				sum += rs.getInt(2) * rs.getInt(3);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
