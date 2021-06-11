package jdbc.method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import jdbc.hikari.HikariCP;

public class Login_combox_data {
	private String sql = "SELECT * FROM employees_table";
	private JComboBox combox;
	
	public JComboBox getComboBox() {
		ComboBox_addData();
		return combox;
	}
	
	
	private void ComboBox_addData() {
		combox = new JComboBox();
		combox.addItem("ID를 선택하세요");
		
		try (
				//Connection conn = HikariCP.getConnection();
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1","cafe_project", "1234");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				combox.addItem(rs.getString(2));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
}
