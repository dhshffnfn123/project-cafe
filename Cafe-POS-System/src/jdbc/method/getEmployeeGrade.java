package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

public class getEmployeeGrade {
	private String sql = "SELECT employee_grade FROM employees_table WHERE employee_name = ?";
	private String grade;
	
	public getEmployeeGrade(String getname) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			
			pstmt.setString(1, getname);
			System.out.println(getname);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			grade = rs.getString(1);
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String getGrade() {
		return grade;
	}
}
