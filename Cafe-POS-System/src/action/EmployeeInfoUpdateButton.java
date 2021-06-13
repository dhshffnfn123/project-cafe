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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jdbc.hikari.HikariCP;
import jdbc.method.RenewalToTable;
import jdbc.method.SelectEmployeeInfo;

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
		if (fields.get(0).getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "변경할 직원 정보를 선택해주세요.", "Guide Message", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String message = String.format("직원 아이디: %s\n이 름: %s\n수정하시겠습니까?", fields.get(0).getText(), fields.get(1).getText());
			int result = JOptionPane.showConfirmDialog(null, message, "Confirm Message", JOptionPane.YES_NO_OPTION);
			
			if (result == JOptionPane.YES_OPTION) {
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
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		new RenewalToTable(table);
	}
}
