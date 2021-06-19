package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

public class selectReceiptNumber {
	
	private String sql = "SELECT receipt_id FROM receipt_table ORDER BY receipt_id";
	private int receiptNumber;
	
	public selectReceiptNumber() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = pstmt.executeQuery();

		){
			rs.last();
			receiptNumber = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getReceiptNumber() {
		return receiptNumber;
	}
	
}
