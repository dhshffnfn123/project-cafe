package action;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import jdbc.hikari.HikariCP;

public class EmployeeInfoUpdateButton implements ActionListener {

	private String sql = "UPDATE employees_table SET employee_pw = ?, employee_grade = ? WHERE employee_id = ? AND employee_name = ?";
	private String employee_id;
	private String employee_name;
	private String employee_pw;
	private String employee_grade;
	private ArrayList<TextField> fields = new ArrayList<>();
	private JComboBox<String> grade_box;
	private JTable table;
	
	public EmployeeInfoUpdateButton(ArrayList<TextField> fields, JComboBox<String> grade_box, JTable table) {
		this.fields = fields;
		this.grade_box = grade_box;
		this.table = table;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			this.employee_id = fields.get(0).getText();
			this.employee_name = fields.get(1).getText();
			this.employee_pw = fields.get(2).getText();
			this.employee_grade = (String)grade_box.getSelectedItem();
			
			pstmt.setString(1, employee_pw);
			pstmt.setString(2, employee_grade);
			pstmt.setString(3, employee_id);
			pstmt.setString(4, employee_name);
			
			pstmt.executeQuery();
			
			Vector<String> row = new Vector<String>();
			DefaultTableModel model = new DefaultTableModel();
			
			
			
			
			fields.get(0).setText("");
			fields.get(1).setText("");
			fields.get(2).setText("");
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
	}
}
