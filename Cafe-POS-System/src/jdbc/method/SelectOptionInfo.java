package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

public class SelectOptionInfo {
	private String sql = "SELECT option_name, o.option_price, menu_count FROM option_table o INNER JOIN receipt_table r USING(option_id)";
	private int sum;
	private int optionIndex;
	public int getSum() {
		return sum;
	}
	public SelectOptionInfo(int optionIndex) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, 
			             ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = pstmt.executeQuery();
		) {
			rs.relative(optionIndex);
			if (rs.next()) {
				System.out.printf("¦¢%-30s\t%5d\t%4d\t%8d¦¢\n", rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(2) * rs.getInt(3));
				sum += rs.getInt(2) * rs.getInt(3);
			}

			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




}