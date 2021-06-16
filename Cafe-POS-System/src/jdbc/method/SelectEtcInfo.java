package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

public class SelectEtcInfo {
	private String sql = "SELECT r.rtd_name, r.receipt_count, t.rtd_price FROM rtd_table t INNER JOIN receipt_table r USING(m_type_id)";
	private int sum;
	public int getSum() {
		return sum;
	}
	public SelectEtcInfo() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			sum = 0;
			while (rs.next()) {
				System.out.printf("¦¢%-20s\t%5d\t%4d\t%8d¦¢\n", rs.getInt(1), rs.getString(3), rs.getInt(2), rs.getInt(2) * rs.getInt(3));
				sum += rs.getInt(2) * rs.getInt(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
