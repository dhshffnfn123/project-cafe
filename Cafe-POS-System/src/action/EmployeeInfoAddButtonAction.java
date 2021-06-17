package action;

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

import jdbc.hikari.HikariCP;
import swing.method.RenewalToTable;

public class EmployeeInfoAddButtonAction implements ActionListener {
	
	private String sql = "INSERT INTO employees_table VALUES(employee_id_seq.nextval, ?, ?, ?)";
	private String employee_name;
	private String employee_pw;
	private String employee_grade;
	private ArrayList<TextField> fields = new ArrayList<>();
	private JComboBox<String> grade_box;
	private JTable table;
	
	public EmployeeInfoAddButtonAction(ArrayList<TextField> fields, JComboBox<String> grade_box, JTable table) {
		this.fields = fields;
		this.grade_box = grade_box;
		this.table = table;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 입력받은 정보가 null이 아닌 경우 정보 추가되지 않도록 조건 설정
		if (fields.get(0).getText().isEmpty() || fields.get(1).getText().isEmpty()) {
			String check = String.format("이름과 패스워드 중 입력하지 않은 곳이 있습니다.\n입력 정보를 확인해주세요.");
			JOptionPane.showMessageDialog(null, check, "Guide Message", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String check = String.format("이 름: %s\n패스워드: %s\n직 급: %s\n등록하시겠습니까?", fields.get(0).getText(), fields.get(1).getText(),
					(String)grade_box.getSelectedItem());
			int result = JOptionPane.showConfirmDialog(null, check, "Confirm Message", JOptionPane.YES_NO_OPTION);
			
			if (result == JOptionPane.YES_OPTION) {
				try (
						Connection conn = HikariCP.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql);
					) {
					
					this.employee_name = fields.get(0).getText();
					this.employee_pw = fields.get(1).getText();
					this.employee_grade = (String)grade_box.getSelectedItem();
					
					pstmt.setString(1, employee_name);
					pstmt.setString(2, employee_pw);
					pstmt.setString(3, employee_grade);
					
					pstmt.executeQuery();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		new RenewalToTable(table);
	}
}
