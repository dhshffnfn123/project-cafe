package jdbc.method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import jdbc.hikari.HikariCP;

public class LoginComboxData {
	private String sql = "select employee_id || employee_name from employees_table";
	private JComboBox combox;
	
	public JComboBox getComboBox() {
		ComboBox_addData();
		return combox;
	}
	
	private void ComboBox_addData() {
		combox = new JComboBox();
		combox.addItem("ID를 선택하세요");
		
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				combox.addItem(rs.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
