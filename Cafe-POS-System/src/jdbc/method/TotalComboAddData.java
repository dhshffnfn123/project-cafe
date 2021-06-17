package jdbc.method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import jdbc.hikari.HikariCP;

public class TotalComboAddData {
	
	private JComboBox year_combo, month_combo;
	private String sql = "SELECT TO_CHAR(order_time, 'YYYY') FROM order_table GROUP BY TO_CHAR(order_time, 'YYYY')";
	private String sql2 = "SELECT TO_CHAR(order_time, 'MM') FROM order_table GROUP BY TO_CHAR(order_time, 'MM')";


	
	public JComboBox year_getComboBox() {
		comboBox_addData();
		return year_combo;
	}
	public JComboBox month_getComboBox() {
		comboBox_addData();
		return month_combo;
	}

	
	
	private void comboBox_addData() {
		year_combo = new JComboBox();
		year_combo.addItem("년도 선택");
		year_combo.addItem("ALL");
		
		month_combo = new JComboBox();
		month_combo.addItem("월 선택");
		month_combo.addItem("ALL");
		month_combo.addItem("None");
		
		
		
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);

				ResultSet rs = pstmt.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
		) {
			while (rs.next()) {
				year_combo.addItem(rs.getString(1));
			}
			while (rs2.next()) {
				month_combo.addItem(rs2.getString(1));
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
}
