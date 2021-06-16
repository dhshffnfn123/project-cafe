package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

public class SelectDrinkInfo {
	private String sql = "SELECT drink_name, drink_price, menu_count FROM drink_table d INNER JOIN receipt_table r USING(drink_id)";
	private int sum;
	private int drinkIndex;
	public int getSum() {
		return sum;
	}
	
	public SelectDrinkInfo(int drinkIndex) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, 
			             ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = pstmt.executeQuery();
		) {
			rs.relative(drinkIndex);
			if (rs.next()) {				
				System.out.printf("¦¢%-20s\t%5d\t%4d\t%8d¦¢\n", rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(2) * rs.getInt(3));
				sum += rs.getInt(2) * rs.getInt(3);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}