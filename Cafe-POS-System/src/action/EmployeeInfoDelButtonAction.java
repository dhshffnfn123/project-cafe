package action;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jdbc.hikari.HikariCP;
import jdbc.method.SelectEmployeeInfo;
import swing.method.RenewalToTable;

public class EmployeeInfoDelButtonAction implements ActionListener {

	private String sql = "DELETE FROM employees_table WHERE employee_id = ? AND employee_name = ? AND employee_pw = ? AND employee_grade = ?";
	private String employee_id;
	private String employee_name;
	private String employee_pw;
	private String employee_grade;
	private ArrayList<TextField> fields = new ArrayList<>();
	private JTable table;
	
	public EmployeeInfoDelButtonAction(ArrayList<TextField> fields, JTable table) {
		this.fields = fields;
		this.table = table;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (fields.get(0).getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "삭제할 직원 정보를 선택해주세요.", "Guide Message", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String message = String.format("직원 아이디: %s\n이 름: %s\n삭제하는 것이 맞습니까?", fields.get(0).getText(), fields.get(1).getText());
			// 사용자의 응답을 int로 반환
			int result = JOptionPane.showConfirmDialog(null, message, "Confirm Message", JOptionPane.YES_NO_OPTION);
			// YES인 경우에만 정보 삭제
			if (result == JOptionPane.YES_OPTION) {
				try (
						Connection conn = HikariCP.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql);
					) {
					this.employee_id = fields.get(0).getText();
					this.employee_name = fields.get(1).getText();
					this.employee_pw = fields.get(2).getText();
					this.employee_grade = fields.get(3).getText();
					
					pstmt.setString(1, employee_id);
					pstmt.setString(2, employee_name);
					pstmt.setString(3, employee_pw);
					pstmt.setString(4, employee_grade);
					
					pstmt.executeQuery();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		new RenewalToTable(table);
	}
}
