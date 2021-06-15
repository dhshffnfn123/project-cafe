package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

public class RoadEmployeeName {
	private String sql = "SELECT employee_name FROM employees_table INNER JOIN order_table USING(employee_id)";
	private String employeeName;
	public RoadEmployeeName() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, 
			             ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = pstmt.executeQuery();
				) {
			rs.last();
			this.employeeName = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String getEmployeeName() {
		return employeeName;
	}
}
