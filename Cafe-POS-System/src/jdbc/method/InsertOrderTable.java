package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;

import jdbc.hikari.HikariCP;

public class InsertOrderTable {
	
	private String sql = "INSERT INTO order_table (order_id, order_time, order_total) "
			+ "VALUES (order_id_seq.nextval, TO_CHAR(sysdate, 'YYYY-MM-DD'), ?)";
	private int totalPrice;
	
	public InsertOrderTable(int totalPrice) {
		
		this.totalPrice = totalPrice;
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			
			pstmt.setInt(1, totalPrice);
			ResultSet rs = pstmt.executeQuery();
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}